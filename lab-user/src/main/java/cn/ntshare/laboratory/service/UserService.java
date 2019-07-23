package cn.ntshare.laboratory.service;

import cn.ntshare.laboratory.entity.User;

public interface UserService {

    User findByAccount(String account);

    boolean login(String account, String password);
}
