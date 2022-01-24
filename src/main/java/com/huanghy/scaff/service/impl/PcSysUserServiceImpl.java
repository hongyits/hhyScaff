package com.huanghy.scaff.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanghy.scaff.domain.PcSysUser;
import com.huanghy.scaff.domain.SysUser;
import com.huanghy.scaff.mapper.PcSysUserMapper;
import com.huanghy.scaff.mapper.SysUserMapper;
import com.huanghy.scaff.service.IPcSysUserService;
import com.huanghy.scaff.service.ISysUserService;
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
public class PcSysUserServiceImpl extends ServiceImpl<PcSysUserMapper, PcSysUser> implements IPcSysUserService {



    @Override
    public PcSysUser getOneByUserName(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userName",userName.trim());
        PcSysUser one = this.getOne(queryWrapper);
        return one;
    }



}
