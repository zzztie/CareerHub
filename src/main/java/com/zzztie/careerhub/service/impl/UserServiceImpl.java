package com.zzztie.careerhub.service.impl;

import com.zzztie.careerhub.domain.User;
import com.zzztie.careerhub.enums.UserStatus;
import com.zzztie.careerhub.exception.UserNotFoundException;
import com.zzztie.careerhub.mapper.UserMapper;
import com.zzztie.careerhub.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User createUser(User user) {
        user.setStatus(UserStatus.ACTIVE);
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        userMapper.insert(user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user = userMapper.selectById(id);
        if(user == null){
            throw new UserNotFoundException(id);
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userMapper.selectById(user.getId());
        if(existingUser == null){
            throw new UserNotFoundException(user.getId());
        }
        user.setUpdateTime(LocalDateTime.now());

        userMapper.updateById(user);
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        int rows = userMapper.deleteById(id);
        if(rows == 0){
            throw new UserNotFoundException(id);
        }
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }
}
