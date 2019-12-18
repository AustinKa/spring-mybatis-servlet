package net.wanho.controller;

import net.wanho.pojo.User;
import net.wanho.service.UserServiceI;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2019/12/17.
 */
@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UserServiceI userServiceI;
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("register")
    public String register(User user){
        userServiceI.addUser(user);
        return "success";
    }
    @RequestMapping("check")
    public String check(User user,String rememberMe){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        System.out.println(rememberMe);
        if("rememberMe".equals(rememberMe)){
            token.setRememberMe(true);
        }
        subject.login(token);
        Session session = subject.getSession();
        System.out.println(session.getId());
        System.out.println(session.getTimeout());
        System.out.println(session.getStartTimestamp());
        System.out.println(session.getLastAccessTime());
        session.setAttribute("user","zhansan");
        return "redirect:/student/getAllStus";
    }
}
