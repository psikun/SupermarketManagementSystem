package com.management.mybatis;

import com.management.domain.Brand;
import com.management.domain.Goods;
import com.management.mapper.BrandMapper;
import com.management.mapper.GoodsMapper;
import com.management.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author SiKun
 * @date 2021/05/18/ 11:38
 */

public class MyBatisTest {
    @Test
    public void brandTest() throws Exception {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> all = mapper.findAll();
//      List<Brand> brandList = sqlSession.selectList("brandMapper.findAll");
        System.out.println(all);
        sqlSession.close();
    }

    @Test
    public void goodsTest() throws Exception {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
        List<Goods> goods = mapper.findAll();
        System.out.println(goods);

    }
}

