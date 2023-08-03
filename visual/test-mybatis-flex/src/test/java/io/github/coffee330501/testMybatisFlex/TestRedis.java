package io.github.coffee330501.testMybatisFlex;

import com.alibaba.fastjson2.JSONObject;
import io.github.coffee330501.common.util.RedisUtil;
import io.github.coffee330501.testMybatisFlex.entity.User;
import io.github.coffee330501.testMybatisFlex.enums.UserTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class TestRedis {
    @Test
    void setGet() {
        User user = new User();
        user.setType(UserTypeEnum.Recovery);
        user.setAge(11);
        user.setName("王五");

        String key = "coffee:user";
        RedisUtil.set(key, user);
        String userStr = RedisUtil.get(key);
        User userParsed = JSONObject.parseObject(userStr, User.class);

        assert userParsed.getType().equals(UserTypeEnum.Recovery);
    }

    @Test
    void setWithExpire() throws InterruptedException {
        String key = "coffee:userExp";
        RedisUtil.set(key, "赵六", 4, TimeUnit.SECONDS);

        String v0 = RedisUtil.get(key);
        assert v0 != null;

        Thread.sleep(4000);
        String v1 = RedisUtil.get(key);
        assert v1 == null;
    }

    @Test
    void setIfAbsent() {
        String key = "coffee:user";
        boolean b0 = RedisUtil.setIfAbsent(key, new User());
        boolean b1 = RedisUtil.setIfAbsent(key + "1", new User());

        assert !b0;
        assert b1;
    }
}
