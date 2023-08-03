package io.github.coffee330501.testMybatisFlex.config.mybatis;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageHelperConfig {
    //使用自动配置无效，
    @Bean
    public PageInterceptor pageInterceptor(){
        return new PageInterceptor();
    }
}
