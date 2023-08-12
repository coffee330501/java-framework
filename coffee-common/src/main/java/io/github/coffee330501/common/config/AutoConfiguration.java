package io.github.coffee330501.common.config;

import io.github.coffee330501.common.util.ExcelUtil;
import io.github.coffee330501.common.util.OssUtil;
import io.github.coffee330501.common.util.RedisUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@Import({RedisUtil.class, OssConfig.class, OssUtil.class, ExcelUtil.class})
@EnableConfigurationProperties
public class AutoConfiguration {
}
