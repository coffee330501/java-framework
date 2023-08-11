package io.github.coffee330501.testMybatisFlex;

import io.github.coffee330501.common.util.ThreadLocalUtil;
import io.github.coffee330501.testMybatisFlex.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestThreadLocalUtil {
    @Test
    void test() throws InterruptedException {
        User user = new User();
        user.setId("abc");
        ThreadLocalUtil.set("user", user);
        User u = (User) ThreadLocalUtil.get("user");
        assert "abc".equals(u.getId());

        new Thread(() -> {
            Object o = ThreadLocalUtil.get("user");
            assert o == null;
        }).start();
        Thread.sleep(1000);

        ThreadLocalUtil.clear();
        Object o = ThreadLocalUtil.get("user");
        assert o == null;


    }
}
