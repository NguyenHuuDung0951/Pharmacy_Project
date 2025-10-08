/*
 * @ (#) KhachHangDAO         08 Oct 2025
 *
 *Copyright (c) 2025 IUH . All right reserved
 */
package com.example.pharmacy_project.dao;

import com.example.pharmacy_project.connectDB.ConnectDB;
import com.example.pharmacy_project.entities.KhachHang;
import com.example.pharmacy_project.entities.NhanVien;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/*
 * @description:  This class represents a bank with many bank accounts
 * @author: Nguyen Van Sy
 * @version: 1.0
 * @created: 08 Oct 2025 13:25
 */
public class KhachHangDAO {
    public KhachHangDAO(){}
    public ArrayList<KhachHang> getAllTbKhachHang() {
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "Select * from KhachHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maKhachHang = rs.getString(1);
                String soDienThoai = rs.getString(2);
                String tenKhachHang = rs.getString(3);
                String hangThanhVien = rs.getString(4);
                boolean gioiTinh = rs.getBoolean(5);
                LocalDate thoiGianTao = rs.getDate(6).toLocalDate();
                String maNhanVien = rs.getString(7);
                NhanVien nv = new NhanVien(maNhanVien);
                KhachHang kh = new KhachHang(maKhachHang, soDienThoai, tenKhachHang, hangThanhVien, gioiTinh, thoiGianTao, nv);
                dsKhachHang.add(kh);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsKhachHang;
    }
}
