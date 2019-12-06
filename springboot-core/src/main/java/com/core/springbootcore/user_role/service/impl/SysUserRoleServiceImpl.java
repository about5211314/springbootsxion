package com.core.springbootcore.user_role.service.impl;

import com.core.springbootcore.user_role.entity.SysUserRole;
import com.core.springbootcore.user_role.mapper.SysUserRoleMapper;
import com.core.springbootcore.user_role.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author shenl
 * @since 2019-11-18
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

}
