package com.bxz.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 日志切面
 *
 * @author baixiangzhu
 * @date 2018/5/22
 **/
@Slf4j
@Component
@Aspect
public class LogAspect {


    /**
     * 打印日志
     * @param pjp
     * @param aroundLogging
     * @return
     * @throws Throwable
     */
    @Around("@annotation(aroundLogging)")
    public Object outputLogAndTimeCast(final ProceedingJoinPoint pjp, AroundLogging aroundLogging) throws Throwable {
        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        String declaringType = methodSignature.getDeclaringType().getSimpleName();

        HashMap<String, Object> paramsMap = generateParamsMap(pjp);
        long start = System.currentTimeMillis();
        log.info("[{}.{}], params:[{}]", declaringType, methodSignature.getMethod().getName(), paramsMap);

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            long end = System.currentTimeMillis();
            log.info("[{}.{}], return:[{}], time cast:[{}]ms", declaringType, methodSignature.getMethod().getName(),
                    result, end - start);
        }
        return result;
    }

    /**
     * 构造入参map
     * @param pjp
     * @return
     */
    private HashMap<String, Object> generateParamsMap(ProceedingJoinPoint pjp) {
        Object[] paramsValue = pjp.getArgs();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        String[] paramsName = methodSignature.getParameterNames();

        HashMap<String, Object> paramsMap = new HashMap<>();
        for (int i = 0; i < paramsName.length; i++) {
            //可以做一些敏感过滤
            if (paramsName[i].toLowerCase().contains("password")) {
                continue;
            }
            paramsMap.put(paramsName[i], paramsValue[i]);
        }
        return paramsMap;
    }

}
