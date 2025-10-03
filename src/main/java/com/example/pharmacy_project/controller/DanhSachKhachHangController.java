package com.example.pharmacy_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DanhSachKhachHangController {
    public void taoKhachHang(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pharmacy_project/gui/taoKhachHang.fxml"));
            Parent parent =loader.load();
            Stage stage=new Stage();
            stage.setTitle("Thêm Khách Hàng");
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
