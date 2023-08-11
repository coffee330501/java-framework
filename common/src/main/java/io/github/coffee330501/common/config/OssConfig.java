package io.github.coffee330501.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data()
@ConfigurationProperties("aliyun.oss")
public class OssConfig {
    private String endpoint;
    private String bucket;
    private String accessKeyId;
    private String accessKeySecret;
}
