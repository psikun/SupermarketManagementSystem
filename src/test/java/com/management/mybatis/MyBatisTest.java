package com.management.mybatis;

import com.management.domain.Brand;
import com.management.domain.Category;
import com.management.domain.Goods;
import com.management.domain.User;
import com.management.mapper.BrandMapper;
import com.management.mapper.CategoryMapper;
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
        Brand brand = mapper.findBrandByName("小米");

//      List<Brand> brandList = sqlSession.selectList("brandMapper.findAll");
        System.out.println(brand);
//        mapper.insertBrand("oppo");
//        sqlSession.commit();


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
        int register = mapper.register("mysql", "mysql");
        System.out.println(register);
        sqlSession.commit();
    }

    @Test
    public void loginTest() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.login("admin", "admin");
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void insertBrandTest() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int i = mapper.insertBrand("联想");
        System.out.println(i);
    }

    @Test
    public void findGoodsByBrand() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
        List<Goods> goods = mapper.findGoodsByCategory("手机");
        System.out.println(goods);
    }

    @Test
    public void insertGoodsTest() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
        CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        Goods goods = new Goods();
        goods.setName("红米k40");
        goods.setPrice(6999);
        Category category = categoryMapper.findCategoryByName("手机");
        goods.setCategory(category);
        Brand brand = brandMapper.findBrandByName("小米");
        System.out.println(category);
        goods.setBrand(brand);
        goods.setSales(20);
        goods.setRemarks("");
        goods.setInventory(100);

        int i = mapper.insertGoods(goods);
    }
}

