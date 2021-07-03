package com.management.controllers;

import com.management.mapper.UserMapper;
import com.management.utils.SqlSessionUtils;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.ibatis.session.SqlSession;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * 实现用户信息的注册
 *
 * @author SiKun
 * @date 2021/07/01/ 11:19
 */

public class LoginController implements Initializable {

    private String username;

    private String password;

    private UserMapper userMapper;


    @FXML
    private Label messageLabel;

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getConnection() throws Exception {
        // 获取用户名
        username = usernameText.getText();
        // 获取密码
        password = passwordText.getText();

        // 获取数据库连接
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    public void register(ActionEvent event) throws Exception {
        // 获取连接
        this.getConnection();
        if (!username.equals("") && !password.equals("")) {
            // 判断用户是否存在
            if (userMapper.findUserByName(username) == null) {
                if (userMapper.register(username, password) != 0)
                    messageLabel.setText("注册成功,请登录！");
            } else {
                messageLabel.setText("该用户已存在！");
                usernameText.setText("");
                passwordText.setText("");
            }
        } else {
            messageLabel.setText("请输入完整的用户名和密码！");
        }
    }

    public void login(ActionEvent event) throws Exception {
        // 获取连接
        this.getConnection();
        if (!username.equals("") && !password.equals("")) {
            if (userMapper.login(username, password) != null) {
                messageLabel.setText("登陆成功！");
                // 沉睡5ms
                Thread.sleep(5);
                Parent tables = FXMLLoader.load(getClass().getResource("/view/tables.fxml"));
                Scene scene = new Scene(tables, 1400, 850);
                Stage tableStage = new Stage();
                tableStage.setScene(scene);
                tableStage.setTitle("超市管理系统");
                tableStage.setResizable(false);
                tableStage.show();
            } else {
                messageLabel.setText("用户名或密码错误！");
                usernameText.setText("");
                passwordText.setText("");
            }
        } else {
            messageLabel.setText("请输入完整的用户名和密码！");
        }
    }

}
