package net.wanho.mapper;

import net.wanho.pojo.Role;

import java.util.List;

/**
 * Created by Administrator on 2019/12/17.
 */
public interface RoleMapper {
    List<Role> getRolesByName(String username);
}
