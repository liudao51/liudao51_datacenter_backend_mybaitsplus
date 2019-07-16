package com.liudao51.datacenter.core.protocol.sys_user;

import com.liudao51.datacenter.core.protocol.ParamValidInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.*;

@Data
@ApiModel(value = "用户分页查询")
public class ListSysUserReq implements ParamValidInterface {

    @NotNull(message = "页码不能为空")
    @NotBlank(message = "页码不能为空")
    @Digits(message = "页码格式不正确")
    @ApiModelProperty(value = "页码", example = "1", required = true)
    private Integer pageNo;

    @NotNull(message = "页数不能为空")
    @NotBlank(message = "页数不能为空")
    @Digits(message = "页数格式不正确")
    @ApiModelProperty(value = "页数", example = "10", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "用户名", example = "zhangshan")
    private String userName;

    @ApiModelProperty(value = "真实姓名", example = "张三")
    private String realName;

    @Digits(message = "手机格式不正确")
    @Length(message = "手机长度不正确")
    @ApiModelProperty(value = "手机", example = "13700000001")
    private String mobile;

    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "邮箱", example = "123@qq.com")
    private String email;


}