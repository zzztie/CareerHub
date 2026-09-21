package com.zzztie.careerhub.service.impl;

import com.zzztie.careerhub.domain.Resume;
import com.zzztie.careerhub.domain.User;
import com.zzztie.careerhub.exception.ResumeNotFoundException;
import com.zzztie.careerhub.exception.UserNotFoundException;
import com.zzztie.careerhub.repository.ResumeRepository;
import com.zzztie.careerhub.repository.UserRepository;
import com.zzztie.careerhub.service.ResumeService;

import java.util.List;

public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeServiceImpl(ResumeRepository resumeRepository, UserRepository userRepository) {
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Resume createResume(Resume resume) {
        if(!userRepository.existsById(resume.getUserId())){
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
        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException(userId);
        }
        return resumeRepository.findByUserId(userId);
    }

    @Override
    public boolean deleteResume(Long id) {
        return resumeRepository.deleteById(id);
    }
}
