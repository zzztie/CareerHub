package com.zzztie.careerhub.service.impl;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzztie.careerhub.context.UserContext;
import com.zzztie.careerhub.domain.Resume;
import com.zzztie.careerhub.domain.User;
import com.zzztie.careerhub.exception.ResumeNotFoundException;
import com.zzztie.careerhub.mapper.ResumeMapper;
import com.zzztie.careerhub.service.ResumeService;
import com.zzztie.careerhub.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ResumeServiceImpl implements ResumeService {
    private final ResumeMapper resumeMapper;
    private final UserService userService;
    private static final long MAX_FILE_SIZE = 5*1024*1024;

    public ResumeServiceImpl(ResumeMapper resumeMapper, UserService userService) {
        this.resumeMapper = resumeMapper;
        this.userService = userService;
    }

    private void checkResumeOwner(Long userId){
        Long currentUserId = UserContext.getUserId();
        if(currentUserId == null ||
        !Objects.equals(currentUserId,userId)){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You do not have permission to access this resume"
            );
        }
    }

    @Override
    public Resume createResume(Resume resume) {
        checkResumeOwner(resume.getUserId());
        userService.getUserById(resume.getUserId());
        LocalDateTime now = LocalDateTime.now();
        resume.setCreateTime(now);
        resume.setUpdateTime(now);
        resumeMapper.insert(resume);
        return resume;
    }

    @Override
    public Resume getResumeById(Long id) {
        Resume resume = resumeMapper.selectById(id);
        if(resume == null){
            throw new ResumeNotFoundException(id);
        }
        checkResumeOwner(resume.getUserId());
        return resume;
    }

    @Override
    public List<Resume> getResumeByUserId(Long userId) {
        checkResumeOwner(userId);
        userService.getUserById(userId);
        LambdaQueryWrapper<Resume> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Resume :: getUserId,userId);
        return resumeMapper.selectList(wrapper);
    }

    @Override
    @Transactional
    public boolean deleteResume(Long id) {
        // 1. 查询简历，同时检查当前用户是否有权限
        Resume resume = getResumeById(id);
        // 2. 先删除数据库记录（处于事务中）
        int rows = resumeMapper.deleteById(id);
        if (rows == 0) {
            throw new ResumeNotFoundException(id);
        }
        // 3. 再删除磁盘文件
        Path filePath = Paths.get(resume.getFilepath());
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException(
                    "failed to delete resume file", e
            );
        }
        return true;
    }

    @Override
    public Resume uploadResume(Long userId, MultipartFile file) {
        checkResumeOwner(userId);
        userService.getUserById(userId);

        if(file.isEmpty()){
            throw new IllegalArgumentException("resume file cannot be empty");
        }
        String originalFileName = file.getOriginalFilename();
        String contentType = file.getContentType();

        if(originalFileName == null || !originalFileName.toLowerCase().endsWith(".pdf")){
            throw new IllegalArgumentException("resume file must be PDF");
        }

        if(!"application/pdf".equals(contentType)){
            throw new IllegalArgumentException("resume file must be PDF");
        }

        if(file.getSize() > MAX_FILE_SIZE){
            throw new IllegalArgumentException("resume file size cannot exceed 5MB");
        }

        String storedFilename = UUID.randomUUID() + ".pdf";

        Path uploadDir = Paths.get("uploads","resumes");

        Path targetPath = uploadDir.resolve(storedFilename);
        try {
            Files.createDirectories(uploadDir);
            file.transferTo(targetPath);
        } catch (IOException e) {
            throw new RuntimeException("failed to save resume file",e);
        }
        Resume resume = new Resume();
        resume.setUserId(userId);
        resume.setFilename(originalFileName);
        resume.setFilepath(targetPath.toString());
        return createResume(resume);
    }


}
