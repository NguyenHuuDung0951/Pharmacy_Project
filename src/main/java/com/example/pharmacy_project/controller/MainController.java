package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.connectDB.ConnectDB;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.SQLException;

public class MainController {
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
    @FXML
    private MenuBar menuBar;
    @FXML
    private Connection connection;

    @FXML
    public void initialize() {
        try {
            connection = ConnectDB.getConnection();
            System.out.println("Kết nối CSDL thành công: " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

            // Bắt hover cho từng Menu
            for (Menu menu : menuBar.getMenus()) {
                menu.setOnMenuValidation(event -> {
                    // không làm gì
                });

                menu.setOnShowing(e -> {
                    // khi mở menu này thì đóng menu khác
                    for (Menu other : menuBar.getMenus()) {
                        if (other != menu) other.hide();
                    }
                });

                // Hover thì mở menu
                menu.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    menu.show();
                });
            }


    }
    public void closeConnection() {
        ConnectDB.getInstance().disconnect();
        System.out.println("Đã ngắt kết nối CSDL");
    }
    public Connection getConnection() {
        return connection;
    }


}
