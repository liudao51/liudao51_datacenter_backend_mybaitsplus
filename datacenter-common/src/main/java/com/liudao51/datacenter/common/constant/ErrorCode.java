package com.liudao51.datacenter.common.constant;

/**
 * 错误码列表
 */
public enum ErrorCode {

    /*
     * 格式为："功能_具体错误_ERROR", 如：REQUEST_SIGN_ERROR, USER_NOT_AUTHORIZE_ERROR
     *
     * 0 - 成功,
     * 1xxxx - 系统相关,
     * 20xxx - 请求相关,
     * 21xxx - 用户相关,
     * 22xxx - 订单相关,
     */
    SYSTEM_UNKNOWN_ERROR(10001, "系统异常"),
    SYSTEM_TIMEOUT_ERROR(10002, "系统超时"),

    REQUEST_SIGN_ERROR(20001, "签名错误"),
    REQUEST_SIGN_TYPE_ERROR(20002, "签名类型错误"),
    REQUEST_APP_ID_ERROR(20003, "APP_ID错误"),
    REQUEST_APP_SECRET_ERROR(20004, "APP_SECRET错误"),

    USER_USERNAME_PASSWORD_ERROR(21001, "用户名或密码错误"),
    USER_ALREADY_EXITS_ERROR(21002, "用户已存在"),
    USER_NOT_AUTHORIZE_ERROR(21003, "没有此操作权限"),

    ORDER_ORDER_SN_ERROR(22001, "订单号错误"),
    ORDER_REPEAT_PAY_ERROR(22002, "订单重复支付"),

    STREAM_FILE_FORMAT_ERROR(23001, "文件格式错误"),

    SUCCESS(0, "success");


    private final int code;
    private final String value;

    ErrorCode(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String toValue() {
        return this.value;
    }

    public int toCode() {
        return this.code;
    }
}
