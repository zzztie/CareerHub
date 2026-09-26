package com.zzztie.careerhub.service.impl;

import com.zzztie.careerhub.domain.Resume;
import com.zzztie.careerhub.domain.User;
import com.zzztie.careerhub.exception.ResumeNotFoundException;
import com.zzztie.careerhub.exception.UserNotFoundException;
import com.zzztie.careerhub.repository.ResumeRepository;
import com.zzztie.careerhub.service.ResumeService;
import com.zzztie.careerhub.service.UserService;

import java.util.List;

public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;
    private final UserService userService;

    public ResumeServiceImpl(ResumeRepository resumeRepository, UserService userService) {
        this.resumeRepository = resumeRepository;
        this.userService = userService;
    }


    @Override
    public Resume createResume(Resume resume) {
        User exsitingUser = userService.getUserById(resume.getUserId());
        if(exsitingUser == null){
            throw new UserNotFoundException(resume.getUserId());
        }
        if (resumeRepository.existsById(resume.getId())) {
            throw new IllegalArgumentException("resume id already exists");
        }
        return resumeRepository.save(resume);
    }

    @Override
    public Resume getResumeById(Long id) {
        Resume resume = resumeRepository.findById(id);
        if(resume == null){
            throw new ResumeNotFoundException(id);
        }
        return resume;
    }

    @Override
    public List<Resume> getResumeByUserId(Long userId) {
        User existingUser = userService.getUserById(userId);
        if(existingUser == null){
            throw new UserNotFoundException(userId);
        }
        return resumeRepository.findByUserId(userId);
    }

    @Override
    public boolean deleteResume(Long id) {
        return resumeRepository.deleteById(id);
    }
}
