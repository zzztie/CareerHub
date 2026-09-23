package com.zzztie.careerhub.repository.memory;

import com.zzztie.careerhub.domain.User;
import com.zzztie.careerhub.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class MemoryUserRepository implements UserRepository {
    private final Map<Long, User> users =new HashMap<>();


    @Override
    public User save(User user) {
        users.put(user.getId(),user);
        return user;
    }

    @Override
    public User findById(Long id) {
        return users.get(id);
    }

    @Override
    public boolean existsById(Long id) {
        return users.containsKey(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return users.remove(id)!=null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
