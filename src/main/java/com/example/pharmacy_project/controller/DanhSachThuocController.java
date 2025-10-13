/*
 * @ (#) DanhSachThuocController         04 Oct 2025
 *
 *Copyright (c) 2025 IUH . All right reserved
 */
package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.ThuocDAO;
import com.example.pharmacy_project.entities.Thuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/*
 * @description:  This class represents a bank with many bank accounts
 * @author: Nguyen Van Sy
 * @version: 1.0
 * @created: 04 Oct 2025 21:51
 */
public class DanhSachThuocController {
    @FXML
    private TableColumn<Thuoc, String> colDangThuoc;

    @FXML
    private TableColumn<Thuoc, String> colDonViTinh;

    @FXML
    private TableColumn<Thuoc, Double> colGiaThuoc;

    @FXML
    private TableColumn<Thuoc, String> colHamLuong;

    @FXML
    private TableColumn<Thuoc, String> colHinhAnh;

    @FXML
    private TableColumn<Thuoc, String> colMaThuoc;

    @FXML
    private TableColumn<Thuoc, String> colNhaSanXuat;

    @FXML
    private TableColumn<Thuoc, String> colTenThuoc;

    @FXML
    private TableColumn<Thuoc, String> colTrangThai;

    @FXML
    private TableView<Thuoc> tblThuoc;
    private final ThuocDAO thuocDAO = new ThuocDAO();
    public void initialize(){
        colMaThuoc.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
        colTenThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
        colHamLuong.setCellValueFactory(new PropertyValueFactory<>("hamLuong"));
        colDangThuoc.setCellValueFactory(new PropertyValueFactory<>("dangThuoc"));
        colGiaThuoc.setCellValueFactory(new PropertyValueFactory<>("giaThuoc"));
        colDonViTinh.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
        colNhaSanXuat.setCellValueFactory(new PropertyValueFactory<>("nhaSanXuat"));
        colTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        colHinhAnh.setCellValueFactory(new PropertyValueFactory<>("anhDaiDien"));

        loadDuLieuThuoc();
    }

    private void loadDuLieuThuoc() {
        ObservableList<Thuoc> ds = FXCollections.observableArrayList(thuocDAO.getAllTblThuoc());
        tblThuoc.setItems(ds);
    }
}
