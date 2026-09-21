package com.zzztie.careerhub.service;

import com.zzztie.careerhub.domain.Job;

import java.util.List;

public interface JobService {
    Job createJob(Job job);
    Job getJobById(Long id);
    List<Job> getAllJobs();
    Job updateJob(Job job);
    boolean deleteJob(Long id);
}
