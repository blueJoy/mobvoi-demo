package com.bxz.interceptor;

import com.bxz.exception.base.RESTfull4xxBaseException;
import com.bxz.exception.base.RestfullBaseException;
import com.bxz.vo.request.ResponseBaseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baixiangzhu
 * @date 2018/5/22
 **/
@Slf4j
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    /** 默认空对象*/
    private static final Map EmptyMap = new HashMap();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ResponseBaseVO responseModel = new ResponseBaseVO(EmptyMap);
        if(obj instanceof ResponseBaseVO) {
            return (ResponseBaseVO)obj;
        } else {
            if(null != obj) {
                responseModel.setData(obj);
            }

            return responseModel;
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseBaseVO<Object> handleUnexpectedServerError(Exception exception, HttpServletResponse response){

        ResponseBaseVO responseBaseVO = new ResponseBaseVO(EmptyMap);

        if(exception instanceof RestfullBaseException) {
            RestfullBaseException bce = (RestfullBaseException)exception;
            responseBaseVO.setMessage(bce.getMessage());
            response.setStatus(this.getCode(exception));
            if(exception instanceof RESTfull4xxBaseException) {
                log.warn("fexception:4xx异常警", bce);
            } else {
                log.error("exception:RESTfullBaseException", exception);
            }
        } else {
            log.error("exception:500Exception ", exception);
            responseBaseVO.setMessage("系统繁忙，请稍后重试。");
            response.setStatus(500);
        }

        return responseBaseVO;
    }

    private int getCode(Object obj) {
        return Integer.parseInt(obj.getClass().getSimpleName().substring(1, 4));
    }
}
