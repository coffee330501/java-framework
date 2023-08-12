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
@Table(value = "apply", schema = "java_framework")
public class Apply implements Serializable {

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

    /**
     * 申请单创建人
     */
    private String createBy;

    /**
     * 最后更新人
     */
    private String updateBy;

    /**
     * 申请单类型
     */
    private String type;

    /**
     * 申请单状态
     */
    private String status;

    private String content;

}
