package io.github.coffee330501.testMybatisFlex.domain.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.github.coffee330501.common.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表 实体类。
 *
 * @author dell
 * @since 2023-08-04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "user", schema = "test_mybatis_flex")
public class User implements Serializable {

    @Id(keyType = KeyType.Generator, value = "SnowflakeId")
    private String id;

    @Column(onInsertValue = "now()")
    private LocalDateTime createTime;

    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    private LocalDateTime updateTime;

    @Column(isLogicDelete = true)
    private LocalDateTime deleteTime;

    /**
     * 名称
     */
    private String name;

    private Integer age;

    @Column(version = true)
    private Long version;

    private String password;

    private UserTypeEnum type;

}
