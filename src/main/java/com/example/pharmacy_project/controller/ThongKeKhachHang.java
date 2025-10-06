package com.example.pharmacy_project.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

public class ThongKeKhachHang {
    @FXML
    private ComboBox<String> cbm;
    @FXML
    private BorderPane rootPane;

    public void initialize(){
    cbm.getItems().addAll("Bán Hàng","Giới Tính","...");
    cbm.getSelectionModel().selectFirst();
    cbm.setOnAction(actionEvent -> {
        String selected = cbm.getSelectionModel().getSelectedItem();
        loadChart(selected);
    });
    }
    private void loadChart(String option){
        try {
            Parent chartView = null;
            switch (option){
                case "Bán Hàng" :
                    chartView = FXMLLoader.load(getClass().getResource("/com/example/pharmacy_project/gui/admin/customer_chart/TopCustomer.fxml"));
                    break;
                case "Giới Tính" :
                    chartView = FXMLLoader.load(getClass().getResource("/com/example/pharmacy_project/gui/admin/customer_chart/TopGender.fxml"));
            }
            if (chartView != null){
                rootPane.setCenter(chartView);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
