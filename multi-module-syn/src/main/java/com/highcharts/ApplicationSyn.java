package com.highcharts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 默认启动类
 */

@SpringBootApplication
public class ApplicationSyn extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationSyn.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSyn.class, args);
    }
}
