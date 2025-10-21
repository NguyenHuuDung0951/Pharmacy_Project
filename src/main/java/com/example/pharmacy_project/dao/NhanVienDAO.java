/*
 * @ (#) NhanVienDAO         08 Oct 2025
 *
 *Copyright (c) 2025 IUH . All right reserved
 */
package com.example.pharmacy_project.dao;

import com.example.pharmacy_project.connectDB.ConnectDB;
import com.example.pharmacy_project.entities.NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class NhanVienDAO {
    public NhanVienDAO() {
    }
    public ArrayList<NhanVien> getAllTblNhanVien(){
        ArrayList<NhanVien> dsNv = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "Select *from NhanVien";
            Statement statement = con.createStatement();
            ResultSet rs =statement.executeQuery(sql);
            while (rs.next()){
                String maNv = rs.getString(1);
                String tenNv = rs.getString(2);
                String sdt = rs.getString(3);
                 String diaChi = rs.getString(4);
                String chucVu = rs.getString(5);


                NhanVien nv = new NhanVien(maNv,tenNv,sdt,diaChi,chucVu);
                dsNv.add(nv);
            }
        }
        catch (Exception e){
        e.printStackTrace();
        }
    return dsNv;
    }
public List<NhanVien> searchNhanVien(String keywork){
        List<NhanVien> result = new ArrayList<>();
        String sql = "SELECT * from NhanVien Where maNhanVien Like ? OR tenNhanVien Like ?";
        try(Connection con = ConnectDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ) {
            String searchPattern = "%" + keywork + "%";
            stmt.setString(1,searchPattern);
            stmt.setString(2,searchPattern);
            try(ResultSet rs = stmt.executeQuery();){
            while (rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("maNhanVien"));
                nv.setTenNhanVien(rs.getString("tenNhanVien"));
                nv.setSoDienThoai(rs.getString("soDienThoai"));
                nv.setDiaChi(rs.getString("diaChi"));
                nv.setAnhDaiDien(rs.getString("anhDaiDien"));
                result.add(nv);
            }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
}
}
