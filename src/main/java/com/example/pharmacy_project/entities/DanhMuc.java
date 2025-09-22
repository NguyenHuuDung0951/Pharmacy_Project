package com.example.pharmacy_project.entities;

public class DanhMuc {
    private String maDanhMuc;
    private String tenDanhMuc;
    private String moTa;

    public DanhMuc(String maDanhMuc, String tenDanhMuc, String moTa) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.moTa = moTa;
    }

    public DanhMuc() {
    }
    public DanhMuc(DanhMuc dm) {
        this.maDanhMuc = dm.maDanhMuc;
        this.tenDanhMuc = dm.tenDanhMuc;
        this.moTa = dm.moTa;
    }
    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "DanhMuc{" +
                "maDanhMuc='" + maDanhMuc + '\'' +
                ", tenDanhMuc='" + tenDanhMuc + '\'' +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
