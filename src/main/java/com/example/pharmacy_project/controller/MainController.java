package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.connectDB.ConnectDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.SQLException;

public class MainController {

    // Ánh xạ các node trong MainView.fxml
    @FXML
    private BorderPane rootPane;
    @FXML
    private VBox sideMenu;
    @FXML
    private Label lblHome;
    @FXML
    private Button btnAction;
    @FXML
    private MenuButton menuBtn;
    @FXML
    private AnchorPane centerPane;

    private Connection connection;

    @FXML
    public void initialize() {
        // Khi MainView load lên thì kết nối DB
        try {
            connection = ConnectDB.getConnection();
            System.out.println("Kết nối CSDL thành công: " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void closeConnection() {
        ConnectDB.getInstance().disconnect();
        System.out.println("Đã ngắt kết nối CSDL");
    }
    private void showHome() {
        // Khi click Home → load nội dung vào centerPane
        centerPane.getChildren().clear();
        Label label = new Label("Đây là trang Home");
        label.setStyle("-fx-font-size: 20px; -fx-text-fill: blue;");
        centerPane.getChildren().add(label);
        AnchorPane.setTopAnchor(label, 20.0);
        AnchorPane.setLeftAnchor(label, 20.0);
    }

    public Connection getConnection() {
        return connection;
    }


}
