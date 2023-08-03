package io.github.coffee330501.testMybatisFlex.config.mybatis;

import com.mybatisflex.core.FlexGlobalConfig;
import io.github.coffee330501.testMybatisFlex.entity.User;
import io.github.coffee330501.testMybatisFlex.entity.listener.UserListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisFlexListenerConfig {
    public MybatisFlexListenerConfig() {
        // 注册监听器
        FlexGlobalConfig config = FlexGlobalConfig.getDefaultConfig();
        config.registerSetListener(new UserListener(), User.class);
    }
}
