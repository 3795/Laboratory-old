package cn.ntshare.laboratory.service.impl;

import cn.ntshare.laboratory.dao.UserMapper;
import cn.ntshare.laboratory.entity.User;
import cn.ntshare.laboratory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByAccount(String account) {
        return userMapper.findByAccount(account);
    }

    @Override
    public boolean login(String account, String password) {
        User user = userMapper.findByAccount(account);
        return user != null && user.getPassword().equals(password);
    }
}
