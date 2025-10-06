/*
 * @ (#) $NAME.java     1.0     10/5/2025
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */
package com.example.pharmacy_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * @description:
 * @author: Khang, Le Hoang
 * @version: 1.0
 * @created: 10/5/2025 3:47 PM
 */
public class DsHoaDonController {
    public void handle_addOrder(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("/com/example/pharmacy_project/gui/lapHoaDon.fxml"));
            Stage stage=new Stage();
            stage.setTitle("Tạo hóa đơn");
            stage.setScene(new Scene(loader));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
