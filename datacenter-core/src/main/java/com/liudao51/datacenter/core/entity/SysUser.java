package com.liudao51.datacenter.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户实体
 */

@ApiModel("用户对象")
@TableName("dc_sys_user")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", example = "1", required = true)
    @TableField("id")
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", example = "admin", required = true)
    @TableField("user_name")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", example = "123456", required = true)
    @TableField("password")
    private String password;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名", example = "刘言", required = true)
    @TableField("real_name")
    private String realName;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", example = "13760050300", required = true)
    @TableField("mobile")
    private String mobile;
    /**
     * 邮箱地址
     */
    @ApiModelProperty(value = "邮箱地址", example = "liu@qq.com", required = true)
    @TableField("email")
    private String email;
    /**
     * 所属部门id
     */
    @ApiModelProperty(value = "所属部门id", example = "100", required = true)
    @TableField("department_id")
    private Long departmentId;
    /**
     * 所属部门名称
     */
    @ApiModelProperty(value = "所属部门名称", example = "技术部", required = true)
    @TableField("department_name")
    private String departmentName;
    /**
     * 用户状态（0-正常,1-未激活,2-禁用,3-冻结）
     */
    @ApiModelProperty(value = "用户状态（0-正常,1-未激活,2-禁用,3-冻结）", example = "0", required = true)
    @TableField("status")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", example = "软件开发工程师", required = true)
    @TableField("remark")
    private String remark;
    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间", example = "1565761054895", required = true)
    @TableField("last_login_time")
    private Long lastLoginTime;
    /**
     * 最后登录IP
     */
    @ApiModelProperty(value = "最后登录IP", example = "127.0.0.1", required = true)
    @TableField("last_login_ip")
    private String lastLoginIp;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", example = "admin", required = true)
    @TableField("created_by")
    private String createdBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", example = "1565761054895", required = true)
    @TableField("created_time")
    private Long createdTime;
    /**
     * 最后修改人
     */
    @ApiModelProperty(value = "最后修改人", example = "admin", required = true)
    @TableField("updated_by")
    private String updatedBy;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间", example = "1565761054895", required = true)
    @TableField("updated_time")
    private Long updatedTime;
    /**
     * 删除标识（0-未删除,1-已删除）
     */
    @ApiModelProperty(value = "删除标识（0-未删除,1-已删除）", example = "0", required = true)
    @TableField("deleted")
    private Integer deleted;
    /**
     * 删除人
     */
    @ApiModelProperty(value = "删除人", example = "admin", required = true)
    @TableField("deleted_by")
    private String deletedBy;
    /**
     * 删除时间
     */
    @ApiModelProperty(value = "删除时间", example = "1565761054895", required = true)
    @TableField("deleted_time")
    private Long deletedTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
