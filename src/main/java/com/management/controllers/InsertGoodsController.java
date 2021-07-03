package com.management.controllers;

import com.management.domain.Brand;
import com.management.domain.Category;
import com.management.domain.Goods;
import com.management.mapper.BrandMapper;
import com.management.mapper.CategoryMapper;
import com.management.mapper.GoodsMapper;
import com.management.utils.SqlSessionUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author SiKun
 * @date 2021/07/02/ 13:17
 */

public class InsertGoodsController implements Initializable {
    private GoodsMapper goodsMapper;
    private CategoryMapper categoryMapper;
    private BrandMapper brandMapper;

    @FXML
    private AnchorPane insertGoods;

    @FXML
    private ChoiceBox<String> categoryChoice, brandChoice, isOnSaleChoice;

    @FXML
    private TextField goodsNameText, goodsInventoryText, goodsSalesText, goodsPriceText;
    @FXML
    private TextArea goodsRemarksText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.updateChoice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getConnection() throws IOException {
        // 获取数据库连接
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        categoryMapper = sqlSession.getMapper(CategoryMapper.class);
        brandMapper = sqlSession.getMapper(BrandMapper.class);
    }

    public void updateChoice() throws Exception {
        this.getConnection();
        List<Category> allCategories = categoryMapper.findAllCategories();
        ArrayList<String> categoryNames = new ArrayList<String>();
        for (Category category : allCategories) {
            categoryNames.add(category.getCategoryName());
        }
        List<Brand> allBrands = brandMapper.findAllBrands();
        ArrayList<String> brandNames = new ArrayList<String>();
        for (Brand brand : allBrands) {
            brandNames.add(brand.getBrandName());
        }
        categoryChoice.setItems(FXCollections.observableArrayList(categoryNames));
        brandChoice.setItems(FXCollections.observableArrayList(brandNames));
    }


    public void insertGoods() {
        Goods goods = new Goods();
        goods.setName(goodsNameText.getText());
        goods.setPrice(Double.parseDouble(goodsPriceText.getText()));
        goods.setInventory(Integer.parseInt(goodsInventoryText.getText()));
        goods.setSales(Integer.parseInt(goodsSalesText.getText()));
        goods.setRemarks(goodsRemarksText.getText());
    }
}
