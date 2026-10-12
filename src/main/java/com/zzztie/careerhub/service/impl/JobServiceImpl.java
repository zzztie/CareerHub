package com.zzztie.careerhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzztie.careerhub.domain.Job;
import com.zzztie.careerhub.enums.JobStatus;
import com.zzztie.careerhub.exception.JobNotFoundException;
import com.zzztie.careerhub.mapper.JobMapper;
import com.zzztie.careerhub.service.JobService;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;

import java.time.LocalDateTime;
import java.util.List;

import java.util.concurrent.TimeUnit;


@Service
public class JobServiceImpl implements JobService {
    private static final String JOB_DETAIL_KEY_PREFIX = "job:detail:";
    private final JobMapper jobMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);


    private void invalidateJobCache(Long jobId) {
        String key = buildJobDetailKey(jobId);
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                Boolean deleted = stringRedisTemplate.delete(key);
                log.info(
                        "Job cache invalidated: key={}, deleted={}",
                        key,
                        deleted
                );
                return;
            } catch (DataAccessException e) {
                log.warn(
                        "Job cache deletion failed: key={}, attempt={}",
                        key,
                        attempt,
                        e
                );
                if (attempt == 3) {
                    log.error(
                            "Job cache invalidation failed after 3 attempts: key={}",
                            key
                    );
                    throw e;
                }
            }
        }
    }

    public JobServiceImpl(JobMapper jobMapper,ObjectMapper objectMapper,StringRedisTemplate stringRedisTemplate) {
        this.jobMapper = jobMapper;
        this.objectMapper = objectMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public String buildJobDetailKey(Long id){
        return JOB_DETAIL_KEY_PREFIX+id;
    }

    @Override
    public Job createJob(Job job) {
        LocalDateTime now = LocalDateTime.now();
        if (job.getStatus() == null) {
            job.setStatus(JobStatus.OPEN);
        }
        job.setCreateTime(now);
        job.setUpdateTime(now);
        jobMapper.insert(job);
        return job;
    }

    @Override
    public Job getJobById(Long id) {
        String key = buildJobDetailKey(id);

        // 第一步：尝试读取 Redis
        try {
            String cachedJobJson =
                    stringRedisTemplate.opsForValue().get(key);

            if (cachedJobJson != null) {
                try {
                    return objectMapper.readValue(
                            cachedJobJson, Job.class
                    );
                } catch (JsonProcessingException e) {
                    log.warn("Job cache JSON invalid, id={}", id, e);
                    stringRedisTemplate.delete(key);
                }
            }
        } catch (DataAccessException e) {
            // Redis 故障：记录日志，继续查询 MySQL
            log.warn("Redis read failed, id={}", id, e);
        }

        // 第二步：查询 MySQL
        Job job = jobMapper.selectById(id);

        if (job == null) {
            throw new JobNotFoundException(id);
        }

        // 第三步：尝试回填 Redis
        try {
            String jobJson =
                    objectMapper.writeValueAsString(job);

            stringRedisTemplate.opsForValue().set(
                    key,
                    jobJson,
                    10,
                    TimeUnit.MINUTES
            );
        } catch (JsonProcessingException e) {
            log.warn("Job JSON serialization failed, id={}", id, e);
        } catch (DataAccessException e) {
            // 回填失败不影响已经查询到的岗位
            log.warn("Redis write failed, id={}", id, e);
        }

        return job;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobMapper.selectList(null);
    }

    @Override
    public Job updateJob(Job job) {
        Job existingJob = jobMapper.selectById(job.getId());
        if(existingJob == null){
            throw new JobNotFoundException(job.getId());
        }
        job.setUpdateTime(LocalDateTime.now());
        jobMapper.updateById(job);
        invalidateJobCache(job.getId());
        return job;
    }

    @Override
    public boolean deleteJob(Long id) {
        int rows = jobMapper.deleteById(id);
        if(rows == 0){
            throw new JobNotFoundException(id);
        }
        invalidateJobCache(id);
        return true;
    }

    @Override
    public IPage<Job> searchJobs(String keyword, JobStatus status, long page, long size) {
        LambdaQueryWrapper<Job> wrapper = new LambdaQueryWrapper<>();
        if(keyword != null && !keyword.isBlank()){
            wrapper.and(w ->w
                    .like(Job :: getTitle,keyword)
                    .or()
                    .like(Job :: getCompany,keyword)
                    .or()
                    .like(Job :: getDescription,keyword));
        }
        if(status != null){
            wrapper.eq(Job :: getStatus,status);
        }
        Page<Job> pageRequest = new Page<>(page,size);
        return jobMapper.selectPage(pageRequest,wrapper);
    }

    @Override
    public long countJobs() {
        return jobMapper.selectCount(null);
    }
}
