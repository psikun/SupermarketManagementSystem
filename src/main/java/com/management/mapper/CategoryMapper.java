package com.management.mapper;

import com.management.domain.Category;

import java.util.List;

/**
 * @author SiKun
 * @date 2021/05/19/ 10:23
 */
public interface CategoryMapper {
    /**
     * 查询所有类别
     * @return 类别集合
     * @throws Exception 抛出异常
     */
    List<Category> findAll() throws Exception;
}
