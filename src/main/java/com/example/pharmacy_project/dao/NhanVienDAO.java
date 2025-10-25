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
                String anhDaiDien = rs.getString(5);


                NhanVien nv = new NhanVien(maNv,tenNv,sdt,diaChi,anhDaiDien);
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
public boolean insertNhanVien(NhanVien nv){
                String sql = """
                INSERT INTO NhanVien(
                    maNhanVien, tenNhanVien, soDienThoai, diaChi, anhDaiDien, maChucVu, maTaiKhoan
                )
                VALUES (
                    ?, ?, ?, ?, ?,
                    (SELECT maChucVu FROM ChucVu WHERE tenChucVu = ?),
                    (SELECT maTaiKhoan FROM TaiKhoan WHERE tenDangNhap = ?)
                )
            """;

    try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nv.getMaNhanVien());
            stmt.setString(2, nv.getTenNhanVien());
            stmt.setString(3, nv.getSoDienThoai());
            stmt.setString(4,nv.getDiaChi());
            stmt.setString(5,nv.getAnhDaiDien());
            stmt.setString(6, nv.getChucVu());
            stmt.setString(7,nv.getTaiKhoan());

            int rows = stmt.executeUpdate();
            return  rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
}
    public String generateMaNV(){
        String nextMa = "NV001";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "SELECT TOP 1 maNhanVien FROM NhanVien ORDER BY maNhanVien DESC";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                String lastMa = rs.getString("maNhanVien");
                if (lastMa.matches("^NV\\d{3}$")){
                    int so = Integer.parseInt(lastMa.substring(2));
                    so++;
                    nextMa = String.format("NV%03d",so);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return nextMa;
    }
}
