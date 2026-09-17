package com.zzztie.careerhub;

import com.zzztie.careerhub.domain.Job;
import com.zzztie.careerhub.domain.Resume;
import com.zzztie.careerhub.domain.User;

public class Main {
    public static void main(String[] args) {
//        User user1=new User();
//        user1.setId(1L);
//        user1.setUsername("zzztie");
//        user1.setEmail("zzztie@gmail.com");
//        user1.printInfo();
//
//        User user2=new User(2L,"mailegod","outlook.com");
//        user2.printInfo();

        Resume resume=new Resume("D盘","简历1",1L,1L);
        Job job=new Job(1L,"java后端开发","人工智能开发公司","专注后端开发");
        resume.printInfo();
        job.printInfo();
    }
}
