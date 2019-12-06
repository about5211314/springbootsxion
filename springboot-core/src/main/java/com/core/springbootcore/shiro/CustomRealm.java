package com.core.springbootcore.shiro;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.core.springbootcore.menu.entity.SysMenu;
import com.core.springbootcore.menu.service.ISysMenuService;
import com.core.springbootcore.role.entity.SysRole;
import com.core.springbootcore.role.service.ISysRoleService;
import com.core.springbootcore.user.entity.SysUser;
import com.core.springbootcore.user.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService isysUserService;
    @Autowired
    private ISysRoleService iSysRoleService;
    @Autowired
    private ISysMenuService iSysMenuService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();



        List<String> permsList;
        //默认用户拥有最高权限
        List<SysMenu> menuList = iSysMenuService.list();
        permsList = new ArrayList<>(menuList.size());
        for(SysMenu menu : menuList){
            permsList.add(menu.getPerms());
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isEmpty(perms)){
                continue;
            }
            System.out.println(perms);
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        info.setStringPermissions(permsSet);

        return info;
    }

    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
     * private UserService userService;
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        String password;
        String salt = "123";
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        String encodedPassword =ShiroEncryption.shiroEncryption(userPwd,salt);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
        wrapper.eq("user_name",userName);
        wrapper.eq("password",encodedPassword);
        List<SysUser> sysUser = isysUserService.list(wrapper);
        if (sysUser.size() == 0) {
            throw new AccountException("用户不存在");
        }else {
            //根据用户名从数据库获取密码
             password = sysUser.get(0).getPassword();
        }
        if (userName == null) {
            throw new AccountException("用户名不正确");
        } else if (!encodedPassword.equals(password)) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(userName, encodedPassword, ByteSource.Util.bytes(salt) ,getName());
    }
}