package com.huanghy.scaff.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huanghy.scaff.domain.PcSysUser;
import com.huanghy.scaff.domain.SysUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-05
 */
public interface IPcSysUserService extends IService<PcSysUser> {

    PcSysUser getOneByUserName(String userName);

}
