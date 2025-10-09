package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.KhachHangDAO;
import com.example.pharmacy_project.entities.KhachHang;
import com.example.pharmacy_project.entities.NhanVien;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

public class DanhSachKhachHangController {
    @FXML
    private TableView<KhachHang> tblKhachHang;

    @FXML
    private TableColumn<KhachHang, String> colGioiTInh;

    @FXML
    private TableColumn<KhachHang, String> colHangThanhVien;

    @FXML
    private TableColumn<KhachHang, String> colMaKH;

    @FXML
    private TableColumn<KhachHang, String> colNguoiTao;

    @FXML
    private TableColumn<KhachHang, String> colSDT;

    @FXML
    private TableColumn<KhachHang, String> colTenKH;

    @FXML
    private TableColumn<KhachHang, String> colThoiGianTao;
    private final KhachHangDAO khachHangDAO = new KhachHangDAO();
    public void initialize() {
        colMaKH.setCellValueFactory(new PropertyValueFactory<>("maKhachHang"));
        colTenKH.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
        colSDT.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        colHangThanhVien.setCellValueFactory(new PropertyValueFactory<>("hangThanhVien"));
        colGioiTInh.setCellValueFactory(new PropertyValueFactory<>("gioiTinhString"));
        colThoiGianTao.setCellValueFactory(new PropertyValueFactory<>("thoiGianTao"));
        colNguoiTao.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));

        loadDuLieuKhachHang();
    }
    private void loadDuLieuKhachHang() {
        ObservableList<KhachHang> ds = FXCollections.observableArrayList(khachHangDAO.getAllTbKhachHang());
        tblKhachHang.setItems(ds);
    }
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
}
