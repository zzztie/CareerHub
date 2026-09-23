package com.zzztie.careerhub.controller;

import com.zzztie.careerhub.service.JobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs/count")
    public int countJob(){
        return jobService.getAllJobs().size();
    }
}
