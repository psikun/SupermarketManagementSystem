package com.management.mapper;

import com.management.domain.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SiKun
 * @date 2021/05/18/ 15:14
 */
public interface BrandMapper {
    /**
     * 查询所有品牌
     *
     * @return 品牌集合
     * @throws Exception 抛出异常
     */
    List<Brand> findAllBrands() throws Exception;

    /**
     * 按照品牌名查找品牌
     *
     * @param brandName 品牌名
     * @return 查找的品牌
     */
    Brand findBrandByName(@Param("brandName") String brandName);

    /**
     * 添加新品牌
     *
     * @param brandName 品牌名称
     * @return 是否成功
     */
    int insertBrand(@Param("brandName") String brandName);

    /**
     * 删除品牌
     *
     * @param id 品牌id
     * @return 是否成功
     */
    int deleteBrand(@Param("id") int id);
}
