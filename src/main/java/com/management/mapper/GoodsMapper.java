package com.management.mapper;

import com.management.domain.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SiKun
 * @date 2021/05/18/ 16:06
 */

public interface GoodsMapper {
    /**
     * 查询所有商品
     *
     * @return 商品集合
     * @throws Exception 抛出异常
     */
    List<Goods> findAllGoods() throws Exception;

    /**
     * 通过商品名查询商品
     *
     * @param name 商品名称
     * @return 商品列表
     */
    List<Goods> findGoodsByName(@Param("name") String name);

    /**
     * 通过品牌查询商品
     *
     * @param brandName 品牌名称
     * @return 商品列表
     */
    List<Goods> findGoodsByBrand(@Param("brandName") String brandName);

    /**
     * 通过分类查询商品
     *
     * @param categoryName 分类名称
     * @return 商品列表
     */
    List<Goods> findGoodsByCategory(@Param("categoryName") String categoryName);


}
