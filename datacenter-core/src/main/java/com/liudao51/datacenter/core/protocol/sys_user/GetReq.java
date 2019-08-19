package com.liudao51.datacenter.core.protocol.sys_user;

import com.liudao51.datacenter.core.protocol.ParamValidInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.*;

@Data
@ApiModel(value = "查询用户对象")
public class GetReq implements ParamValidInterface {

    @ApiModelProperty(value = "用户名", example = "", required = false)
    private String userName;

    @ApiModelProperty(value = "真实姓名", example = "", required = false)
    private String realName;

    @Digits(message = "手机格式不正确")
    @Length(min = 10, max = 13, message = "手机长度不正确")
    @ApiModelProperty(value = "手机", example = "", required = false)
    private String mobile;

    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "邮箱", example = "", required = false)
    private String email;
}
