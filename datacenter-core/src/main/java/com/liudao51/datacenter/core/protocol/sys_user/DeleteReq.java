package com.liudao51.datacenter.core.protocol.sys_user;

import com.liudao51.datacenter.core.protocol.ParamValidInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.*;

@Data
@ApiModel(value = "用户删除")
public class DeleteReq implements ParamValidInterface {

    @NotNull(message = "用户id不能为空")
    @NotBlank(message = "用户id不能为空")
    @Digits(message = "用户id格式不正确")
    @ApiModelProperty(value = "用户id", example = "1", required = true)
    private Long id;
}
