package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.connectDB.ConnectDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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


    public void list_empl(ActionEvent actionEvent) {
        try {
            Parent content = FXMLLoader.load(
                    getClass().getResource("/com/example/pharmacy_project/gui/admin/list_empl.fxml")
            );
            rootPane.setCenter(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void overview(ActionEvent actionEvent) {
        try {
            Parent content = FXMLLoader.load(
                    getClass().getResource("/com/example/pharmacy_project/gui/overview.fxml")
            );
            rootPane.setCenter(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void danhSachKhachHang(ActionEvent actionEvent) {
        try {
            Parent content = FXMLLoader.load(
                    getClass().getResource("/com/example/pharmacy_project/gui/DanhSachKhachHang.fxml")
            );
            rootPane.setCenter(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void danhsachthuoc(ActionEvent actionEvent) {
        try {
            Parent loadder=FXMLLoader.load
                    (getClass().getResource("/com/example/pharmacy_project/gui/DanhSachThuoc.fxml"));
            rootPane.setCenter(loadder);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void danhSachKhuyenMai(ActionEvent actionEvent) {

    }

    public void handle_listOrder(ActionEvent actionEvent) {
        try {
            Parent loadder=FXMLLoader.load
                    (getClass().getResource("/com/example/pharmacy_project/gui/dsHoaDon.fxml"));
            rootPane.setCenter(loadder);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void handle_dsDatThuoc(ActionEvent actionEvent) {
        try {
            Parent loadder=FXMLLoader.load
                    (getClass().getResource("/com/example/pharmacy_project/gui/dsDonDat.fxml"));
            rootPane.setCenter(loadder);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
