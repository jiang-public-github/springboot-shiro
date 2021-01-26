package com.shiro.logical.sys.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author jianglanglang
 * @since 2021-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRole对象", description="系统角色表")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色英文名")
    private String englishName;

    @ApiModelProperty(value = "是否禁用（0-未禁用，1-已禁用）")
    private String isForbidden;

    @ApiModelProperty(value = "应用系统")
    private String system;

    @ApiModelProperty(value = "角色备注")
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
