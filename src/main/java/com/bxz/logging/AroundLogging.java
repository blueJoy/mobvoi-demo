package com.bxz.logging;

import java.lang.annotation.*;

/**
 * 日志注解 用于方法，打印切入点[类.方法], 参数，耗时
 *
 * Created by baixiangzhu on 2018/5/22.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AroundLogging {


}
