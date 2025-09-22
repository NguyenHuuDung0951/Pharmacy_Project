package com.example.pharmacy_project.entities;

public class KhachHang {
    private String maKhachHang;
    private String soDienThoai;
    private String tenKhachHang;
    private String hangThanhVien;

    public KhachHang(String maKhachHang, String soDienThoai, String tenKhachHang, String hangThanhVien) {
        this.maKhachHang = maKhachHang;
        this.soDienThoai = soDienThoai;
        this.tenKhachHang = tenKhachHang;
        this.hangThanhVien = hangThanhVien;
    }

    public KhachHang() {
    }

    public KhachHang(KhachHang kh) {
        this.maKhachHang = kh.maKhachHang;
        this.soDienThoai = kh.soDienThoai;
        this.tenKhachHang = kh.tenKhachHang;
        this.hangThanhVien = kh.hangThanhVien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        if (!maKhachHang.matches("^KH\\d{3}"))
        {
            throw new IllegalArgumentException("Mã Khách Hàng phải đúng định dạng vd:HD001");
        }
        this.maKhachHang = maKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        if (soDienThoai.isEmpty()){
            throw new IllegalArgumentException("Số điện thoại không được để trống");
        }
        if (soDienThoai.length()!=10){
            throw new IllegalArgumentException("Số điện thoại phải 10 số");
        }
        this.soDienThoai = soDienThoai;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        if (tenKhachHang.isEmpty()){
            throw new IllegalArgumentException("Tên khách hàng không được để trống");
        }
        this.tenKhachHang = tenKhachHang;
    }

    public String getHangThanhVien() {
        return hangThanhVien;
    }

    public void setHangThanhVien(String hangThanhVien) {
        if (hangThanhVien .isEmpty()){
            throw new IllegalArgumentException("Hạng thành viên không được để trống");
        }
        this.hangThanhVien = hangThanhVien;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKhachHang='" + maKhachHang + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", tenKhachHang='" + tenKhachHang + '\'' +
                ", hangThanhVien='" + hangThanhVien + '\'' +
                '}';
    }
}
