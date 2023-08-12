package io.github.coffee330501.model.domain.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *  实体类。
 *
 * @author dell
 * @since 2023-08-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "nium_card", schema = "test_mybatis_flex")
public class NiumCard implements Serializable {

    @Id(keyType = KeyType.Generator, value = "SnowflakeId")
    private String id;

    @Column(onInsertValue = "now()")
    private LocalDateTime createTime;

    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    private LocalDateTime updateTime;

    @Column(isLogicDelete = true)
    private LocalDateTime deleteTime;

    @Column(version = true)
    private Long version;

    private Integer cardNo;

    private BigDecimal balance;

}
