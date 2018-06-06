package com.highcharts.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;

/**
 * <p>Spring-Boot-Demo/cn.mrdear.conf</p>
 * web配置
 *
 * @author Created by BruceZheng
 * @date 2018-01-22 14:19
 **/
@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public WebConfig() {
        super();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
        registry.addResourceHandler("/mapper/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/mapper/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截规则：除了login，其他都拦截判断
        // registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/adminUser/login","/hello02");
        registry.addInterceptor(new MyInterceptor());
        super.addInterceptors(registry);
    }

    /**
     * 以前要访问一个页面需要先创建个Controller控制类，再写方法跳转到页面
     * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.jsp页面了
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toLogin").setViewName("login");
        super.addViewControllers(registry);
/*默认首页？？
        registry.addViewController( "/" ).setViewName( "forward:/hello.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );*/
    }
}
