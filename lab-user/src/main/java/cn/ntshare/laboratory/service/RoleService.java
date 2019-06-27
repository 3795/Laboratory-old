package cn.ntshare.laboratory.service;

import java.util.List;

public interface RoleService {

    List<String> findRoleByUserId(Integer id);
}
