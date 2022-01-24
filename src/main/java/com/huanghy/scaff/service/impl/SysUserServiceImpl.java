package com.huanghy.scaff.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huanghy.scaff.domain.SysUser;
import com.huanghy.scaff.mapper.SysUserMapper;
import com.huanghy.scaff.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {



    @Override
    public SysUser getOneByUserName(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userName",userName.trim());
        SysUser one = this.getOne(queryWrapper);
        return one;
    }



}
