package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.KhuyenMaiDAO;
import com.example.pharmacy_project.entities.KhuyenMai;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class DanhSachKhuyenMaiController {

    @FXML
    private TableColumn<KhuyenMai, LocalDate> colBatDau;

    @FXML
    private TableColumn<KhuyenMai, Integer> colDieuKien;

    @FXML
    private TableColumn<KhuyenMai, LocalDate> colKetThuc;

    @FXML
    private TableColumn<KhuyenMai, String> colMa;

    @FXML
    private TableColumn<KhuyenMai, Double> colPhanTram;

    @FXML
    private TableColumn<KhuyenMai, String> colTen;

    @FXML
    private TableView<KhuyenMai> tblKhuyenMai;
    private final KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
    public void initialize() {
        colMa.setCellValueFactory(new PropertyValueFactory<>("maKM"));
        colTen.setCellValueFactory(new PropertyValueFactory<>("tenCT"));
        colBatDau.setCellValueFactory(new PropertyValueFactory<>("tuNgay"));
        colKetThuc.setCellValueFactory(new PropertyValueFactory<>("denNgay"));
        colPhanTram.setCellValueFactory(new PropertyValueFactory<>("phanTramGiamGia"));
        colDieuKien.setCellValueFactory(new PropertyValueFactory<>("dieuKienApDung"));
        loadDuLieuKhuyenMai();

    }

    private void loadDuLieuKhuyenMai() {
        ObservableList<KhuyenMai> ds = FXCollections.observableArrayList(kmDAO.getAllTblKhuyenMai());
        tblKhuyenMai.setItems(ds);
    }
}
