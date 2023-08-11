package io.github.coffee330501.testMybatisFlex.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.coffee330501.testMybatisFlex.domain.entity.User;
import io.github.coffee330501.testMybatisFlex.mapper.UserMapper;
import io.github.coffee330501.testMybatisFlex.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表 服务层实现。
 *
 * @author dell
 * @since 2023-08-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
