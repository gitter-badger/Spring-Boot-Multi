package com.highcharts.syn.task;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.RecursiveTask;

/**
 * @program: Spring-Boot-Multi
 * @description:
 * @author: Brucezheng
 * @create: 2018-06-07 16:05
 **/
@Component
public class SynTestTaskForkAndJoin extends RecursiveTask<Integer> {
    private static Random random = new Random();

    @Override
    protected Integer compute() {
        System.out.println("开始做任务forkAndJoin");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("完成任务forkAndJoin，耗时：" + (end - start) + "毫秒");
        return null;
    }
}
