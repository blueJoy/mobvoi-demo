package com.bxz.exception;

import com.bxz.exception.base.RESTfull4xxBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 404异常
 *
 * @author baixiangzhu
 * @date 2018/5/22
 **/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class C404Exception extends RESTfull4xxBaseException {

    public C404Exception(String message) {
        super(message);
    }

}
