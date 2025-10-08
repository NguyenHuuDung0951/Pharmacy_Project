package com.example.pharmacy_project.entities;

public class NhanVien {
    private String maNhanVien;
    private String tenNhanVien;
    private String soDienThoai;
    private String diaChi;
    private String chucVu;

    public NhanVien(String maNhanVien, String tenNhanVien, String soDienThoai, String diaChi, String chucVu) {
        this.maNhanVien = maNhanVien;
        setMaNhanVien(maNhanVien);
        this.tenNhanVien = tenNhanVien;
        setTenNhanVien(tenNhanVien);
        this.soDienThoai = soDienThoai;
        setSoDienThoai(soDienThoai);
        this.diaChi = diaChi;
        setDiaChi(diaChi);
        this.chucVu = chucVu;
    }

    public NhanVien(NhanVien nv) {
        this.maNhanVien = nv.maNhanVien;
        this.tenNhanVien = nv.tenNhanVien;
        this.soDienThoai = nv.soDienThoai;
        this.diaChi = nv.diaChi;
        this.chucVu = nv.chucVu;
    }

    public NhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setMaNhanVien(String maNhanVien) {
        if (!maNhanVien.matches("^NV\\d{3}")){
            throw new IllegalArgumentException("Mã nhân viên bắt đầu bằng NV và theo sau 3 số vd: NV001");
        }
        this.maNhanVien = maNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        if (tenNhanVien.isEmpty() || tenNhanVien.length() < 2 || tenNhanVien.length() >50){
            throw new IllegalArgumentException("Tên Nhân Viên không được rỗng và từ 2-50 ký tự");
        }
        this.tenNhanVien = tenNhanVien;
    }

    public void setSoDienThoai(String soDienThoai) {
        if (soDienThoai.isEmpty() || soDienThoai.matches("0\\d{9}")){
            throw new IllegalArgumentException("Số điện thoại bắt đầu 0 và 10 số");
        }
        this.soDienThoai = soDienThoai;
    }

    public void setDiaChi(String diaChi) {
        if (diaChi.isEmpty()){
            throw new IllegalArgumentException("Địa chỉ không được rỗng");
        }
        this.diaChi = diaChi;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanVien='" + maNhanVien + '\'' +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", chucVu='" + chucVu + '\'' +
                '}';
    }
}
