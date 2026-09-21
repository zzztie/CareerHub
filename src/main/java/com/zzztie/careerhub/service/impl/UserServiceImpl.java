package com.zzztie.careerhub.service.impl;

import com.zzztie.careerhub.domain.User;
import com.zzztie.careerhub.enums.UserStatus;
import com.zzztie.careerhub.exception.UserNotFoundException;
import com.zzztie.careerhub.repository.UserRepository;
import com.zzztie.careerhub.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if(userRepository.existsById(user.getId())){
            throw new IllegalArgumentException("user id already exists");
        }
        user.setUpdateTime(LocalDateTime.now());
        user.setStatus(UserStatus.ACTIVE);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id);
        if(user == null){
            throw new UserNotFoundException(id);
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        if(!userRepository.existsById(user.getId())){
            throw new UserNotFoundException(user.getId());
        }
        user.setUpdateTime(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
