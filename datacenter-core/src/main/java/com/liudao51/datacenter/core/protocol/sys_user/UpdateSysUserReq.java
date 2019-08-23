package com.liudao51.datacenter.core.protocol.sys_user;

import com.liudao51.datacenter.core.protocol.ParamValidInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

@ApiModel(value = "UpdateSysUserReq")
@Data
public class UpdateSysUserReq implements ParamValidInterface {

    @NotNull(message = "用户id不能为空")
    @NotBlank(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id", example = "admin", required = true)
    private Long id;

    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", example = "admin", required = true)
    private String userName;

    @NotNull(message = "密码不能为空")
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", example = "123456", required = true)
    private String password;

    @ApiModelProperty(value = "真实姓名", example = "刘言", required = false)
    private String realName;

    @ApiModelProperty(value = "手机号码", example = "13760050300", required = false)
    private String mobile;

    @ApiModelProperty(value = "邮箱地址", example = "liu@qq.com", required = false)
    private String email;

    @ApiModelProperty(value = "所属部门id", example = "100", required = false)
    private Long departmentId;

    @ApiModelProperty(value = "所属部门名称", example = "技术部", required = false)
    private String departmentName;

    @ApiModelProperty(value = "用户状态（0-正常,1-未激活,2-禁用,3-冻结）", example = "0", required = false)
    private Integer status;

    @ApiModelProperty(value = "备注", example = "软件开发工程师", required = false)
    private String remark;
}
