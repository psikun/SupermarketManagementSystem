package com.management.service;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage loginStage;

    public static void main(String[] args) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent login = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        loginStage = primaryStage;
        loginStage.setTitle("用户登录");
        loginStage.setScene(new Scene(login, 800, 600));
        // 禁止更改大小
        loginStage.setResizable(false);
        loginStage.show();

    }
}
