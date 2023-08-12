package io.github.coffee330501.demo.config.mybatis;

import com.mybatisflex.core.FlexGlobalConfig;
import io.github.coffee330501.model.domain.entity.User;
import io.github.coffee330501.demo.domain.listener.UserListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisFlexListenerConfig {
    public MybatisFlexListenerConfig() {
        // 注册监听器
        FlexGlobalConfig config = FlexGlobalConfig.getDefaultConfig();
        config.registerSetListener(new UserListener(), User.class);
    }
}
