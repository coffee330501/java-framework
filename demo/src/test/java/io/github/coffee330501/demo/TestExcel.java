package io.github.coffee330501.demo;

import com.mybatisflex.core.query.QueryWrapper;
import io.github.coffee330501.common.util.ExcelUtil;
import io.github.coffee330501.model.mapper.UserMapper;
import io.github.coffee330501.demo.domain.vo.UserExcelTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
public class TestExcel {
    @Resource()
    UserMapper userMapper;

    @Test
    void write() {
        ExcelUtil.write(UserExcelTemplate.class, (pageNumber, pageSize) -> userMapper.paginate(pageNumber, pageSize, new QueryWrapper()));
    }

    @Test
    void writeToOss() throws IOException {
        ExcelUtil.writeToOss("test",".xlsx",UserExcelTemplate.class, (pageNumber, pageSize) -> userMapper.paginate(pageNumber, pageSize, new QueryWrapper()));
    }
}
