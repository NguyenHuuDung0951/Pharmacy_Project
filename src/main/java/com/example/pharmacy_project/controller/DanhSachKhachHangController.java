package com.example.pharmacy_project.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

public class DanhSachKhachHangController {
    public void taoKhachHang(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("/com/example/pharmacy_project/gui/taoKhachHang.fxml"));
            Stage stage=new Stage();
            stage.setTitle("Thêm Khách Hàng");
            stage.setScene(new Scene(loader));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    @FXML
//    private TextField taoKhachHang;
//    @FXML
//    public void xaoboiDen()
//    {
//        Platform.runLater(()->
//                {
//                    taoKhachHang.requestFocus();
//                    taoKhachHang.positionCaret(taoKhachHang.getText().length());
//                }
//        );
//    }
}
