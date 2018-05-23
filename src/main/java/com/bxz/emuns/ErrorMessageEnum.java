package com.bxz.emuns;

/**
 * 返回的异常信息
 *
 * @author baixiangzhu
 * @date 2018/5/23
 **/
public enum  ErrorMessageEnum {

    SYSTEM_ERROR(500,"系统繁忙，请稍后重试。"),
    PARAM_ERROR(422,"参数校验错误"),
    USER_NOT_EXIST(404,"请求的用户不存在"),
    COINS_NOT_ENOUGH(400,"转账金币不足");

    private Integer code;
    private String message;

    private ErrorMessageEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
