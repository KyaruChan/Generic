package org.kyaruchan.provider.service.impl;
import org.kyaruchan.model.bean.User;
import org.kyaruchan.provider.mapper.UserMapper;
import org.kyaruchan.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public User getUser(Long id) {
        return userMapper.queryUser(id);
    }

    @Override
    public List<User> getUserInDatabase(String dbName) {
        return userMapper.queryUserInDatabase(dbName);
    }

    @Override
    public List<User> getAll() {
        return userMapper.queryAll();
    }
}
