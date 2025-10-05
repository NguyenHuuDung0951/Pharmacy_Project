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
 * @created: 10/5/2025 5:02 PM
 */
public class DsDonDatController {
    public void handle_lapPhieuDat(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("/com/example/pharmacy_project/gui/lapPhieuDat.fxml"));
            Stage stage=new Stage();
            stage.setTitle("Đặt thuốc");
            stage.setScene(new Scene(loader));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
