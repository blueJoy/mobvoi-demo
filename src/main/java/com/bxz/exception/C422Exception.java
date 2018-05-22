package com.bxz.exception;

import com.bxz.exception.base.RESTfull4xxBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author baixiangzhu
 * @date 2018/5/22
 **/
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class C422Exception extends RESTfull4xxBaseException {
    public C422Exception(String message) {
        super(message);
    }
}
