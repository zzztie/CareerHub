package com.zzztie.careerhub;

import com.zzztie.careerhub.domain.BaseEntity;
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
//        user1.updateProfile("zzztie");
//        user1.updateProfile("zzztie","zzztie666");
//
//        User user2=new User(2L,"mailegod","outlook.com");
//        user2.printInfo();
//
//        Resume resume=new Resume("D盘","简历1",1L,1L);
//        Job job=new Job(1L,"java后端开发","人工智能开发公司","专注后端开发");
//        resume.printInfo();
//        job.printInfo();

//        BaseEntity baseEntity1=new User(
//                1L,
//                "Tom",
//                "tom@example.com"
//        );
//        baseEntity1.printInfo();
//
//        BaseEntity baseEntity2=new Resume("D盘","简历",2L,2L);
//        BaseEntity baseEntity3=new Job(3L,"java开发","Unitree","具身机器人开发");
//        baseEntity1.printInfo();
//        baseEntity2.printInfo();
//        baseEntity3.printInfo();

        User user=new User(1L, "Tom", "tom@example.com");
        Resume resume = new Resume("resume.pdf", "D:\\resume\\resume.pdf",2L, 1L);
        Job job = new Job(3L, "Java后端开发", "ABC公司", "负责Java后端开发");
        showEntity(user);
        showEntity(resume);
        showEntity(job);
    }

    public static void showEntity(BaseEntity baseEntity){
        baseEntity.printInfo();
    }
}
