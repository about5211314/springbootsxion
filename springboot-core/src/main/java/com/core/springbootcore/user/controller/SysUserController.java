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
/**
* <p>
    * 系统用户 前端控制器
    * </p>
*
* @author zhuwei
* @since 2019-12-18
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
* 获取数据列表
*/
@RequestMapping("/list")
@ResponseBody
public BaseResponse findListByPage(@RequestParam(name = "page", defaultValue = "1") int pageIndex,@RequestParam(name = "rows", defaultValue = "20") int step){
Page page = new Page(pageIndex,step);
targetService.page(page);
return BaseResponse.success(page);
}


/**
* 获取全部数据
*/
@RequestMapping("/all")
@ResponseBody
public BaseResponse findAll(){
List<SysUser> models = targetService.list(null);
return BaseResponse.success(models);
}


/**
* 根据ID查找数据
*/
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
* 添加数据
*/
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
* 更新数据
*/
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
* 删除数据
*/
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

