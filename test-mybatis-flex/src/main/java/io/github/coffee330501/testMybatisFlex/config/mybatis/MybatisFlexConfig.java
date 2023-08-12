package io.github.coffee330501.testMybatisFlex.config.mybatis;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.logicdelete.impl.DateTimeLogicDeleteProcessor;
import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisFlexConfig implements MyBatisFlexCustomizer, ConfigurationCustomizer {
    private static final Logger logger = LoggerFactory.getLogger("mybatis-flex-sql");

    @Override
    public void customize(FlexGlobalConfig flexGlobalConfig) {
        // 开启审计功能
        AuditManager.setAuditEnable(true);
        // 设置 SQL 审计收集器(mybatis-flex内置方案)
        AuditManager.setMessageCollector(auditMessage ->
                logger.info("\nSQL日志:----------->\nsql:    {}\nspend:   {}ms", auditMessage.getFullSql(), auditMessage.getElapsedTime())
        );
        // 注册雪花算法ID生成策略
        KeyGeneratorFactory.register("SnowflakeId", new SnowflakeKeyGenerator());
        // 逻辑删除处理器
        LogicDeleteManager.setProcessor(new DateTimeLogicDeleteProcessor());
    }

    @Override
    public void customize(FlexConfiguration flexConfiguration) {
        // SQL打印(mybatis方案)
//        flexConfiguration.setLogImpl(StdOutImpl.class);
    }
}
