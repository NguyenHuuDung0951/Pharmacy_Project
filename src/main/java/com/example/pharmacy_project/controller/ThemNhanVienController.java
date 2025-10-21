package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.ChucVuDao;
import com.example.pharmacy_project.dao.taiKhoanDao;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ThemNhanVienController {

    @FXML
    private ComboBox<String> cmbChucVu;
    @FXML
    private ComboBox<String> cmbTaiKhoan;
    private ChucVuDao chucVuDao = new ChucVuDao();
    private taiKhoanDao tkdao = new taiKhoanDao();

    @FXML
    public void initialize() {
        cmbChucVu.setItems(chucVuDao.getAllChucVu());
        cmbTaiKhoan.setItems(tkdao.getAllTenDangNhap());
    }
}
