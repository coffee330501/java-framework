package io.github.coffee330501.testMybatisFlex;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.coffee330501.common.enums.UserTypeEnum;
import io.github.coffee330501.model.domain.entity.User;
import io.github.coffee330501.model.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static io.github.coffee330501.model.domain.entity.table.UserTableDef.USER;


@SpringBootTest
public class TestMybatisFlex {
    @Resource
    private UserMapper userMapper;

    @Test
    void insert() {
        User user = new User();
        user.setName("张三");
        user.setAge(1);
        user.setType(UserTypeEnum.Recovery);
        userMapper.insert(user);
    }

    @Test
    void insertBatch() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("李四" + i);
            user.setAge(i);
            user.setType(UserTypeEnum.Recovery);
            users.add(user);
        }
        userMapper.insertBatch(users);
    }

    @Test
    void select() {
        QueryWrapper wrapper = QueryWrapper.create().select().where(USER.NAME.eq("张三")).limit(1);
        User user = userMapper.selectOneByQuery(wrapper);
        System.out.println(user);
    }

    @Test
    void page() {
        Page<User> page = new Page<>();
        page.setPageNumber(1);
        page.setPageSize(10);
        QueryWrapper wrapper = QueryWrapper.create().select().where(USER.NAME.eq("张三")).limit(1);
        Page<User> userPage = userMapper.paginate(page, wrapper);
        System.out.println(userPage);
    }

    /**
     * 注意：引入pageHelper后不能再使用xmlPaginate
     */
    @Test
    void pageByXML() {
        Page<User> page = new Page<>();
        page.setPageNumber(1);
        page.setPageSize(2);
        Page<User> page1 = userMapper.xmlPaginate("page", page, new QueryWrapper());
        System.out.println(page1);
    }

    @Test
    void pageByPageHelper() {
        PageHelper.startPage(1, 2);
        List<User> users = userMapper.selectAll();
        PageInfo<User> page = new PageInfo<>(users);
        System.out.println(page.getList());
    }

    @Test
    void update() {
        QueryWrapper wrapper = QueryWrapper.create().select().where(USER.NAME.eq("张三")).limit(1);
        User user = userMapper.selectOneByQuery(wrapper);
        user.setAge(3);
        userMapper.update(user, true);
    }

    @Test
    void delete() {
        QueryWrapper wrapper = QueryWrapper.create().where(USER.NAME.likeLeft("李四"));
        int i = userMapper.deleteByQuery(wrapper);
        System.out.println("软删除了" + i + "条数据");
    }

    @Test()
    @Transactional(rollbackFor = Exception.class)
    void tx() {
        User user = new User();
        user.setName("张三3");
        user.setAge(2);
        userMapper.insert(user);
        int a = 1;
        int b = 0;
        int c = a / b;
        userMapper.insert(user);
    }

    @Test()
    void enumField() {
        QueryWrapper wrapper = QueryWrapper.create();
        wrapper.where(USER.TYPE.eq(UserTypeEnum.Pedlar)).limit(1);
        User user = userMapper.selectOneByQuery(wrapper);
        System.out.println(user);
    }
}
