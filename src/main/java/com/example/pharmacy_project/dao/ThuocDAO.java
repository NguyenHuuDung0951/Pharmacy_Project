/*
 * @ (#) $NAME.java     1.0     10/12/2025
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */
package com.example.pharmacy_project.dao;

import com.example.pharmacy_project.connectDB.ConnectDB;
import com.example.pharmacy_project.entities.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ThuocDAO {
    public ThuocDAO(){}
    public ArrayList<Thuoc> getAllTblThuoc() {
        ArrayList<Thuoc> dsThuoc = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "Select * from Thuoc";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maThuoc = rs.getString(1);
                String tenThuoc = rs.getString(2);
                String hamLuong = rs.getString(3);
                String dangThuoc = rs.getString(4);
                double giaThuoc = rs.getDouble(5);
                String donViTinh = rs.getString(6);
                String nhaSanXuat = rs.getString(7);
                String trangThai = rs.getString(8);
                String anhDaiDien = rs.getString(9);
                String maKe = rs.getString(10);
                String maNhanVien = rs.getString(11);
                String maNhom = rs.getString(12);
                KeThuoc keThuoc = new KeThuoc(maKe);
                NhanVien nv = new NhanVien(maNhanVien);
                NhomThuoc nhomThuoc = new NhomThuoc(maNhom);
                Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, hamLuong, dangThuoc, giaThuoc, donViTinh, nhaSanXuat, trangThai, anhDaiDien, keThuoc, nv, nhomThuoc);
                dsThuoc.add(thuoc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsThuoc;
    }
}