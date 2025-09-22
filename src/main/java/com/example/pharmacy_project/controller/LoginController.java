package com.example.pharmacy_project.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblErrorusername;
    @FXML
    private Label lblErrorpassword;
    @FXML
    public void initialize(){
        txtUsername.requestFocus();
        txtPassword.setOnAction(actionEvent -> handleLogin(actionEvent));
    }
    @FXML
    public void handleLogin(javafx.event.ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        boolean valid = true;
        if (username.isEmpty()){
            lblErrorusername.setText(" Vui Lòng nhập Tên Đăng Nhập!!!");
            valid = false;
        }
        else if(!"admin".equals(username)) {
            lblErrorusername.setText("Sai Tên Đăng Nhập!!!");
            valid = false;
        }
        else {
            lblErrorusername.setText("");
        }
        if (password.isEmpty()){
            lblErrorpassword.setText("Vui Lòng nhập Mật Khẩu!!!");
            valid = false;
        }
        else if(!"123".equals(password)) {
            lblErrorpassword.setText("Sai Mật Khẩu!!!");
            valid = false;
        }
        else {
            lblErrorpassword.setText("");
        }
        if(valid){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pharmacy_project/gui/MainView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Hello!");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.show();

                Stage currenStage = (Stage) txtUsername.getScene().getWindow();
                currenStage.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
