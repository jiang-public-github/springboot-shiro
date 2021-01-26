package com.shiro.logical.sys.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author jianglanglang
 * @since 2021-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysMenu对象", description="系统菜单表")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "父级点编号")
    private String parentId;

    @ApiModelProperty(value = "当前菜单的所有父节点编号")
    private String parentIds;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单链接地址")
    private String menuUrl;

    @ApiModelProperty(value = "权限标识")
    private String permission;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否显示（0-显示，1-隐藏）")
    private String isShow;

    @ApiModelProperty(value = "菜单备注")
    private String remarks;

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
