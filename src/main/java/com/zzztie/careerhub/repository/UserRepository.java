package com.zzztie.careerhub.repository;

import com.zzztie.careerhub.domain.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    User findById(Long id);
    boolean existsById(Long id);
    boolean deleteById(Long id);
    List<User> findAll();
}
