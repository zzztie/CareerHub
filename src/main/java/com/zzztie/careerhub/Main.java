package com.zzztie.careerhub;

import com.zzztie.careerhub.domain.BaseEntity;
import com.zzztie.careerhub.domain.Job;
import com.zzztie.careerhub.domain.User;
import com.zzztie.careerhub.repository.JobRepository;
import com.zzztie.careerhub.repository.memory.MemoryJobRepository;
import com.zzztie.careerhub.service.JobService;
import com.zzztie.careerhub.service.impl.JobServiceImpl;

import java.util.Comparator;

public class Main {
    private static final Comparator<User> USER_ID_DESC=Comparator.comparing(User::getId).reversed();
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
//
//        User user = new User(1L, "Tom", "tom@example.com");
//        UserService userService = new UserServiceImpl();
//        userService.createUser(user);
//        System.out.println(user.getStatus());
//        System.out.println(user.getCreateTime());
//        System.out.println(user.getUpdateTime());
//        user.setStatus(UserStatus.DISABLED);
//        System.out.println(user.getUpdateTime());
//        try {
//            userService.getUserById(999L);
//        }catch (UserNotFoundException e){
//            System.out.println("[ERROR]"+e.getMessage());
//        }

//        User user1 = new User(1L, "Tom", "tom@example.com");
//        User user2 = new User(2L, "Alice", "alice@example.com");
//        User user3 = new User(3L, "Bob", "bob@example.com");
//        user1.setStatus(UserStatus.ACTIVE);
//        user2.setStatus(UserStatus.DISABLED);
//        user3.setStatus(UserStatus.ACTIVE);
//        List<User> users = new ArrayList<>(List.of(user1,user2,user3));
//        List<String> activeUsername = users.stream()
//                .filter(user -> user.getStatus() == UserStatus.ACTIVE)
//                .sorted(Comparator.comparing(User :: getId))
//                .map(User :: getUsername)
//                .toList();

//        User user1 = new User(1L, "Tom", "tom@example.com");
//        User user2 = new User(2L, "Alice", "alice@example.com");
//        User user3 = new User(3L, "Bob", "bob@example.com");
//        UserRepository userRepository = new MemoryUserRepository();
//        UserService userService = new UserServiceImpl(userRepository);
//        userService.createUser(user1);
//        userService.createUser(user2);
//        userService.createUser(user3);
//        for (User allUser : userService.getAllUsers()) {
//            System.out.println(allUser.getId()+":"+allUser.getUsername());
//        }
//        userRepository.deleteById(2L);
//        for (User allUser : userService.getAllUsers()) {
//            System.out.println(allUser.getId()+":"+allUser.getUsername());
//        }
//        User user = userService.getUserById(1L);
//        user.setUsername("TomNew");
//        userService.updateUser(user);
//        User updateUser = userService.getUserById(1L);
//        System.out.println(
//                updateUser.getId()+":"+updateUser.getUsername());

//        User user1 = new User(1L, "Tom", "tom@example.com");
//        UserRepository userRepository = new MemoryUserRepository();
//        UserService userService = new UserServiceImpl(userRepository);
//        userService.createUser(user1);
//        ResumeRepository resumeRepository = new MemoryResumeRepository();
//        ResumeService resumeService = new ResumeServiceImpl(resumeRepository,userRepository);
//        Resume resume1 = new Resume("D/zzztie","java后端开发",1L,1L);
//        resumeService.createResume(resume1);
//        Resume resume2 = new Resume("D/royle","具身机器人开发",1L,2L);
//        resumeService.createResume(resume2);
//        for (Resume resume : resumeService.getResumeByUserId(user1.getId())) {
//            System.out.println(resume.getId()+resume.getFilename()+" "+resume.getUserId());
//        }
//        resumeService.deleteResume(2L);
//        for (Resume resume : resumeService.getResumeByUserId(user1.getId())) {
//            System.out.println(resume.getId()+resume.getFilename()+" "+resume.getUserId());
//        }

        JobRepository jobRepository = new MemoryJobRepository();
        JobService jobService = new JobServiceImpl(jobRepository);
        Job job1 = new Job(1L,"开发人员","ByteDance","实习生");
        Job job2 = new Job(2L,"行政人员","ByteDance","就职两年");
        jobService.createJob(job1);
        jobService.createJob(job2);
        System.out.println(jobService.getJobById(1L));
        System.out.println(jobService.getJobById(2L));
//        jobService.updateJob()
        for (Job allJob : jobService.getAllJobs()) {
            System.out.println(allJob.getId()+" "+allJob.getTitle());
        }
        jobService.deleteJob(2L);
        for (Job allJob : jobService.getAllJobs()) {
            System.out.println(allJob.getId()+" "+allJob.getTitle());
        }




    }

    public static void showEntity(BaseEntity baseEntity){
        baseEntity.printInfo();
    }
}
