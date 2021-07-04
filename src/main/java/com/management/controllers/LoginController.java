package com.management.controllers;

import com.management.domain.User;
import com.management.mapper.UserMapper;
import com.management.service.Main;
import com.management.utils.SqlSessionUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

    public static Stage tableStage;

    private String username;

    private String password;

    private UserMapper userMapper;

    public static User user;

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
            user = userMapper.login(username, password);
            if (user != null) {
                messageLabel.setText("登陆成功");
                Thread.sleep(50);
                // 加载表格窗体
                Parent tables = FXMLLoader.load(getClass().getResource("/view/tables.fxml"));
                Scene scene = new Scene(tables, 1800, 850);
                tableStage = new Stage();
                tableStage.setScene(scene);
                tableStage.setTitle("超市管理系统");
                tableStage.setResizable(false);
                tableStage.show();

                // 登陆成功后关闭登陆窗口
                Main.loginStage.close();
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
