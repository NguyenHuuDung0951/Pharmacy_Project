package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.taiKhoanDao;

public class TestTaiKhoan {
    public static void main(String[] args) {
        taiKhoanDao dao = new taiKhoanDao();

        boolean ok = dao.addTaiKhoan("TK001", "user2", "123456", "Nhân viên", "Kích Hoạt");
        System.out.println(ok ? "Thêm user2 thành công!" : "Thêm user2 thất bại!");

        boolean ok1 = dao.addTaiKhoan("TK002", "admin", "123", "Quản lí", "Kích Hoạt");
        System.out.println(ok1 ? "Thêm admin thành công!" : "Thêm admin thất bại!");

        boolean ok2 = dao.addTaiKhoan("TK003","user1", "456", "Nhân viên", "Kích Hoạt");
        System.out.println(ok2 ? "Thêm user1 thành công!" : "Thêm user1 thất bại!");
    }
}

