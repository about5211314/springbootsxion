package com.api.springbootapi.controller;

import com.utils.springbootutils.helper.CommonMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * showdoc
     * @catalog 测试文档/用户相关
     * @title 用户登录
     * @description 用户登录的接口
     * @method get
     * @url https://www.showdoc.cc/home/user/login
     * @header token 可选 string 设备token
     * @return {"error_code":0,"data":{"uid":"1","username":"12154545","name":"吴系挂","groupid":2,"reg_time":"1436864169","last_login_time":"0"}}
     * @return_param groupid int 用户组id
     * @return_param name string 用户昵称
     * @number 99
     */
    @GetMapping("/date")
    public String getdate(){
        Date d = CommonMethod.getCurDate();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "now time ：" + df.format(d);
       // return "ddddd";
    }

}
