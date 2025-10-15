/*
 * @ (#) $NAME.java     1.0     10/15/2025
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */
package com.example.pharmacy_project.dao;

import com.example.pharmacy_project.connectDB.ConnectDB;
import com.example.pharmacy_project.entities.KhuyenMai;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/*
 * @description:
 * @author: Khang, Le Hoang
 * @version: 1.0
 * @created: 10/15/2025 1:21 AM
 */
public class KhuyenMaiDAO {
    public KhuyenMaiDAO(){}
    public ArrayList<KhuyenMai> getAllTblKhuyenMai() {
        ArrayList<KhuyenMai> dsKM = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "Select * from KhuyenMai";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maKhuyenMai = rs.getString(1);
                String tenChuongTrinh = rs.getString(2);
                LocalDate tuNgay = rs.getDate(3).toLocalDate();
                LocalDate denNgay = rs.getDate(4).toLocalDate();
                double phanTramGiamGia = rs.getDouble(5);
                int dieuKienApDung = rs.getInt(6);
                KhuyenMai km = new KhuyenMai(maKhuyenMai, tenChuongTrinh, tuNgay, denNgay, phanTramGiamGia, dieuKienApDung);
                dsKM.add(km);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dsKM;
    }
}
