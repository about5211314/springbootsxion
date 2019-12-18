package com.core.springbootcore.user.service.impl;

import com.core.springbootcore.user.entity.SysUser;
import com.core.springbootcore.user.mapper.SysUserMapper;
import com.core.springbootcore.user.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author zhuwei
 * @since 2019-12-18
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
