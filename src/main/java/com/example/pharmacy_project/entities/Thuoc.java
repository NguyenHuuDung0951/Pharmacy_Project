package com.example.pharmacy_project.entities;

public class Thuoc {
    private String maThuoc;
    private String tenThuoc;
    private String hamLuong;
    private String dangThuoc;
    private Double giaThuoc;
    private String donViTinh;
    private Boolean yeuCauKeDon;
    private String nhaSanXuat;
    private TrangThai trangThai;

    public Thuoc(String maThuoc, String tenThuoc, String hamLuong, String dangThuoc, Double giaThuoc, String donViTinh, Boolean yeuCauKeDon, String nhaSanXuat, TrangThai trangThai) {
        this.maThuoc = maThuoc;
        setMaThuoc(maThuoc);
        this.tenThuoc = tenThuoc;
        setTenThuoc(tenThuoc);
        this.hamLuong = hamLuong;
        setHamLuong(hamLuong);
        this.dangThuoc = dangThuoc;
        setDangThuoc(dangThuoc);
        this.giaThuoc = giaThuoc;
        setGiaThuoc(giaThuoc);
        this.donViTinh = donViTinh;
        setGiaThuoc(giaThuoc);
        this.yeuCauKeDon = yeuCauKeDon;
        this.nhaSanXuat = nhaSanXuat;
        this.trangThai = trangThai;
    }

    public Thuoc() {
    }
    public Thuoc(Thuoc other) {
        this.maThuoc = other.maThuoc;
        this.tenThuoc = other.tenThuoc;
        this.hamLuong = other.hamLuong;
        this.dangThuoc = other.dangThuoc;
        this.giaThuoc = other.giaThuoc;
        this.donViTinh = other.donViTinh;
        this.yeuCauKeDon = other.yeuCauKeDon;
        this.nhaSanXuat = other.nhaSanXuat;
        this.trangThai = other.trangThai;
    }

    public void setMaThuoc(String maThuoc) {
        if(!maThuoc.matches("^TH\\d{3}")){
            throw new IllegalArgumentException("Mã nhân viên không hợp lệ! (Đúng dạng NVxxx, ví dụ NV001)");
        }
        this.maThuoc = maThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        if (tenThuoc.isEmpty() || tenThuoc.isBlank() || tenThuoc.length() <= 0){
            throw new IllegalArgumentException("Tên thuốc không được bỏ trống!");
        }
        if(tenThuoc.length() <2 || tenThuoc.length()>50){
            throw new IllegalArgumentException("Tên thuốc phải từ 2-50 ký tự!");
        }
        this.tenThuoc = tenThuoc;
    }

    public void setHamLuong(String hamLuong) {
        if(hamLuong.length() <2 || hamLuong.length()>50){
            throw new IllegalArgumentException("Hàm Lượng phải từ 2-50 ký tự!");
        }
        if (hamLuong.isBlank() || hamLuong.isEmpty()){
            throw new IllegalArgumentException("Hàm lượng không được để trống");
        }
        this.hamLuong = hamLuong;
    }

    public void setDangThuoc(String dangThuoc) {
        this.dangThuoc = dangThuoc;
    }

    public void setGiaThuoc(Double giaThuoc) {
        if (giaThuoc <= 0 ){
            throw new IllegalArgumentException("Giá thuốc phải >=0");
        }
        this.giaThuoc = giaThuoc;
    }

    public void setDonViTinh(String donViTinh) {
        if (donViTinh.isEmpty() || donViTinh.isBlank()){
            throw new IllegalArgumentException("Đơn vị tính không được để trống");
        }
        this.donViTinh = donViTinh;
    }

    public void setYeuCauKeDon(Boolean yeuCauKeDon) {
        this.yeuCauKeDon = yeuCauKeDon;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public void setTrangThai(TrangThai trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public String getHamLuong() {
        return hamLuong;
    }

    public String getDangThuoc() {
        return dangThuoc;
    }

    public Double getGiaThuoc() {
        return giaThuoc;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public Boolean getYeuCauKeDon() {
        return yeuCauKeDon;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public TrangThai getTrangThai() {
        return trangThai;
    }

    @Override
    public String toString() {
        return "Thuoc{" +
                "maThuoc='" + maThuoc + '\'' +
                ", tenThuoc='" + tenThuoc + '\'' +
                ", hamLuong='" + hamLuong + '\'' +
                ", dangThuoc='" + dangThuoc + '\'' +
                ", giaThuoc=" + giaThuoc +
                ", donViTinh='" + donViTinh + '\'' +
                ", yeuCauKeDon=" + yeuCauKeDon +
                ", nhaSanXuat='" + nhaSanXuat + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}
