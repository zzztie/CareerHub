package com.zzztie.careerhub.service;

import com.zzztie.careerhub.domain.User;
import com.zzztie.careerhub.enums.UserStatus;
import com.zzztie.careerhub.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public User createUser(User user) {
        if(users.containsKey(user.getId())){
            throw new IllegalArgumentException("user id already exists");
        }
        user.setUpdateTime(LocalDateTime.now());
        user.setStatus(UserStatus.ACTIVE);
        users.put(user.getId(),user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user=users.get(id);
        if(user == null){
            throw new UserNotFoundException(id);
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        if(!users.containsKey(user.getId())){
            throw new UserNotFoundException(user.getId());
        }
        user.setUpdateTime(LocalDateTime.now());
        users.put(user.getId(),user);
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        if(!users.containsKey(id)){
            throw new IllegalArgumentException("user not found");
        }
        users.remove(id);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
}
