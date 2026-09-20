package com.zzztie.careerhub;

import com.zzztie.careerhub.algorithm.Box;
import com.zzztie.careerhub.domain.BaseEntity;
import com.zzztie.careerhub.domain.Job;
import com.zzztie.careerhub.domain.Resume;
import com.zzztie.careerhub.domain.User;
import com.zzztie.careerhub.enums.UserStatus;
import com.zzztie.careerhub.exception.UserNotFoundException;
import com.zzztie.careerhub.exception.UserValidationException;
import com.zzztie.careerhub.service.Describable;
import com.zzztie.careerhub.service.UserService;
import com.zzztie.careerhub.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//        BaseEntity baseEntity1=new User(1L, "Tom", "tom@example.com");
//        BaseEntity baseEntity2=new Resume("D盘","简历",2L,2L);
//        BaseEntity baseEntity3=new Job(3L,"java开发","Unitree","具身机器人开发");
////        baseEntity1.printInfo();
////        baseEntity2.printInfo();
////        baseEntity3.printInfo();
//        System.out.println(baseEntity1.getEntityType());
//        System.out.println(baseEntity2.getEntityType());
//        System.out.println(baseEntity3.getEntityType());

//        User user=new User(1L, "Tom", "tom@example.com");
//        Resume resume = new Resume("resume.pdf", "D:\\resume\\resume.pdf",2L, 1L);
//        Job job = new Job(3L, "Java后端开发", "ABC公司", "负责Java后端开发");
//        showEntity(user);
//        showEntity(resume);
//        showEntity(job);

//        Describable d1=new User(1L, "Tom", "tom@example.com");
//        Describable d2=new Resume("D盘","简历",2L,2L);
//        Describable d3=new Job(3L,"java开发","Unitree","具身机器人开发");
//        System.out.println(d1.getSummary());
//        System.out.println(d2.getSummary());
//        System.out.println(d3.getSummary());

//        User user = new User();
//
//        try {
//            user.setUsername("");
//        } catch (UserValidationException e) {
//            System.out.println("用户名设置失败：" + e.getMessage());
//        } finally {
//            System.out.println("本次用户名设置操作结束");
//        }
//
//        System.out.println("程序继续运行");

//        List<User> users = new ArrayList<>();
//        User user1 = new User(1L, "Tom", "tom@example.com");
//        User user2 = new User(2L, "Alice", "alice@example.com");
//        User user3 = new User(3L, "Bob", "bob@example.com");
//
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);
//
//        System.out.println(users.size());
//        System.out.println(users.get(0).getUsername());
//        users.remove(1);
//        System.out.println(users.size());
//        System.out.println(users.get(1).getUsername());

//        UserService userService=new UserServiceImpl();
//        User user1 = new User(1L, "Tom", "tom@example.com");
//        User user2 = new User(2L, "Alice", "alice@example.com");
//
//        userService.createUser(user1);
//        userService.createUser(user2);
//
//        User result = userService.getUserById(2L);
//        System.out.println(result.getUsername());

//        UserService userService=new UserServiceImpl();
//        User user1 = new User(1L, "TomNew", "new@example.com");
//        User user2 = new User(2L, "Alice", "alice@example.com");
//        userService.createUser(user1);
//        userService.createUser(user2);
//        User user3 = new User(1L, "ZvetiE", "new@example.com");
//        userService.updateUser(user3);
//        userService.deleteUser(1L);
//        userService.deleteUser(1L);

//        User updateUser = new User(2L, "AliceNew", "new@example.com");
//        userService.updateUser(updateUser);
//        System.out.println(userService.getUserById(2L).getUsername());
//
//        System.out.println(userService.deleteUser(1L));
//
//        List<User> allUsers = userService.getAllUsers();
//        for (User user : allUsers) {
//            user.printInfo();
//        }

//        Box<String> stringBox = new Box<>();
//        stringBox.setValue("CareerHub");
//        System.out.println(stringBox.getValue());
//
//        Box<User> userBox = new Box<>();
//        User user = new User(1L, "Tom", "tom@example.com");
//        userBox.setValue(user);
//        System.out.println(userBox.getValue().getUsername());


//        Map<Long, User> map = new HashMap<>();
//        map.put(1L, new User(1L, "Tom", "tom@example.com"));
//        map.put(2L, new User(2L, "Alice", "alice@example.com"));
//        map.put(3L, new User(3L, "Bob", "bob@example.com"));
//
//        for (Long key : map.keySet()) {
//            System.out.println(key);
//        }
//        for (User user : map.values()) {
//            System.out.println(user.getUsername());
//        }
//        for (Map.Entry<Long, User> entry : map.entrySet()) {
//            Long id=entry.getKey();
//            User user= entry.getValue();
//            System.out.println(
//                    "id="+id+
//                    " user="+user.getUsername()
//            );
////        }
//        User user = new User(1L,"zzztie","outlook.con");
//        UserService userService=new UserServiceImpl();
//        userService.createUser(user);
//        userService.deleteUser(1L);
//        try {
//            userService.updateUser(user);
//        }catch (UserNotFoundException e){
//            System.out.println(e.getMessage());
//        }

//        User user = new User(1L, "Tom", "tom@example.com");
//        if(user.getStatus()==UserStatus.ACTIVE){
//            System.out.println("用户当前可用");
//        }
//        user.setStatus(UserStatus.DISABLED);
//        if (user.getStatus() == UserStatus.DISABLED) {
//            System.out.println("用户已被禁用");
//        }

//        User user = new User(1L, "Tom", "tom@example.com");
//        UserService userService=new UserServiceImpl();
//        userService.createUser(user);
//        System.out.println(user.getCreateTime());
//        System.out.println(user.getUpdateTime());
//
//        user.setUsername("TomNew");
//        userService.updateUser(user);
//        System.out.println(user.getUpdateTime());

        User user = new User(1L, "Tom", "tom@example.com");
        UserService userService = new UserServiceImpl();
        userService.createUser(user);
        System.out.println(user.getStatus());
        System.out.println(user.getCreateTime());
        System.out.println(user.getUpdateTime());
        user.setStatus(UserStatus.DISABLED);
        System.out.println(user.getUpdateTime());
        try {
            userService.getUserById(999L);
        }catch (UserNotFoundException e){
            System.out.println("[ERROR]"+e.getMessage());
        }

    }

    public static void showEntity(BaseEntity baseEntity){
        baseEntity.printInfo();
    }
}
