package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.KhachHangDAO;
import com.example.pharmacy_project.dao.NhaCungCapDAO;
import com.example.pharmacy_project.entities.KhachHang;
import com.example.pharmacy_project.entities.NhaCungCap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DsNhaCungCapController {

    @FXML
    private TableColumn<NhaCungCap, String> colDiaChi;
    private NhaCungCapDAO ncdao=new NhaCungCapDAO();

    @FXML
    private TableColumn<NhaCungCap, String> colMaNhaCungCap;

    @FXML
    private TableColumn<NhaCungCap, String> colSoDienThoai;

    @FXML
    private TableColumn<NhaCungCap, String> colTenNhaCungCap;

    @FXML
    private TableView<NhaCungCap> tblNhaCungCap;
    public void initialize() {
        colMaNhaCungCap.setCellValueFactory(new PropertyValueFactory<>("maNhaCungCap"));
        colTenNhaCungCap.setCellValueFactory(new PropertyValueFactory<>("tenNhaCungCap"));
        colSoDienThoai.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        loadDuLieuNhaCungCap();
    }

    private void loadDuLieuNhaCungCap() {
        ObservableList<NhaCungCap> ds = FXCollections.observableArrayList(ncdao.getAllTblNhaCungCap());
        tblNhaCungCap.setItems(ds);
    }

}

