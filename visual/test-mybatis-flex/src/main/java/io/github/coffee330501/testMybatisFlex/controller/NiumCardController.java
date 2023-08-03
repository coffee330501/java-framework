package io.github.coffee330501.testMybatisFlex.controller;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.coffee330501.testMybatisFlex.entity.NiumCard;
import io.github.coffee330501.testMybatisFlex.service.NiumCardService;
import org.springframework.web.bind.annotation.RestController;
import java.io.Serializable;
import java.util.List;

/**
 *  控制层。
 *
 * @author dell
 * @since 2023-08-01
 */
@RestController
@RequestMapping("/niumCard")
public class NiumCardController {

    @Autowired
    private NiumCardService niumCardService;

    /**
     * 添加。
     *
     * @param niumCard 
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody NiumCard niumCard) {
        return niumCardService.save(niumCard);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return niumCardService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param niumCard 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody NiumCard niumCard) {
        return niumCardService.updateById(niumCard);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<NiumCard> list() {
        return niumCardService.list();
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public NiumCard getInfo(@PathVariable Serializable id) {
        return niumCardService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<NiumCard> page(Page<NiumCard> page) {
        return niumCardService.page(page);
    }

}
