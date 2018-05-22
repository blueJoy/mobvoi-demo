package com.bxz.vo.request;

import lombok.Data;

/**
 * 基本返回格式
 * @author baixiangzhu
 * @date 2018/5/22
 **/
@Data
public class ResponseBaseVO<T> {
    private String message;
    private T data;

    public ResponseBaseVO() {
    }

    public ResponseBaseVO(T data) {
        this.data = data;
    }

    public ResponseBaseVO(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
