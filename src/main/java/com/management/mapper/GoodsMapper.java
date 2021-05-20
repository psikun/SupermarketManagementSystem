package com.management.mapper;

import com.management.domain.Goods;

import java.util.List;

/**
 * @author SiKun
 * @date 2021/05/18/ 16:06
 */

public interface GoodsMapper {
    /**
     * 查询所有商品
     * @return 商品集合
     * @throws Exception 抛出异常
     */
    List<Goods> findAllGoods() throws Exception;
}
