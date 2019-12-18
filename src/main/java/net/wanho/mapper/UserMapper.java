package net.wanho.mapper;

import net.wanho.pojo.User;

/**
 * Created by Administrator on 2019/12/17.
 */
public interface UserMapper {
    void addUser(User user);
    User getByUsername(String username);
}
