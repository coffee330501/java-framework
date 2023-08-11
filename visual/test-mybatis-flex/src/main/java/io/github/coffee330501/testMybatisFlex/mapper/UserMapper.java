package io.github.coffee330501.testMybatisFlex.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import io.github.coffee330501.testMybatisFlex.domain.entity.User;

/**
 * 用户表 映射层。
 *
 * @author dell
 * @since 2023-08-01
 */
public interface UserMapper extends BaseMapper<User> {
    Page<User> page(Page userPAGE);
}
