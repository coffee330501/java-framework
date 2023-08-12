package io.github.coffee330501.testMybatisFlex.config.mybatis;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.mybatisflex.core.keygen.IKeyGenerator;

public class SnowflakeKeyGenerator implements IKeyGenerator {
    Snowflake snowflake = IdUtil.getSnowflake(1, 1);

    @Override
    public Object generate(Object o, String s) {
        return snowflake.nextIdStr();
    }
}
