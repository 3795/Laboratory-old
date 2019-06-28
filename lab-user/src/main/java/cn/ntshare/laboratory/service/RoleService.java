package cn.ntshare.laboratory.service;

import cn.ntshare.laboratory.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findRoleByUserId(Integer id);
}
