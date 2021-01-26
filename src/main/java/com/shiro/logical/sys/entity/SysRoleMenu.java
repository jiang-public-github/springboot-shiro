package com.shiro.logical.sys.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统-角色-菜单表
 * </p>
 *
 * @author jianglanglang
 * @since 2021-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRoleMenu对象", description="系统-角色-菜单表")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "系统角色编号（对应sys_role - id）")
    private String roleId;

    @ApiModelProperty(value = "系统菜单编号（对应sys_menu - id）")
    private String menuId;

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
