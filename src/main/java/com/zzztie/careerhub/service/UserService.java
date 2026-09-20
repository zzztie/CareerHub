package com.zzztie.careerhub.service;

import com.zzztie.careerhub.domain.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    User updateUser(User user);
    boolean deleteUser(Long id);
    List<User> getAllUsers();
}
