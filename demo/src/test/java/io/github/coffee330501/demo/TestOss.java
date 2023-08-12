package io.github.coffee330501.demo;

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
    void upload0() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("E:\\Download\\test.txt");
        String filename = OssUtil.upload(inputStream, "test/test.txt");
        System.out.println(filename);
    }

    @Test()
    void upload1() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("E:\\Download\\test.txt");
        String filename = OssUtil.upload(inputStream,"/test/b", "test.txt");
        System.out.println(filename);
    }
}
