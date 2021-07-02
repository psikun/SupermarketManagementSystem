package com.management.controllers;

import com.management.domain.Brand;
import com.management.domain.Category;
import com.management.domain.Goods;
import com.management.mapper.GoodsMapper;
import com.management.mapper.UserMapper;
import com.management.utils.SqlSessionUtils;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import org.apache.ibatis.session.SqlSession;


import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author SiKun
 * @date 2021/07/01/ 20:03
 */


public class TableController implements Initializable {

    private GoodsMapper goodsMapper;

    @FXML
    private TableView<Goods> goodsTable;

    @FXML
    private TableColumn<Goods, Integer> goodsID, goodsSales, goodsInventory, goodsIsOnSale, goodsDeleted;
    @FXML
    private TableColumn<Goods, String> goodsName, goodsRemarks, goodsCategory, goodsBrand;
    @FXML
    private TableColumn<Goods, Double> goodsPrice;
    @FXML
    private TableColumn<Goods, Date> goodsAddTime, goodsUpdateTime;

    @FXML
    private TextField goodsNameInput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.printTable(getAllGoods());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getConnection() throws IOException {
        // 获取数据库连接
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        goodsMapper = sqlSession.getMapper(GoodsMapper.class);
    }

    /**
     * 获取所有的商品
     *
     * @return 返回全部商品
     * @throws Exception
     */

    public List<Goods> getAllGoods() throws Exception {
        // 获取数据库连接
        this.getConnection();
        // 定义商品集合，并且从数据库中查询
        return goodsMapper.findAllGoods();
    }

    /**
     * 将给定的商品表打印至表格
     *
     * @param goods 商品表
     * @throws Exception
     */
    public void printTable(List<Goods> goods) throws Exception {

        ObservableList<Goods> teamMembers = FXCollections.observableArrayList(goods);

        // 设置单元格的值
        goodsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        goodsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        goodsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //  重写CallBack中的ObservableValue方法；
        goodsCategory.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Goods, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Goods, String> param) {
                String category = param.getValue().getCategory().getCategoryName();
                return new SimpleStringProperty(category);
            }
        });

        goodsBrand.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Goods, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Goods, String> param) {
                String brand = param.getValue().getBrand().getBrandName();
                return new SimpleStringProperty(brand);
            }
        });
        goodsSales.setCellValueFactory(new PropertyValueFactory<>("sales"));
        goodsInventory.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        goodsAddTime.setCellValueFactory(new PropertyValueFactory<>("addTime"));
        goodsUpdateTime.setCellValueFactory(new PropertyValueFactory<>("updateTime"));
        goodsRemarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        goodsIsOnSale.setCellValueFactory(new PropertyValueFactory<>("isOnSale"));
        goodsDeleted.setCellValueFactory(new PropertyValueFactory<>("deleted"));

        // 将集合中的值打印至表格
        goodsTable.setItems(teamMembers);
    }

    /**
     * 通过商品名搜索商品并打印至表格
     */
    public void findGoods(ActionEvent event) throws Exception {
        this.getConnection();
        List<Goods> goods = goodsMapper.findGoodsByName(goodsNameInput.getText());
        this.printTable(goods);
    }

}
