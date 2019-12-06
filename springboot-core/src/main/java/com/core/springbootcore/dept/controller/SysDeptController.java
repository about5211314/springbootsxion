package com.core.springbootcore.dept.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.springbootcore.dept.entity.SysDept;
import com.core.springbootcore.dept.mapper.SysDeptMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 组织管理 前端控制器
 * </p>
 *
 * @author shenl
 * @since 2019-11-14
 */
@RestController
@RequestMapping("/dept/sys-dept")
public class SysDeptController {

    @Resource
    private SysDeptMapper sysDeptMapper;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String defaultLogin() {
        return "首页222";
    }

    /**
     * 添加数据
     * @param sysDept
     * @return
     */
    @RequiresPermissions("sys:dept:add")
    @ResponseBody
    @RequestMapping("/insert")
    public int insert (SysDept sysDept){
        return sysDeptMapper.insert(sysDept);
    }



    /**
     * 删除
     * @param deptId
     * @return
     */
    @RequiresPermissions("sys:dept:del")
    @ResponseBody
    @RequestMapping("/remove")
    public int remove(Integer deptId){
        return sysDeptMapper.deleteById(deptId);
    }


    /**
     * 修改
     * @param sysDept
     * @return
     */
    @RequiresPermissions("sys:dept:update")
    @ResponseBody
    @RequestMapping("/update")
    public int update(SysDept sysDept){
        return sysDeptMapper.updateById(sysDept);
    }


    /**
     * 查询
     * @param current size
     * @return
     */

    @RequiresPermissions("sys:dept:shiro")
    @ResponseBody
    @RequestMapping(value = "/listall")//,method = RequestMethod.POST)
    public IPage<SysDept> listall(int current, int size) {


        IPage<SysDept> page = new Page<>(current, size);
        LambdaQueryWrapper<SysDept> wrapper = new LambdaQueryWrapper<>();

        try {
            page = sysDeptMapper.selectPage( page, wrapper) ;
            System.out.println(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("================= 相关的分页信息 ==================");
        System.out.println("总条数:" + page.getTotal());
        System.out.println("当前页码:" + page.getCurrent());
        System.out.println("总页数:" + page.getPages());
        System.out.println("每页显示条数:" + page.getSize());
        System.out.println("当前数据:" + page.getRecords());
        return page;

    }

}
