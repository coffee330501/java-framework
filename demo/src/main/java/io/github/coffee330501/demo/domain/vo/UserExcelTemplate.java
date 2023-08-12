package io.github.coffee330501.demo.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.github.coffee330501.common.convert.EnumConvert;
import io.github.coffee330501.common.enums.UserTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserExcelTemplate {

    private String id;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;

    private Long version;

    private String password;

    @ExcelProperty(value = "类型",converter = EnumConvert.class)
    private UserTypeEnum type;
}
