package io.github.coffee330501.demo.config.mybatis;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageHelperConfig {
    @Bean
    public PageInterceptor pageInterceptor(){
        return new PageInterceptor();
    }
}
