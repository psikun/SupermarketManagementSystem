package com.management.controllers;

import com.management.domain.Brand;
import com.management.domain.Category;
import com.management.domain.Goods;
import com.management.mapper.BrandMapper;
import com.management.mapper.CategoryMapper;
import com.management.mapper.GoodsMapper;
import com.management.utils.SqlSessionUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author SiKun
 * @date 2021/07/02/ 13:17
 */

public class InsertGoodsController implements Initializable {

    private static int IS_ON_SALE;
    public static int isInsertSuccess = 0;

    private GoodsMapper goodsMapper;
    private CategoryMapper categoryMapper;
    private BrandMapper brandMapper;

    @FXML
    private AnchorPane insertGoods;

    @FXML
    private ChoiceBox<String> isOnSaleChoice;

    @FXML
    private TextField goodsNameText, goodsInventoryText, goodsSalesText, goodsPriceText, goodsCategoryText, goodsBrandText;
    @FXML
    private TextArea goodsRemarksText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.updateChoice();
            this.getIsOnSale();
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
        String[] status = {"下架", "在售"};
        isOnSaleChoice.setItems(FXCollections.observableArrayList(status));
        isOnSaleChoice.setValue("在售");
    }

    public void getIsOnSale() {
        //设置销售状态选项框的监听
        isOnSaleChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                IS_ON_SALE = newValue.intValue();
            }
        });
    }

    public Category getCategory(String categoryName) {
        // 数据库中插入种类和品牌
        Category category = categoryMapper.findCategoryByName(categoryName);
        if (category == null) {
            categoryMapper.insertCategory(categoryName);
            category = categoryMapper.findCategoryByName(categoryName);
        }
        return category;
    }

    public Brand getBrand(String brandName) {
        Brand brand = brandMapper.findBrandByName(brandName);
        if (brand == null) {
            brandMapper.insertBrand(brandName);
            brand = brandMapper.findBrandByName(brandName);
        }
        return brand;
    }

    public void insertGoods(ActionEvent event) throws Exception {
        Goods goods = new Goods();

        // 判断商品信息是否完全
        if (goodsNameText.getText().equals("") || goodsBrandText.getText().equals("")
                || goodsCategoryText.getText().equals("") || goodsPriceText.getText().equals("")
                || goodsInventoryText.getText().equals("·") || goodsSalesText.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.titleProperty().set("警告");
            alert.headerTextProperty().set("请输入完整信息！");
            alert.showAndWait();
            System.out.println(goodsMapper.findGoodsByName(goodsNameText.getText()));
        }
        // 判断商品是否已经存在
        else if (goodsMapper.findGoodsByName(goodsNameText.getText()).size() != 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.titleProperty().set("警告");
            alert.headerTextProperty().set("商品已存在！");
            alert.showAndWait();
            goodsNameText.setText("");
        } else {
            // 得到种类和品牌的名字
            Brand brand = this.getBrand(goodsBrandText.getText());
            Category category = this.getCategory(goodsCategoryText.getText());
            boolean flag = IS_ON_SALE != 0;
            goods.setOnSale(flag);
            goods.setName(goodsNameText.getText());
            goods.setCategory(category);
            goods.setBrand(brand);
            goods.setPrice(Double.parseDouble(goodsPriceText.getText()));
            goods.setInventory(Integer.parseInt(goodsInventoryText.getText()));
            goods.setSales(Integer.parseInt(goodsSalesText.getText()));
            goods.setRemarks(goodsRemarksText.getText());

            // 使用变量判断是都添加成功
            isInsertSuccess = goodsMapper.insertGoods(goods);
            if (isInsertSuccess != 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.titleProperty().set("添加成功");
                alert.headerTextProperty().set("添加商品成功！请刷新页面！");
                alert.showAndWait();
                //关闭窗口
                TablesController.insertStage.close();
            }
        }

    }
}
