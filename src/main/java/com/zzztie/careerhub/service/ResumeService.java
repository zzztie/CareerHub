package com.zzztie.careerhub.service;

import com.zzztie.careerhub.domain.Resume;

import java.util.List;

public interface ResumeService {
    Resume createResume(Resume resume);
    Resume getResumeById(Long id);
    List<Resume> getResumeByUserId(Long userId);
    boolean deleteResume(Long id);
}
