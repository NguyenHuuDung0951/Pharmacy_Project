package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.NhanVienDAO;
import com.example.pharmacy_project.entities.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListEmplController {

    @FXML
    private TableColumn<NhanVien, String> colChucVu;

    @FXML
    private TableColumn<NhanVien, String> colDiaChi;

    @FXML
    private TableColumn<NhanVien, String> colMaNV;

    @FXML
    private TableColumn<NhanVien, String> colSDT;

    @FXML
    private TableColumn<NhanVien, String> colTenNV;

    @FXML
    private TableView<NhanVien> dsNhanVien;
    private final NhanVienDAO nvDAO = new NhanVienDAO();
    public void initialize() {
        colMaNV.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
        colTenNV.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));
        colSDT.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colChucVu.setCellValueFactory(new PropertyValueFactory<>("tenChucVu"));
        loadDuLieuNhanVien();
    }

    private void loadDuLieuNhanVien() {
        ObservableList<NhanVien> ds = FXCollections.observableArrayList(nvDAO.getAllTblNhanVien());
        dsNhanVien.setItems(ds);
    }
}
