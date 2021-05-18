package com.management.mapper;

import com.management.domain.Brand;

import java.util.List;

/**
 * @author SiKun
 * @date 2021/05/18/ 15:14
 */
public interface BrandMapper {
    List<Brand> findAll() throws Exception;
}