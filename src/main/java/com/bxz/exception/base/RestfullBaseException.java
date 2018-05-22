package com.bxz.exception.base;

import java.io.Serializable;

/**
 * RestFull异常父类
 *
 * @author baixiangzhu
 * @date 2018/5/22
 **/
public class RestfullBaseException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 5589282350569379052L;

    RestfullBaseException(String message) {
        super(message);
    }

}
