package com.example.pharmacy_project.entities;

public class ChiTietDanhMucThuoc {
    private DanhMuc danhMuc;
    private Thuoc thuoc;

    public ChiTietDanhMucThuoc(DanhMuc danhMuc, Thuoc thuoc) {
        this.danhMuc = danhMuc;
        this.thuoc = thuoc;
    }

    public ChiTietDanhMucThuoc() {
    }
    public ChiTietDanhMucThuoc(ChiTietDanhMucThuoc ctdmt) {
        this.danhMuc = ctdmt.danhMuc;
        this.thuoc = ctdmt.thuoc;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    @Override
    public String toString() {
        return "ChiTietDanhMucThuoc{" +
                "danhMuc=" + danhMuc +
                ", thuoc=" + thuoc +
                '}';
    }
}
