package com.management.controllers;


import com.management.domain.Brand;
import com.management.domain.Category;
import com.management.domain.Goods;
import com.management.domain.User;
import com.management.mapper.BrandMapper;
import com.management.mapper.CategoryMapper;
import com.management.mapper.GoodsMapper;
import com.management.mapper.UserMapper;
import com.management.utils.SqlSessionUtils;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
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


public class TablesController implements Initializable {
    public static Stage insertStage;

    private GoodsMapper goodsMapper;
    private List<Goods> updateGoods = new ArrayList<>();

    @FXML
    private TableView<Goods> goodsTable;
    @FXML
    private TableView<Category> categoryTable;

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
    private TextField goodsNameInput, categoryNameInput, brandNameInput;

    @FXML
    Label usernameLabel, userIdentityLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String identity = "普通用户";
        try {
            if (LoginController.user.isIsAdmin()) {
                identity = "管理员";
            }
            userIdentityLabel.setText(identity);
            usernameLabel.setText(LoginController.user.getUsername());
            this.printGoodsTable(getAllGoods());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @throws IOException 数据库连接异常
     */
    public void getConnection() throws IOException {
        // 获取数据库连接
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        goodsMapper = sqlSession.getMapper(GoodsMapper.class);
    }

    /**
     * 获取所有的商品
     *
     * @return 返回全部商品
     * @throws Exception 数据库连接异常
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
     */
    public void printGoodsTable(List<Goods> goods) throws Exception {

        ObservableList<Goods> teamMembers = FXCollections.observableArrayList(goods);

        // 表格设置为可编辑
        goodsTable.setEditable(true);
        // 设置单元格的值

        // 设置删除复选框
        goodsDelete.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Goods, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Goods, Boolean> param) {
                return param.getValue().deletedProperty();
            }
        });
        goodsDelete.setCellFactory(CheckBoxTableCell.forTableColumn(goodsDelete));
        // 设置商品编号列
        goodsID.setCellValueFactory(new PropertyValueFactory<>("id"));

        // 设置商品名称列，并设置为可编辑
        goodsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        goodsName.setCellFactory(TextFieldTableCell.forTableColumn());

        // 设置商品价格列，并设置为可编辑
        goodsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        goodsPrice.setCellFactory(TextFieldTableCell.<Goods, Double>forTableColumn(new DoubleStringConverter()));


        String[] choices = {"在售", "下架"};
        goodsIsOnSale.setCellFactory(ChoiceBoxTableCell.forTableColumn(choices));
        goodsIsOnSale.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Goods, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Goods, String> param) {
                if (param.getValue().isOnSale())
                    return new SimpleStringProperty(choices[0]);
                return new SimpleStringProperty(choices[1]);
            }
        });

        //  重写CallBack中的ObservableValue方法,设置分类和品牌的值
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
        // 设置商品销量列，并设置为可编辑
        goodsSales.setCellValueFactory(new PropertyValueFactory<>("sales"));
        goodsSales.setCellFactory(TextFieldTableCell.<Goods, Integer>forTableColumn(new IntegerStringConverter()));

        // 设置商品库存列，并设置为可编辑
        goodsInventory.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        goodsInventory.setCellFactory(TextFieldTableCell.<Goods, Integer>forTableColumn(new IntegerStringConverter()));

        // 设置商品添加时间和更新时间
        goodsAddTime.setCellValueFactory(new PropertyValueFactory<>("addTime"));
        goodsUpdateTime.setCellValueFactory(new PropertyValueFactory<>("updateTime"));

        // 设置商品备注，并设置为可编辑
        goodsRemarks.setCellFactory(TextFieldTableCell.forTableColumn());
        goodsRemarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));


        // 将集合中的值打印至表格
        goodsTable.setItems(teamMembers);
    }

    /**
     * 通过商品名搜索商品并打印至表格
     *
     * @param event 商品搜索按钮
     * @throws Exception 数据库连接异常
     */
    public void findGoods(ActionEvent event) throws Exception {
        this.getConnection();
        String text = "%" + goodsNameInput.getText() + "%";
        List<Goods> goods = goodsMapper.findGoodsByName(text);
        goodsNameInput.setText("");
        this.printGoodsTable(goods);
    }

    /**
     * 通过分类搜索商品
     *
     * @param event 分类搜索按钮
     * @throws Exception 数据库连接异常
     */
    public void searchCategory(ActionEvent event) throws Exception {
        this.getConnection();
        List<Goods> goods = goodsMapper.findGoodsByCategory(categoryNameInput.getText());
        categoryNameInput.setText("");
        this.printGoodsTable(goods);
    }

    /**
     * 通过品牌搜索商品
     *
     * @param event 品牌搜索按钮
     * @throws Exception 数据库连接异常
     */
    public void searchBrand(ActionEvent event) throws Exception {
        this.getConnection();
        List<Goods> goods = goodsMapper.findGoodsByBrand(brandNameInput.getText());
        brandNameInput.setText("");
        this.printGoodsTable(goods);
    }

    /**
     * 删除商品
     *
     * @param event 删除按钮
     * @throws Exception 数据库连接异常
     */
    public void deleteGoods(ActionEvent event) throws Exception {

        if (LoginController.user.isIsAdmin()) {

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

            this.printGoodsTable(getAllGoods());
        } else {
            this.warnAuthority();
        }
    }

    /**
     * 插入商品
     *
     * @param event 商品插入按钮
     * @throws Exception 数据库连接异常
     */
    public void insertGoods(ActionEvent event) throws Exception {
        if (LoginController.user.isIsAdmin()) {
            Parent insertGoods = FXMLLoader.load(getClass().getResource("/view/insertGoods.fxml"));
            Scene scene = new Scene(insertGoods, 750, 500);
            insertStage = new Stage();
            insertStage.setScene(scene);
            insertStage.setTitle("添加商品");
            insertStage.setResizable(false);
            insertStage.show();
        } else {
            this.warnAuthority();
        }


    }

    /**
     * 刷新商品
     *
     * @param event 刷新按钮
     * @throws Exception 数据库连接异常
     */
    public void refresh(ActionEvent event) throws Exception {
        this.printGoodsTable(getAllGoods());
    }

    /**
     * 权限不足警告
     */
    public void warnAuthority() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.titleProperty().set("权限不足");
        alert.headerTextProperty().set("您没有足够的权限！请联系管理员");
        alert.showAndWait();
    }

    /**
     * 设置表格单元格监听
     *
     * @param value 获取表格的值
     */
    public void getUpdateValue(TableColumn.CellEditEvent<Goods, ?> value) {
        // 得到本行商品
        Goods goods = value.getRowValue();
        // 获取修改的列
        String columnName = value.getTableColumn().getId();

        switch (columnName) {
            case "goodsName":
                if (value.getNewValue().toString().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.titleProperty().set("输入无效");
                    alert.headerTextProperty().set("商品名称不能为空！");
                    alert.showAndWait();
                } else {
                    goods.setName((String) value.getNewValue());
                }
                break;
            case "goodsPrice":
                goods.setPrice(Double.parseDouble((value.getNewValue()).toString()));
                break;

            case "goodsSales":
                goods.setSales(Integer.parseInt(value.getNewValue().toString()));
                break;

            case "goodsInventory":
                goods.setInventory(Integer.parseInt(value.getNewValue().toString()));
                break;

            case "goodsIsOnSale":
                goods.setOnSale(Boolean.parseBoolean(((String) value.getNewValue())));
                break;

            case "goodsRemarks":
                goods.setRemarks((String) value.getNewValue());
                break;
        }
        updateGoods.add(goods);
    }

    /**
     * 保存按钮
     *
     * @param event 保存按钮点击事件
     * @throws Exception 数据库连接异常
     */
    public void save(ActionEvent event) throws Exception {
        if (LoginController.user.isIsAdmin()) {
            this.getConnection();
            for (Goods goods : updateGoods) {
                goodsMapper.updateGoods(goods);
                this.printGoodsTable(this.getAllGoods());
            }
        } else
            this.warnAuthority();

    }
}






