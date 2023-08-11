package io.github.coffee330501.testMybatisFlex;

import com.mybatisflex.core.query.QueryWrapper;
import io.github.coffee330501.common.util.ExcelUtil;
import io.github.coffee330501.testMybatisFlex.domain.vo.UserExcelTemplate;
import io.github.coffee330501.testMybatisFlex.mapper.UserMapper;
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
