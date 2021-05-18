package com.management.mapper;

import com.management.domain.Goods;

import java.util.List;

/**
 * @author SiKun
 * @date 2021/05/18/ 16:06
 */

public interface GoodsMapper {
    List<Goods> findAll() throws Exception;
}
