package com.zzztie.careerhub.service.impl;

import com.zzztie.careerhub.domain.Job;
import com.zzztie.careerhub.exception.JobNotFoundException;
import com.zzztie.careerhub.repository.JobRepository;
import com.zzztie.careerhub.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job createJob(Job job) {
        if (jobRepository.existsById(job.getId())){
            throw new IllegalArgumentException("job id already exists");
        }
        return jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        Job job = jobRepository.findById(id);
        if(job == null){
            throw new JobNotFoundException(id);
        }
        return job;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job updateJob(Job job) {
        if(!jobRepository.existsById(job.getId())){
            throw new JobNotFoundException(job.getId());
        }
        return jobRepository.save(job);
    }

    @Override
    public boolean deleteJob(Long id) {
        return jobRepository.deleteById(id);
    }
}
