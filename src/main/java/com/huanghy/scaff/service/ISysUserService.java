package com.huanghy.scaff.service;

import com.huanghy.scaff.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-05
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser getOneByUserName(String userName);

}
