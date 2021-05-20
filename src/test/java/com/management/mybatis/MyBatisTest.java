package com.management.mybatis;

import com.management.domain.Brand;
import com.management.domain.Goods;
import com.management.domain.User;
import com.management.mapper.BrandMapper;
import com.management.mapper.GoodsMapper;
import com.management.mapper.UserMapper;
import com.management.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
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
        List<Brand> all = mapper.findAllBrands();
//      List<Brand> brandList = sqlSession.selectList("brandMapper.findAll");
        System.out.println(all);
        sqlSession.close();
    }

    @Test
    public void goodsTest() throws Exception {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
        List<Goods> goods = mapper.findAllGoods();
        System.out.println(goods);

    }

    @Test
    public void userTest() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> allUsers = mapper.findAllUsers();
        System.out.println(allUsers);
    }

    @Test
    public void registerTest() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.register("test1","test2");
        sqlSession.commit();
    }

    @Test
    public void loginTest() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.login("admin", "admin");
        System.out.println(user);

    }
}

