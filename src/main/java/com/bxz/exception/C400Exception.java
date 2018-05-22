package com.bxz.exception;

import com.bxz.exception.base.RESTfull4xxBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 400异常
 *
 * @author baixiangzhu
 * @date 2018/5/22
 **/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class C400Exception extends RESTfull4xxBaseException {

    public C400Exception(String message) {
        super(message);
    }

}
