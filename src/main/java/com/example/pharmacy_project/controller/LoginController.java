package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.taiKhoanDao;
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
    private taiKhoanDao dao = new taiKhoanDao();
    @FXML
    public void handleLogin(javafx.event.ActionEvent actionEvent) {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        boolean valid = true;

        if (username.isEmpty()) {
            lblErrorusername.setText("Vui lòng nhập Tên Đăng Nhập!");
            valid = false;
        } else {
            lblErrorusername.setText("");
        }

        if (password.isEmpty()) {
            lblErrorpassword.setText("Vui lòng nhập Mật Khẩu!");
            valid = false;
        } else {
            lblErrorpassword.setText("");
        }

        if (valid) {
            boolean success = dao.validateLogin(username, password);
            if (success) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pharmacy_project/gui/MainView2.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Pharmacy Management");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.centerOnScreen();
                    stage.show();

                    // Đóng cửa sổ login
                    Stage currentStage = (Stage) txtUsername.getScene().getWindow();
                    currentStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                lblErrorpassword.setText("Sai Tên Đăng Nhập hoặc Mật Khẩu!");
            }
        }
    }

}
