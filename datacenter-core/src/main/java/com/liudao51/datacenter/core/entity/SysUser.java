package com.liudao51.datacenter.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dc_sys_user")
@ToString
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 手机号码
     */
    @TableField("mobile")
    private String mobile;
    /**
     * 邮箱地址
     */
    @TableField("email")
    private String email;
    /**
     * 所属部门id
     */
    @TableField("department_id")
    private Long departmentId;
    /**
     * 所属部门名称
     */
    @TableField("department_name")
    private String departmentName;
    /**
     * 用户状态（0：可用；1：不可用）
     */
    @TableField("user_status")
    private Integer userStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;
    /**
     * 最后修改人
     */
    @TableField("last_modified_by")
    private String lastModifiedBy;
    /**
     * 最后修改时间
     */
    @TableField("last_modified_time")
    private Date lastModifiedTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
