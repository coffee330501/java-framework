package io.github.coffee330501.common.config;

import io.github.coffee330501.common.util.RedisUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RedisUtil.class})
public class AutoConfiguration {
}
