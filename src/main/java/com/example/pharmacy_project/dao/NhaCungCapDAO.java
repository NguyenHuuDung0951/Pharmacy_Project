package com.example.pharmacy_project.dao;

import com.example.pharmacy_project.connectDB.ConnectDB;
import com.example.pharmacy_project.entities.KhuyenMai;
import com.example.pharmacy_project.entities.NhaCungCap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class NhaCungCapDAO {
    public NhaCungCapDAO(){}
    public ArrayList<NhaCungCap> getAllTblNhaCungCap() {
        ArrayList<NhaCungCap> dsncc = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "Select * from NhaCungCap";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNhaCungCap = rs.getString(1);
                String tenNhaCungCap = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String diaChi = rs.getString(4);

                NhaCungCap ncc = new NhaCungCap(maNhaCungCap, tenNhaCungCap, soDienThoai, diaChi);
                dsncc.add(ncc);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dsncc;
    }
}
