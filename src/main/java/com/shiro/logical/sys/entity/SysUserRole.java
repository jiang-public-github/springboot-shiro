package com.shiro.logical.sys.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统-用户-角色表
 * </p>
 *
 * @author jianglanglang
 * @since 2021-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUserRole对象", description="系统-用户-角色表")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "系统用户编号（对应sys_user - id）")
    private String userId;

    @ApiModelProperty(value = "系统角色编号（对应sys_role - id）")
    private String roleId;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "删除标记（0-正常，1-删除）")
    private String delFlag;


}
