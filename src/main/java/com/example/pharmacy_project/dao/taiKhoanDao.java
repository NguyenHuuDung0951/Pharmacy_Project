package com.example.pharmacy_project.dao;

import com.example.pharmacy_project.connectDB.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class taiKhoanDao {
    // Hàm lấy danh sách các tài khoản
    public ObservableList<String> getAllTenDangNhap(){
        ObservableList<String> list = FXCollections.observableArrayList();
        String sql = "select tenDangNhap from TaiKhoan";
        try(Connection con = ConnectDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                list.add(rs.getString("tenDangNhap"));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // Hàm kiểm tra đăng nhập
    public boolean validateLogin(String username, String password) {
        String sql = "SELECT matKhau FROM TaiKhoan WHERE tenDangNhap = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("matKhau");
                    if (hashedPassword == null || hashedPassword.trim().isEmpty())
                        return false;

                    try {
                        return BCrypt.checkpw(password, hashedPassword);
                    } catch (IllegalArgumentException ex) {
                        ex.printStackTrace();
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm thêm tài khoản mới
    public boolean addTaiKhoan(String maTaiKhoan, String username, String password, String role, String status) {
        String sql = "INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau, vaiTro, trangThai) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Hash mật khẩu trước khi lưu vào DB
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

            stmt.setString(1, maTaiKhoan);
            stmt.setString(2, username);
            stmt.setString(3, hashedPassword);
            stmt.setString(4, role);
            stmt.setString(5, status);

            int rows = stmt.executeUpdate();
            return rows > 0; // Trả về true nếu insert thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
