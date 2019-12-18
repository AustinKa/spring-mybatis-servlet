package net.wanho.service.impl;

import net.wanho.mapper.UserMapper;
import net.wanho.pojo.User;
import net.wanho.service.UserServiceI;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Administrator on 2019/12/17.
 */
@Service
public class UserServiceImpl implements UserServiceI {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void addUser(User user) {
        String salt= UUID.randomUUID().toString();
       user.setSalt(salt);
       user.setPassword(shiroMD5(user.getPassword(),salt));
        userMapper.addUser(user);
    }

    @Override
    public User getUsername(String username) {
        User username1 = userMapper.getByUsername(username);
            return username1;
    }

    public String shiroMD5(String password,String salt) {
        //加密方式
        String algorithmName = "MD5";
        //salt转ByteSource型
        ByteSource saltSource = ByteSource.Util.bytes(salt);
        //加密次数
        int hashIterations = 2;
        Object obj = new SimpleHash(algorithmName, password, saltSource, hashIterations);
        return obj.toString();
    }
}
