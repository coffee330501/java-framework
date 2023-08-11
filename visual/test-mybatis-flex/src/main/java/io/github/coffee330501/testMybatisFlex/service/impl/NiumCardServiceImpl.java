package io.github.coffee330501.testMybatisFlex.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.coffee330501.testMybatisFlex.domain.entity.NiumCard;
import io.github.coffee330501.testMybatisFlex.mapper.NiumCardMapper;
import io.github.coffee330501.testMybatisFlex.service.NiumCardService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author dell
 * @since 2023-08-01
 */
@Service
public class NiumCardServiceImpl extends ServiceImpl<NiumCardMapper, NiumCard> implements NiumCardService {

}
