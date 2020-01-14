package com.core.springbootcore.user.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.springbootcore.user.service.ISysUserService;
import com.core.springbootcore.user.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import com.core.springbootcore.BaseResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
/**
* <p>
    * 系统用户 前端控制器
    * </p>
*
* @author zhuwei
* @since 2020-01-06
*/
@RestController
@RequestMapping("/user/sys-user")
        public class SysUserController {



private ISysUserService targetService;

@Autowired
public SysUserController(ISysUserService targetService){
this.targetService = targetService;
}


/**
* showdoc
* @catalog SysUser
* @title 分页列表
* @description 分页列表接口
* @method post
* @url http://localhost/user/sys-user/list
* @param page 非必选 string 页数（不传默认第一页）
* @param rows 非必选 string 行数（不传默认一页10行）
* @remark 无
* @number 1
*/
@RequiresPermissions("SysUser:list")
@RequestMapping("/list")
@ResponseBody
public BaseResponse findListByPage(@RequestParam(name = "page", defaultValue = "1") int pageIndex,@RequestParam(name = "rows", defaultValue = "10") int step){
Page page = new Page(pageIndex,step);
targetService.page(page);
return BaseResponse.success(page);
}


/**
* showdoc
* @catalog SysUser
* @title 所有数据列表
* @description 所有数据列表
* @method post
* @url http://localhost/user/sys-user/all
* @param 无 无 无 无
* @remark 无
* @number 2
*/
@RequiresPermissions("SysUser:all")
@RequestMapping("/all")
@ResponseBody
public BaseResponse findAll(){
List<SysUser> models = targetService.list(null);
return BaseResponse.success(models);
}


/**
* showdoc
* @catalog SysUser
* @title 详情页面
* @description 详情页面接口
* @method post
* @url http://localhost/user/sys-user/find
* @param id 必选 Long 页数
* @remark 无
* @number 3
*/
@RequiresPermissions("SysUser:find")
@RequestMapping("/find")
@ResponseBody
public BaseResponse find(@RequestParam("id") Long id){
SysUser SysUser = targetService.getById(id);
if(SysUser==null){
return BaseResponse.error("尚未查询到此ID");
}
return BaseResponse.success(SysUser);
}


/**
* showdoc
* @catalog SysUser
* @title 新增数据
* @description 新增数据接口
* @method post
* @url http://localhost/user/sys-user/add
* @param SysUser 必选 SysUser 保存对象
* @remark 无
* @number 4
*/
@RequiresPermissions("SysUser:add")
@RequestMapping(value = "/add", method = RequestMethod.POST)
@ResponseBody
public BaseResponse addItem(@RequestBody SysUser SysUser){
boolean isOk = targetService.save(SysUser);
if(isOk){
return BaseResponse.success("数据添加成功！");
}
return BaseResponse.error("数据添加失败");
}


/**
* showdoc
* @catalog SysUser
* @title 更新数据
* @description 更新数据接口
* @method post
* @url http://localhost/user/sys-user/add
* @param SysUser 必选 SysUser 保存对象
* @remark 无
* @number 5
*/
@RequiresPermissions("SysUser:update")
@RequestMapping(value = "/update", method = RequestMethod.POST)
@ResponseBody
public BaseResponse updateItem(@RequestBody SysUser SysUser){
boolean isOk = targetService.updateById(SysUser);
if(isOk){
return BaseResponse.success("数据更改成功！");
}
return BaseResponse.error("数据更改失败");
}


/**
* showdoc
* @catalog SysUser
* @title 删除数据
* @description 删除数据接口
* @method post
* @url http://localhost/user/sys-user/del
* @param ids 必选 List<Long> 页数
* @remark 无
* @number 7
*/
@RequiresPermissions("SysUser:del")
@RequestMapping("/del")
@ResponseBody
public BaseResponse deleteItems(@RequestParam("ids") List
<Long> ids){
    boolean isOk = targetService.removeByIds(ids);
    if(isOk){
    return BaseResponse.success("数据删除成功！");
    }
    return BaseResponse.error("数据删除失败");
    }
    }

