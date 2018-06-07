package com.highcharts.syn.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @program: Spring-Boot-Multi
 * @description: Spring 框架注解方式 异步1.在需要异步方式方法上 @Async 注解 ，在需要调用的类上加入 @EnableAsync
 * 不能在本类中执行异步方法
 * @author: Brucezheng
 * @create: 2018-06-07 15:40
 **/
@Component
public class SynTestAnnotation {
    private static Random random = new Random();

    @Async
    public Future<String> doTaskOne(int a, int b) throws Exception {
        System.out.println("开始做任务Spring-Anootation");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务Spring-Anootation，耗时：" + (end - start) + "毫秒");
        //返回值
        return new AsyncResult<>("任务完成");
    }
}
