package io.github.coffee330501.testMybatisFlex.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("io.github.coffee330501.testMybatisFlex.mapper")
public class MybatisConfig {
}
