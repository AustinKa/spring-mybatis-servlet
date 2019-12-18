package net.wanho.service.impl;

import net.wanho.mapper.RoleMapper;
import net.wanho.pojo.Role;
import net.wanho.service.RoleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/12/17.
 */
@Service
public class RoleServiceImpl implements RoleServiceI {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRolesByName(String username) {
        List<Role> roles = roleMapper.getRolesByName(username);
        return roles;
    }
}
