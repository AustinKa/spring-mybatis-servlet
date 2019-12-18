package net.wanho.realm;

import net.wanho.pojo.Permission;
import net.wanho.pojo.Role;
import net.wanho.pojo.User;
import net.wanho.service.RoleServiceI;
import net.wanho.service.UserServiceI;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/12/16.
 */
public class MyRealm extends AuthorizingRealm{
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private RoleServiceI roleServiceI;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       // 获取当前的用户//
        String username = (String) principalCollection.getPrimaryPrincipal();
        System.out.println(username);
        List<Role> roles1 = roleServiceI.getRolesByName(username);
        System.out.println(roles1);
        if (CollectionUtils.isEmpty(roles1)) {
            throw new RuntimeException("角色权限信息有误");
        }

        List<String> roles =new ArrayList<>();
        List<String> permissons = new ArrayList<>();
        for(Role list:roles1){
            roles.add(list.getRoleName());
            List<Permission> p = list.getPermissionList();
            for(Permission permission:p){
                permissons.add(permission.getPermission());
            }

        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(permissons);

        return simpleAuthorizationInfo;
    }
//验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username= (String) authenticationToken.getPrincipal();
        String password= new String ((char[])authenticationToken.getCredentials());
        User username1 = userServiceI.getUsername(username);

        if(username1==null){
            throw new RuntimeException("用户不存在");
        }
        String pwd = userServiceI.shiroMD5(password, username1.getSalt());
        if(!pwd.equals(username1.getPassword())){
            throw new IncorrectCredentialsException("密码不正确");
        }
        return new SimpleAuthenticationInfo(username1.getUsername(),username1.getPassword(),ByteSource.Util.bytes(username1.getSalt()),getName());
    }

    @Override
    public String getName() {
        return "myRealm";
    }


}
