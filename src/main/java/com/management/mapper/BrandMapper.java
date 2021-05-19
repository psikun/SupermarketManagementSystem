package com.management.mapper;

import com.management.domain.Brand;

import java.util.List;

/**
 * @author SiKun
 * @date 2021/05/18/ 15:14
 */
public interface BrandMapper {
    /**
     * 查询所有品牌
     * @return 品牌集合
     * @throws Exception 抛出异常
     */
    List<Brand> findAll() throws Exception;
}
