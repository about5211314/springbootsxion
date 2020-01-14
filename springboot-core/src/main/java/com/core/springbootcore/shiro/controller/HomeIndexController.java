package com.core.springbootcore.shiro.controller;

import com.core.springbootcore.BaseResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeIndexController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String defaultLogin() {
        return "首页";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Object> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return BaseResponse.error("未知账户");

        } catch (IncorrectCredentialsException ice) {
            return BaseResponse.error("密码不正确");
        } catch (LockedAccountException lae) {
            return BaseResponse.error("账户已锁定");

        } catch (ExcessiveAttemptsException eae) {
            return BaseResponse.error("用户名或密码错误次数过多");

        } catch (AuthenticationException ae) {
            return BaseResponse.error("用户名或密码不正确");

        }
        if (subject.isAuthenticated()) {
            return BaseResponse.success("登录成功");

        } else {
            token.clear();
            return BaseResponse.error("登录失败");
               }
    }
}
