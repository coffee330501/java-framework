package io.github.coffee330501.demo.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.coffee330501.model.domain.entity.User;
import io.github.coffee330501.model.mapper.UserMapper;
import io.github.coffee330501.demo.service.UserService;
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
