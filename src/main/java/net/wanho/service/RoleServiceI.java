package net.wanho.service;

import net.wanho.pojo.Role;

import java.util.List;

public interface RoleServiceI {

    List<Role> getRolesByName(String username);


}
