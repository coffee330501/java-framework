package io.github.coffee330501.testMybatisFlex;

import io.github.coffee330501.common.config.OssConfig;
import io.github.coffee330501.common.util.OssUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
public class TestOss {
    @Resource
    OssConfig ossConfig;
    @Test()
    void test() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("E:\\WorkSpace\\Coffee\\java-framework\\visual\\test-mybatis-flex\\src\\main\\resources\\application.yml");
        String filename = OssUtil.upload(inputStream, "yml/application.yml");
        System.out.println(filename);
    }
}
