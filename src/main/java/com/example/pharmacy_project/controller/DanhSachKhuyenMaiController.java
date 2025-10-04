/*
 * @ (#) DanhSachKhuyenMaiController         04 Oct 2025
 *
 *Copyright (c) 2025 IUH . All right reserved
 */
package com.example.pharmacy_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * @description:  This class represents a bank with many bank accounts
 * @author: Nguyen Van Sy
 * @version: 1.0
 * @created: 04 Oct 2025 23:50
 */
public class DanhSachKhuyenMaiController {

    public void themKhuyenMai(ActionEvent actionEvent) {
        try{
            Parent loader= FXMLLoader.load(getClass().getResource("/com/example/pharmacy_project/gui/ThemKhuyenMai.fxml"));
            Stage stage=new Stage();
            stage.setTitle("Thêm Khuyến Mại");
            stage.setScene(new Scene(loader));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
