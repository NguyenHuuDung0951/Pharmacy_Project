/*
 * @ (#) $NAME.java     1.0     10/7/2025
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */
package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.KeThuocDAO;
import com.example.pharmacy_project.dao.NhaCungCapDAO;
import com.example.pharmacy_project.dao.NhanVienDAO;
import com.example.pharmacy_project.dao.NhomThuocDAO;
import com.example.pharmacy_project.entities.NhaCungCap;
import com.example.pharmacy_project.entities.NhanVien;
import com.example.pharmacy_project.entities.NhomThuoc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.util.ArrayList;

/*
 * @description:
 * @author: Khang, Le Hoang
 * @version: 1.0
 * @created: 10/7/2025 12:44 PM
 */
public class LapPhieuNhapController {
    @FXML
    private ComboBox<NhaCungCap> cbxNhaCungCap;

    @FXML
    private ComboBox<NhanVien> cbxNhanVien;

    private final NhaCungCapDAO ncc = new NhaCungCapDAO();
    private final NhanVienDAO nv = new NhanVienDAO();
    public void initialize(){
        loadcbxNhaCungCap();
        loadcbxNhanVien();
        cbxNhanVien.setConverter(new StringConverter<>() {
            @Override public String toString(NhanVien nv) { return nv == null ? "" : nv.getTenNhanVien(); }
            @Override public NhanVien fromString(String s) { return null; } // không dùng
        });
        cbxNhaCungCap.setConverter(new StringConverter<>() {
            @Override public String toString(NhaCungCap ncc) { return ncc == null ? "" : ncc.getTenNhaCungCap(); }

            @Override
            public NhaCungCap fromString(String s) {
                return null;
            }
        });
    }

    private void loadcbxNhanVien() {
        ArrayList<NhanVien> dsNhanVien = nv.getAllTblNhanVien();
        cbxNhanVien.getItems().setAll(dsNhanVien);
    }

    private void loadcbxNhaCungCap() {
        ArrayList<NhaCungCap> dsNhaCungCap = ncc.getAllTblNhaCungCap();
        cbxNhaCungCap.getItems().setAll(dsNhaCungCap);
    }
}
