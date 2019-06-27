package cn.ntshare.laboratory.service.impl;

import cn.ntshare.laboratory.dao.RoleMapper;
import cn.ntshare.laboratory.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<String> findRoleByUserId(Integer id) {
        return roleMapper.findRoleByUserId(id);
    }
}
