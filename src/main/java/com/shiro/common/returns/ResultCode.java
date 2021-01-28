package com.shiro.common.returns;

/**
 * @Author jianglanglang
 * @Date 2020/11/8 13:52
 * @Description 接口返回状态码和返回值
 */
public enum ResultCode {
    /* 1000 - 1999 系统基础业务状态码 */
    SUCCESS(1000, "成功!"),
    FAILURE(1001, "失败!"),
    NO_DATA(1002, "暂无数据!"),
    DATA_MISTAKE(1003, "数据有误!"),
    NO_DATA_EXIST(1004, "数据不存在!"),
    DATA_ALREADY_EXISTS(1005, "数据已存在!"),
    DATA_BE_BOUND(1006, "数据已被绑定!"),
    PROGRAM_EXCEPTION(1007, "程序异常!"),
    SYSTEM_MAINTAIN(1008, "系统维护中!"),
    SYSTEM_EXPLOIT(1009, "系统开发中!"),
    SYSTEM_INNER_ERROR(1010, "系统繁忙，请稍后重试!"),


    /* 2000 - 2999 参数错误状态码 */
    PARAM_IS_INVALID(2000, "参数无效!"),
    PARAM_IS_BLANK(2001, "参数为空!"),
    PARAM_TYPE_BIND_ERROR(2003, "参数类型错误!"),
    PARAM_NOT_COMPLETE(2004, "参数缺失!"),
    PARAM_NUMBERS_TOO_MUCH(2005, "参数不合法!"),

    /* 3000 - 3999 关于用户的一些状态码 */
    VERIFICATION_CODE_ERROR(3000, "验证码错误!"),
    VERIFICATION_CODE_FAILURE(3001, "验证码失效!"),
    USER_NOT_LOGGED_IN(3002, "用户未登录!"),
    USER_LOGIN_ERROR(3003, "账号不存在或密码错误!"),
    USER_LOGIN_SUCCESS(3004, "登录成功!"),
    USER_ACCOUNT_FORBIDDEN(3005, "账号已被禁用!"),
    USER_LOGOUT_SUCCESS(3006, "退出成功!"),
    USER_NOT_EXIST(3007, "用户不存在!"),
    USER_HAS_EXISTED(3008, "用户已存在!"),
    TOKEN_ERROR(3009, "Token未授权或已失效!"),
    SHIRO_AUTHENTICATION_FAILURE(3010, "认证失败!"),
    SHIRO_UNAUTHORIZED(3011, "未授权，无此操作权限!"),
    SHIRO_ACCESS_AUTHORITY(3012, "无访问权限!"),

    /* 4000 - 4999 app 或 pc的一些状态码 */
    SIGN_INVALID(4000, "签名无效!"),
    SECRET_KEY_ERROR(4001, "秘钥错误!"),

    /* 5000 - 5999 接口的一些状态码 */
    INTERFACE_FORBID_VISIT(5000, "接口禁止访问!"),
    INTERFACE_ADDRESS_INVALID(5001, "接口地址无效!"),
    INTERFACE_REQUEST_TIMEOUT(5002, "接口请求超时!"),
    INTERFACE_EXCEED_LOAD(5003, "接口负载过高!"),
    INTERFACE__CALL_OVERTIME(5004, "接口调用超过限额!"),
    INTERFACE_INNER_INVOKE_ERROR(5005, "内部系统接口调用异常!"),
    INTERFACE_OUTTER_INVOKE_ERROR(5006, "外部系统接口调用异常!"),

    /* 6000 - 6999 一些散态的状态码 */
    ILLEGALITY_SQL(6000, "非法SQL!"),
    SENSITIVE_KEYWORD_SQL(6001, "SQL中含有敏感关键字!"),
    INEXISTENCE_SQL(6003, "未读取到SQL模板数据!"),

    /* 7000 - 7999 shiro */


    SUPER_AUTHORITY(0000, "这是超级权限!");


    private Integer code;

    private String message;

    public Integer getCode() { return code; }

    public void setCode(Integer code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
