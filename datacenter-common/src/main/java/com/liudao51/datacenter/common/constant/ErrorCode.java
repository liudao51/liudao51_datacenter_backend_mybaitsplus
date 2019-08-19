package com.liudao51.datacenter.common.constant;

/**
 * 错误码列表
 */
public enum ErrorCode {

    /*
     * 格式为："功能_具体错误_ERROR", 如：REQUEST_SIGN_ERROR, USER_NOT_AUTHORIZE_ERROR
     *
     * 长度为：大于5位整数。
     * -1 - 未定义,
     * 0 - 成功,
     * 1xxxx - 系统相关,
     * 20xxx - 请求相关,
     * 21xxx - 用户相关,
     * 22xxx - 订单相关,
     */
    SYSTEM_UNKNOWN_ERROR(10001, "系统繁忙"),
    SYSTEM_TIMEOUT_ERROR(10002, "系统超时"),

    REQUEST_SIGN_ERROR(20001, "签名错误"),
    REQUEST_SIGN_TYPE_ERROR(20002, "签名类型错误"),
    REQUEST_APP_ID_ERROR(20003, "APP_ID错误"),
    REQUEST_APP_SECRET_ERROR(20004, "APP_SECRET错误"),
    REQUEST_PARAM_ERROR(20005, "请求参数错误"),
    REQUEST_NEED_IMPLEMENTS_PARAMVALIDINTERFACE_ERROR(20006, "请求参数对象必须实现要ParamValidInterface接口"),

    USER_NOT_AUTHORIZE_ERROR(21001, "没有此操作权限"),
    USER_NOT_LOGIN_ERROR(21002, "请先登录"),
    USER_ALREADY_EXITS_ERROR(21003, "用户已存在"),
    USER_NOT_EXITS_ERROR(21004, "用户不存在"),
    USER_USERNAME_PASSWORD_ERROR(21005, "用户名或密码错误"),
    USER_PASSWORD_ERROR(21006, "密码错误"),
    USER_NAME_FORMAT_ERROR(21007, "用户名格式错误"),
    USER_PASSWORD_FORMAT_ERROR(21008, "密码格式错误"),
    USER_UNACTIVATED_ERROR(21009, "用户未激活"),
    USER_FORBIDDEN_ERROR(21010, "用户已禁用"),
    USER_FREEZE_ERROR(21011, "用户已冻结"),
    USER_ADD_ERROR(21012, "用户新增失败"),
    USER_UPDATE_ERROR(21013, "用户更新失败"),
    USER_DELETE_ERROR(21014, "用户删除失败"),

    ORDER_ORDER_SN_ERROR(22001, "订单号错误"),
    ORDER_REPEAT_PAY_ERROR(22002, "订单重复支付"),

    STREAM_FILE_FORMAT_ERROR(23001, "文件格式错误"),

    UNDEFINED(-1, "未定义"), SUCCESS(0, "success");

    private final Integer code;
    private final String value;

    ErrorCode(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String toValue() {
        return this.value;
    }

    public Integer toCode() {
        return this.code;
    }
}
