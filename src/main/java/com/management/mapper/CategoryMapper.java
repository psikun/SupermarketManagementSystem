package com.management.mapper;

import com.management.domain.Brand;
import com.management.domain.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SiKun
 * @date 2021/05/19/ 10:23
 */
public interface CategoryMapper {
    /**
     * 查询所有类别
     *
     * @return 类别集合
     * @throws Exception 抛出异常
     */
    List<Category> findAllCategories() throws Exception;

    Category findCategoryById(@Param("id") int id);


    /**
     * 按照类别名查找分类
     *
     * @param categoryName 分类名
     * @return 查找的分类
     */
    Category findCategoryByName(@Param("categoryName") String categoryName);

    /**
     * 添加新分类
     *
     * @param categoryName 分类名
     * @return 是否成功
     */
    int insertCategory(@Param("categoryName") String categoryName);

    /**
     * 删除分类
     *
     * @param id 类别id
     * @return 是否成功
     */
    int deleteCategory(@Param("id") int id);
}
