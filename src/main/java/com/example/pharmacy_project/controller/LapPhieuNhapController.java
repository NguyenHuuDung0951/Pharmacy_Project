/*
 * @ (#) $NAME.java     1.0     10/7/2025
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */
package com.example.pharmacy_project.controller;

import javafx.event.ActionEvent;
import javafx.scene.Parent;

/*
 * @description:
 * @author: Khang, Le Hoang
 * @version: 1.0
 * @created: 10/7/2025 12:44 PM
 */
public class LapPhieuNhapController {
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    public void handle_quayLai(ActionEvent actionEvent) {
        mainController.openDsPhieuNhap();
    }
}
