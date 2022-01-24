package com.huanghy.scaff.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.huanghy.scaff.domain.SysUser;
import com.huanghy.scaff.domain.ZhiboIds;
import com.huanghy.scaff.dto.Result;
import com.huanghy.scaff.dto.ResultGenerator;
import com.huanghy.scaff.service.ISysUserService;
import com.huanghy.scaff.service.IZhiboIdsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-05
 */
@RestController
@RequestMapping("v1/card")
public class SysUserController {

    @Resource
    ISysUserService sysUserService;

    @Resource
    IZhiboIdsService zhiboIdsService;

    @PostMapping("/login")
    public Result login(@RequestBody SysUser sysUser) {
        try {
            if (null == sysUser || StringUtils.isEmpty(sysUser.getUserName())) {
                return ResultGenerator.genFailResult("请输入用户名");
            }
            SysUser getSysUser = sysUserService.getOneByUserName(sysUser.getUserName());
            if (null == getSysUser) {
                return ResultGenerator.genFailResult("该用户还没注册");
            }

            if (getSysUser.getOnlineNum() + 1 > getSysUser.getLimitNum()) {
                return ResultGenerator.genFailResult("一个账户只能登陆一台机器");
            }
            Date nowDate = new Date();
            if (nowDate.getTime() > getSysUser.getExpireTime().getTime()) {
                return ResultGenerator.genFailResult("用户已过期，请联系管理员充值");
            }
            //判断是不是同一个deviceId
            String deviceIds = getSysUser.getDeviceIds();
            if (!deviceIds.contains(sysUser.getDeviceId())) {
                getSysUser.setDeviceIds(deviceIds + "," + sysUser.getDeviceId());
                getSysUser.setOnlineNum(getSysUser.getOnlineNum() + 1);
                sysUserService.updateById(getSysUser);
            }

            long time = getSysUser.getExpireTime().getTime();
            getSysUser.setExpires(time);
            return ResultGenerator.genSuccessResult(getSysUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }


    @PostMapping("/heartbeat")
    public Result heartbeat(@RequestBody SysUser sysUser) {
        SysUser getSysUser = sysUserService.getOneByUserName(sysUser.getUserName());
        if (null == getSysUser) {
            return ResultGenerator.genFailResult("该用户还没注册");
        }
        Date nowDate = new Date();
        if (nowDate.getTime() > getSysUser.getExpireTime().getTime()) {
            return ResultGenerator.genFailResult("用户已过期，请联系管理员充值");
        }

        long time = getSysUser.getExpireTime().getTime();
        getSysUser.setExpires(time);

        return ResultGenerator.genSuccessResult(getSysUser);
    }

    @GetMapping("/saveZbId")
    public boolean saveZbId(@RequestParam(value = "displayId") String displayId) {
        ZhiboIds zhiboIds = new ZhiboIds();
        zhiboIds.setDisplayId(displayId);
        boolean save = zhiboIdsService.save(zhiboIds);
        return save;
    }



    @PostMapping("/loginOut")
    public Result loginOut(@RequestBody SysUser sysUser) {
        if (null == sysUser || StringUtils.isEmpty(sysUser.getUserName()) || StringUtils.isEmpty(sysUser.getPassWord())) {
            return ResultGenerator.genFailResult("请输入用户名跟密码");
        }
        SysUser getSysUser = sysUserService.getOneByUserName(sysUser.getUserName());
        if (null == getSysUser) {
            return ResultGenerator.genFailResult("该用户还没注册");
        }

        String deviceIds = getSysUser.getDeviceIds();
        if (deviceIds.contains(sysUser.getDeviceId())) {
            deviceIds = deviceIds.replace(sysUser.getDeviceId(), "");
            int newNum = (getSysUser.getOnlineNum() - 1) > 0 ? getSysUser.getOnlineNum() - 1 : 0;
            getSysUser.setOnlineNum(newNum);
            getSysUser.setDeviceIds(deviceIds);
            sysUserService.updateById(getSysUser);
        }


        return ResultGenerator.genSuccessResult();
    }

}
