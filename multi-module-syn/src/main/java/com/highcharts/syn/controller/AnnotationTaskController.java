package com.highcharts.syn.controller;

import com.highcharts.common.base.BaseController;
import com.highcharts.syn.task.SynTestAnnotation;
import com.highcharts.syn.task.SynTestTaskForkAndJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ForkJoinPool;

/**
 * @program: Spring-Boot-Multi
 * @description:
 * @author: Brucezheng
 * @create: 2018-06-07 15:43
 **/
@RestController
/**
 *controller上加上@EnableAsync
 */
@EnableAsync
public class AnnotationTaskController extends BaseController {
    @Autowired
    private SynTestAnnotation synTestAnnotation;

    @Autowired
    private SynTestTaskForkAndJoin synTestTaskForkAndJoin;

    /**
     * 注解方式
     */
    @RequestMapping(value = "/syn/annotation.do")
    public void synTaskAnnotation() {
        try {
            synTestAnnotation.doTaskOne(1, 10);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * JDK1.7 自带ForkAndJoin
     */
    @RequestMapping(value = "/syn/fork.do", produces = JSON_UTF8)
    public String synTaskForkJoin() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(synTestTaskForkAndJoin);
        forkJoinPool.shutdown();
        return success();
    }

    /**
     * jdk1.8 CompletableFuture
     */
    public void synCompletableFuture(){

    }
}
