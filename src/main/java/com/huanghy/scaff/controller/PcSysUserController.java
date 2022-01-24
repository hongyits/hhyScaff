package com.huanghy.scaff.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.huanghy.scaff.domain.PcSysUser;
import com.huanghy.scaff.domain.SysUser;
import com.huanghy.scaff.dto.Result;
import com.huanghy.scaff.dto.ResultGenerator;
import com.huanghy.scaff.service.IPcSysUserService;
import com.huanghy.scaff.utils.AesUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author huangHy
 * @description: PC端脚本用户入口
 * @date 2022/1/3 10:28
 */
@RestController()
@RequestMapping("/pc")
public class PcSysUserController {

    @Resource
    IPcSysUserService sysUserService;

    @GetMapping("/login")
    public Result login(HttpServletRequest req, @RequestBody SysUser sysUser) {
        try {
            String key = req.getHeader("key");
            sysUser.setUserName(AesUtil.desStr(key, sysUser.getUserName()));
            sysUser.setPassWord(AesUtil.desStr(key, sysUser.getPassWord()));
            PcSysUser getSysUser = sysUserService.getOneByUserName(sysUser.getUserName());
            if (null == getSysUser) {
                return ResultGenerator.genFailResult("该用户还没注册");
            }

            if (getSysUser.getOnlineNum() + 1 > getSysUser.getLimitNum()) {
                return ResultGenerator.genFailResult("已到达限定机器数，当前账号数量数是" + getSysUser.getOnlineNum());
            }
            if (new Date().getTime() > getSysUser.getExpireTime().getTime()) {
                return ResultGenerator.genFailResult("用户已过期，请联系管理员");
            }
            //判断是不是同一个deviceId
            String deviceIds = getSysUser.getDeviceIds();
            if (StringUtils.isEmpty(deviceIds)) {
                getSysUser.setDeviceIds(sysUser.getDeviceId());
                getSysUser.setOnlineNum(getSysUser.getOnlineNum() + 1);
                sysUserService.updateById(getSysUser);
            } else {
                if (!deviceIds.contains(sysUser.getDeviceId())) {
                    getSysUser.setOnlineNum(getSysUser.getOnlineNum() + 1);
                    sysUserService.updateById(getSysUser);
                }
            }

            return ResultGenerator.genSuccessResult(getSysUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }
}
