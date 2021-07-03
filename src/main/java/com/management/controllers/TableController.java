package com.management.controllers;


import com.management.domain.Goods;
import com.management.mapper.GoodsMapper;
import com.management.utils.SqlSessionUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.ibatis.session.SqlSession;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private TableColumn<Goods, Integer> goodsID, goodsSales, goodsInventory;
    @FXML
    private TableColumn<Goods, String> goodsName, goodsRemarks, goodsCategory, goodsBrand, goodsIsOnSale;
    @FXML
    private TableColumn<Goods, Double> goodsPrice;
    @FXML
    private TableColumn<Goods, Date> goodsAddTime, goodsUpdateTime;
    @FXML
    private TableColumn<Goods, Boolean> goodsDelete;
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


        // 表格设置为可编辑
        goodsTable.setEditable(true);
        // 设置单元格的值

        // 设置复选框
        goodsDelete.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Goods, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Goods, Boolean> param) {
                return param.getValue().deletedProperty();
            }
        });
        goodsDelete.setCellFactory(CheckBoxTableCell.forTableColumn(goodsDelete));
        goodsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        goodsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        goodsName.setCellFactory(TextFieldTableCell.forTableColumn());//给需要编辑的列设置属性
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
        goodsIsOnSale.setCellValueFactory(new PropertyValueFactory<>("onSale"));
//        goodsIsOnSale.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Goods, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Goods, String> param) {
//                Boolean isOnSale = param.getValue().getOnSale();
//                String flag;
//                if (isOnSale) {
//                    flag = "在售";
//                } else {
//                    flag = "下架";
//                }
//                return new SimpleStringProperty(flag);
//            }
//        });

        // 将集合中的值打印至表格
        goodsTable.setItems(teamMembers);

    }

    /**
     * 通过商品名搜索商品并打印至表格
     */
    public void findGoods(ActionEvent event) throws Exception {
        this.getConnection();
        String text = "%" + goodsNameInput.getText() + "%";
        List<Goods> goods = goodsMapper.findGoodsByName(text);
        this.printTable(goods);
    }

    /**
     * 删除商品
     *
     * @param event 删除按钮
     * @throws Exception
     */
    public void deleteGoods(ActionEvent event) throws Exception {

        // 设置表格监听
        goodsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Goods>() {
            @Override
            public void changed(ObservableValue<? extends Goods> observable, Goods oldValue, Goods newValue) {
            }
        });

        // 表格中第一列，即复选框列
        TableColumn<Goods, ?> goodsDeleteColumn = goodsTable.getColumns().get(0);

        // 获取第二列，即商品ID
        TableColumn<Goods, ?> goodsIDColumn = goodsTable.getColumns().get(1);

        // 获取数据库中商品的数目
        ObservableList<Goods> goods = FXCollections.observableArrayList(this.getAllGoods());

        // 定义删除的集合
        ArrayList<Integer> deleteGoods = new ArrayList<Integer>();

        // 从商品列表中循环判断复选框是否被选中，若被选中，则将商品的ID放入集合中
        for (int i = 0; i < goods.size(); i++) {
            if ((Boolean) goodsDeleteColumn.getCellObservableValue(i).getValue()) {
                deleteGoods.add((Integer) goodsIDColumn.getCellObservableValue(i).getValue());
            }
        }
        // 获取数据库连接
        this.getConnection();
        // 循环列表进行删除
        for (int id : deleteGoods)
            goodsMapper.deleteGoods(id);

        this.printTable(getAllGoods());
    }

    public void insertGoods(ActionEvent event) throws IOException {
        Parent insertGoods = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(insertGoods, 750, 500);
        Stage insertStage = new Stage();
        insertStage.setScene(scene);
        insertStage.setTitle("添加商品");
        insertStage.setResizable(false);
        insertStage.show();
    }

    public void refresh(ActionEvent event) throws Exception {
        this.printTable(getAllGoods());
    }
}
