package com.shiro.logical.sys.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author jianglanglang
 * @since 2021-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUser对象", description="系统用户表")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户名（真实姓名）")
    private String userName;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "身份证号码")
    private String idNumber;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "头像")
    private String headPortrait;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "是否禁用（0-否，1-是）")
    private String isForbidden;

    @ApiModelProperty(value = "最后登录的IP")
    private String loginIp;

    @ApiModelProperty(value = "最后登录的时间")
    private LocalDateTime loginData;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "删除标记 （0-正常，1-删除）")
    private String delFlag;


}
