package com.huanghy.scaff.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUser extends Model {

    private static final long serialVersionUID = 1L;

    private Long id;

    @TableField("userName")
    private String userName;

    @TableField("passWord")
    private String passWord;

    @TableField("deviceIds")
    private String deviceIds;

    @TableField("limitNum")
    private Integer limitNum;

    @TableField("onlineNum")
    private Integer onlineNum;

    @TableField("crtTime")
    private Date crtTime;

    @TableField("buyTime")
    private Date buyTime;

    @TableField("expireTime")
    private Date expireTime;

    @TableField(exist = false)
    private String deviceId;

    @TableField(exist = false)
    private long expires;




}
