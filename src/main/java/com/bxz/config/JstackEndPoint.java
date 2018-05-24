package com.bxz.config;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baixiangzhu
 * @date 2018/5/24
 **/
@ConfigurationProperties(prefix="endpoints.jstack")
public class JstackEndPoint extends AbstractEndpoint<Map<String, Object>> {

    public JstackEndPoint(){
        super("jstack");
    }
    @Override
    public Map<String, Object> invoke() {
        Map<String,Object> result= new HashMap<>();


        System.out.println("=====================通过java来获取相关系统状态====================");
        long i = Runtime.getRuntime().totalMemory()/1024/1024;//Java 虚拟机中的内存总量，以字节为单位
        long j = Runtime.getRuntime().freeMemory()/1024/1024;//Java 虚拟机中的空闲内存量
        long k = Runtime.getRuntime().maxMemory()/1024/1024;

        result.put("总内存",i + "Mb");
        result.put("空闲内存",j + "Mb");
        result.put("最大可用内存",k + "Mb");

        System.out.println("=======================ThreadMXBean============================ ");
        ThreadMXBean tm=(ThreadMXBean) ManagementFactory.getThreadMXBean();
        result.put("当前线程数",tm.getThreadCount());
        result.put("线程峰值数",tm.getPeakThreadCount());
        result.put("当前线程使用cpu时间",tm.getCurrentThreadCpuTime());
        result.put("当前守护线程数",tm.getDaemonThreadCount());
        result.put("当前线程使用时间",tm.getCurrentThreadUserTime());

        return result;
    }

}
