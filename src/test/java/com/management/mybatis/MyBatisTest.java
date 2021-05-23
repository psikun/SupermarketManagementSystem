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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * The type My batis test.
 *
 * @author SiKun
 * @date 2021 /05/18/ 11:38
 */
public class MyBatisTest {
    private SqlSession sqlSession;
    GoodsMapper goodsMapper;
    CategoryMapper categoryMapper;
    BrandMapper brandMapper;
    UserMapper userMapper;


    @Before
    public void before() throws IOException {
        sqlSession = SqlSessionUtils.getSqlSession();
        goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        categoryMapper = sqlSession.getMapper(CategoryMapper.class);
        brandMapper = sqlSession.getMapper(BrandMapper.class);
        userMapper = sqlSession.getMapper(UserMapper.class);

    }

    @After
    public void after() throws IOException {
        sqlSession.close();
    }

    @Test
    public void brandTest() throws Exception {
        Brand brand = brandMapper.findBrandByName("小米");
        List<Brand> brands = brandMapper.findAllBrands();
        List<Category> categories = categoryMapper.findAllCategories();
        System.out.println(categories);
        System.out.println(brand);
        System.out.println(brands);
    }

    @Test
    public void goodsTest() throws Exception {
        List<Goods> goods = goodsMapper.findAllGoods();
        System.out.println(goods);
    }

    @Test
    public void userTest() throws IOException {
        List<User> allUsers = userMapper.findAllUsers();
        System.out.println(allUsers);
    }

    @Test
    public void registerTest() throws IOException {
        int register = userMapper.register("mysql", "mysql");
        System.out.println(register);
    }


    @Test
    public void loginTest() throws IOException {
        User user = userMapper.login("admin", "admin");
        System.out.println(user);
    }

    @Test
    public void insertBrandTest() throws IOException {
        int i = brandMapper.insertBrand("联想");
        System.out.println(i);
    }

    @Test
    public void findGoodsByBrand() throws IOException {
        List<Goods> goods = goodsMapper.findGoodsByCategory("手机");
        System.out.println(goods);
    }

    @Test
    public void insertGoodsTest() throws IOException {

        Goods goods = new Goods();
        goods.setName("红米k40pro");
        goods.setPrice(6999);
        Category category = categoryMapper.findCategoryByName("手机");
        goods.setCategory(category);
        Brand brand = brandMapper.findBrandByName("小米");
        System.out.println(category);
        goods.setBrand(brand);
        goods.setSales(20);
        goods.setRemarks("");
        goods.setInventory(100);
        int i = goodsMapper.insertGoods(goods);
        System.out.println(i);
    }


    @Test
    public void addRemarksTest() {
        int remark = goodsMapper.addRemark("我试试能不能插入", 1);
    }
}

