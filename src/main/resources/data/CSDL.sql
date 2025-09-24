create database QLNHATHUOC
use QLNHATHUOC
create table NhanVien
(
	maNhanVien varchar(10) primary key,
	tenNhanVien nvarchar(50) not null,
	soDienThoai varchar(11) not null,
	diaChi nvarchar(50) not null,
	chucVu nvarchar(20) check (chucVu in ('Qu?n Lý','Bán Hàng'))
)
create table NhaCungCap
(
	maNhaCungCap varchar(10) primary key ,
	tenNhaCungCap nvarchar(50) not null,
	soDienThoai varchar(11) not null,
	diaChi nvarchar(50) not null
)
create table PhieuNhap 
(
	maPhieuNhap varchar (10) primary key,
	ngayNhap date not null,
	maNhaCungCap varchar(10) not null,
	maNhanVien varchar(10) not null,
	foreign key (maNhanVien) references NhanVien(maNhanVien),
	foreign key (maNhaCungCap) references NhaCungCap(maNhaCungCap)
)

create table LoThuoc
(
	maLoThuoc varchar (10) primary key,
	soLo varchar(10) not null,
	ngaySanXuat date not null,
	ngayHetHan date not null,
	giaNhap float not null,
	soLuongTon int not null

)

create table ChiTietPhieuNhap 
(
	maChiTietPhieuNhap varchar(10) primary key,
	soLuong int not null,
	donGiaNhap float not null,
	maPhieuNhap varchar(10),
	maLoThuoc varchar(10),
	foreign key(maPhieuNhap) references PhieuNhap(maPhieuNhap),
	foreign key(maLoThuoc) references LoThuoc(maLoThuoc),

)

create table PhieuDoiTra
(
	maDoiTra varchar(10) primary key,
	thoiDiem date not null,
	lyDo nvarchar(200) not null,
	loaiDoiTra nvarchar(20) check (loaiDoiTra in ('??i','Tr?')),
	maNhanVien varchar(10),
	foreign key(maNhanVien) references NhanVien(maNhanVien),
)
create table ChiTietDoiTra
(
	maChiTietDoiTra varchar(20) primary key,
	soLuong int not null,
	maDoiTra varchar(10),
	maLoThuoc varchar(10),
	foreign key(maDoiTra) references PhieuDoiTra(maDoiTra),
	foreign key(maLoThuoc) references LoThuoc(maLoThuoc)
)
create table KhuyenMai
(
	maKhuyenMai varchar(20) primary key,
	tenChuongTrinh nvarchar(50) not null,
	tuNgay date not null,
	denNgay date not null,
	phanTramGiamGia float not null,
	dieuKienApDung int not null
)
create table Thue
(
	maThue varchar(20) primary key,
	tenThue nvarchar(50) not null,
	hieuLucTu date not null,
	hetHieuLuc date not null
)
create table KeThuoc
(
	maKe varchar(20) primary key,
	viTri nvarchar(50) not null,
	loaiKe nvarchar(20) not null,
	sucChua int not null
)
create table DanhMucThuoc
(
	maDanhMuc varchar(20) primary key,
	tenDanhMuc nvarchar(50) not null,
	moTa nvarchar(20) not null
)

create table Thuoc
(
	maThuoc varchar(20) primary key,
	tenThuoc nvarchar(50) not null,
	hamLuong nvarchar(20) not null,
	dangThuoc nvarchar(30) not null,
	giaThuoc float not null,
	donViTinh varchar(20) not null,
	yeuCauKeDon bit not null,
	nhaSanXuat nvarchar(50) not null,
	trangThai nvarchar(20) check (trangThai in ('?ang Kinh Doanh','Ng?ng Kinh Doanh')),
	maKhuyenMai varchar(20) not null,
	maThue varchar(20) not null,
	maKe varchar(20) not null,
	maDanhMuc varchar(20) not null,
	maNhanVien varchar(10) not null,
	foreign key(maKhuyenMai) references KhuyenMai(maKhuyenMai),
	foreign key(maThue) references Thue(maThue),
	foreign key(maKe) references KeThuoc(maKe),
	foreign key(maDanhMuc) references DanhMucThuoc(maDanhMuc),
	foreign key(maNhanVien) references NhanVien(maNhanVien)
)
create table ChiTietDanhMucThuoc
(
	maDanhMuc varchar(20),
	maThuoc varchar(20),
	foreign key (maDanhMuc) references DanhMucThuoc(maDanhMuc),
	foreign key (maThuoc) references Thuoc(maThuoc) 
)
create table KhachHang
(
	maKhachHang varchar(20) primary key,
	soDienThoai varchar(11) not null,
	tenKhachHang nvarchar(50) not null,
	hangThanhVien varchar(20) not null
)
create table HoaDon
(
	maHoaDon varchar(20) primary key,
	ngayLap date not null,
	phuongThucThanhToan nvarchar(20) not null,
	maKhachHang varchar(20) not null,
	maNhanVien varchar(10) not null,
	foreign key(maKhachHang) references KhachHang(maKhachHang),
	foreign key(maNhanVien) references NhanVien(maNhanVien)
)
create table ChiTietHoaDon
(
	maChiTietHoaDon varchar(20) primary key,
	soLuong int not null,
	tienThue float not null,
	tienGiam float not null ,
	maHoaDon varchar(20) not null,
	maThuoc varchar(20) not null,
	foreign key(maHoaDon) references HoaDon(maHoaDon),
	foreign key(maThuoc) references Thuoc(maThuoc)
)
create table TaiKhoan
(
	tenDangNhap varchar(20) primary key,
	matKhau varchar(255) not null,
	vaiTro varchar(40) not null,
	trangThai varchar(20) check ( trangThai in('Kích Ho?t','Khóa')),
	maNhanVien varchar(10) not null,
	foreign key(maNhanVien) references NhanVien(maNhanVien)
)
-- ==========================
-- 1. Insert NhanVien
-- ==========================
INSERT INTO NhanVien (maNhanVien, tenNhanVien, soDienThoai, diaChi, chucVu)
VALUES 
('NV001', N'Nguyễn Văn An', '0901234567', N'123 Lê Lợi, Quận 1', N'Quản Lý'),
('NV002', N'Trần Thị Bình', '0912345678', N'456 Trần Hưng Đạo, Quận 5', N'Bán Hàng'),
('NV003', N'Lê Văn Cường', '0923456789', N'789 Nguyễn Trãi, Quận 10', N'Bán Hàng'),
('NV004', N'Phạm Thị Dung', '0934567890', N'12 Cách Mạng Tháng 8, Quận 3', N'Quản Lý');

-- ==========================
-- 2. Insert NhaCungCap
-- ==========================
INSERT INTO NhaCungCap (maNhaCungCap, tenNhaCungCap, soDienThoai, diaChi)
VALUES
('NCC01', N'Công ty Dược Hòa Bình', '0987654321', N'12 Nguyễn Huệ, Quận 1'),
('NCC02', N'Công ty Dược Phương Nam', '0978123456', N'34 Điện Biên Phủ, Quận 3');

-- ==========================
-- 3. Insert KhuyenMai
-- ==========================
INSERT INTO KhuyenMai (maKhuyenMai, tenChuongTrinh, tuNgay, denNgay, phanTramGiamGia, dieuKienApDung)
VALUES
('KM01', N'Tết 2025', '2025-01-01', '2025-02-01', 10, 500000),
('KM02', N'Hè 2025', '2025-06-01', '2025-06-30', 15, 300000);

-- ==========================
-- 4. Insert Thue
-- ==========================
INSERT INTO Thue (maThue, tenThue, hieuLucTu, hetHieuLuc)
VALUES
('T01', N'Thuế VAT 5%', '2024-01-01', '2025-12-31'),
('T02', N'Thuế VAT 8%', '2024-01-01', '2025-12-31');

-- ==========================
-- 5. Insert KeThuoc
-- ==========================
INSERT INTO KeThuoc (maKe, viTri, loaiKe, sucChua)
VALUES
('K01', N'Tầng 1 - Khu A', N'Kệ Đứng', 100),
('K02', N'Tầng 1 - Khu B', N'Kệ Treo', 80);

-- ==========================
-- 6. Insert DanhMucThuoc
-- ==========================
INSERT INTO DanhMucThuoc (maDanhMuc, tenDanhMuc, moTa)
VALUES
('DM01', N'Thuốc Kháng Sinh', N'Thuốc chống nhiễm khuẩn'),
('DM02', N'Thuốc Giảm Đau', N'Thuốc giảm đau hạ sốt');

-- ==========================
-- 7. Insert KhachHang
-- ==========================
INSERT INTO KhachHang (maKhachHang, soDienThoai, tenKhachHang, hangThanhVien)
VALUES
('KH001', '0909876543', N'Nguyễn Văn Hùng', 'Vàng'),
('KH002', '0911223344', N'Lê Thị Hoa', 'Bạc');

-- ==========================
-- 8. Insert Thuoc (tham chiếu nhiều bảng)
-- ==========================
INSERT INTO Thuoc (maThuoc, tenThuoc, hamLuong, dangThuoc, giaThuoc, donViTinh, yeuCauKeDon, nhaSanXuat, trangThai, maKhuyenMai, maThue, maKe, maDanhMuc, maNhanVien)
VALUES
('T001', N'Amoxicillin', N'500mg', N'Viên nén', 2000, 'Viên', 1, N'VN Pharma', N'Đang Kinh Doanh', 'KM01', 'T01', 'K01', 'DM01', 'NV002'),
('T002', N'Paracetamol', N'500mg', N'Viên nén', 1500, 'Viên', 0, N'Mekophar', N'Đang Kinh Doanh', 'KM02', 'T02', 'K02', 'DM02', 'NV003');

-- ==========================
-- 9. Insert LoThuoc
-- ==========================
INSERT INTO LoThuoc (maLoThuoc, soLo, ngaySanXuat, ngayHetHan, giaNhap, soLuongTon)
VALUES
('L001', 'LO123', '2025-01-01', '2026-01-01', 1000, 500),
('L002', 'LO124', '2025-02-01', '2026-02-01', 1200, 300);

-- ==========================
-- 10. Insert ChiTietDanhMucThuoc
-- ==========================
INSERT INTO ChiTietDanhMucThuoc (maDanhMuc, maThuoc)
VALUES
('DM01', 'T001'),
('DM02', 'T002');

-- ==========================
-- 11. Insert PhieuNhap
-- ==========================
INSERT INTO PhieuNhap (maPhieuNhap, ngayNhap, maNhaCungCap, maNhanVien)
VALUES
('PN001', '2025-09-01', 'NCC01', 'NV001'),
('PN002', '2025-09-02', 'NCC02', 'NV004');

-- ==========================
-- 12. Insert HoaDon
-- ==========================
INSERT INTO HoaDon (maHoaDon, ngayLap, phuongThucThanhToan, maKhachHang, maNhanVien)
VALUES
('HD001', '2025-09-01', N'Tiền mặt', 'KH001', 'NV002'),
('HD002', '2025-09-02', N'Chuyển khoản', 'KH002', 'NV003');

-- ==========================
-- 13. Insert PhieuDoiTra
-- ==========================
INSERT INTO PhieuDoiTra (maDoiTra, thoiDiem, lyDo, loaiDoiTra, maNhanVien)
VALUES
('DT001', '2025-09-10', N'Thuốc bị hỏng bao bì', N'Đổi', 'NV002'),
('DT002', '2025-09-12', N'Hết hạn sử dụng', N'Trả', 'NV003');

-- ==========================
-- 14. Insert ChiTietPhieuNhap
-- ==========================
INSERT INTO ChiTietPhieuNhap (maChiTietPhieuNhap, soLuong, donGiaNhap, maPhieuNhap, maLoThuoc)
VALUES
('CTPN01', 100, 1000, 'PN001', 'L001'),
('CTPN02', 200, 1200, 'PN002', 'L002');

-- ==========================
-- 15. Insert ChiTietHoaDon
-- ==========================
INSERT INTO ChiTietHoaDon (maChiTietHoaDon, soLuong, tienThue, tienGiam, maHoaDon, maThuoc)
VALUES
('CTHD01', 2, 200, 100, 'HD001', 'T001'),
('CTHD02', 1, 120, 50, 'HD002', 'T002');

-- ==========================
-- 16. Insert ChiTietDoiTra
-- ==========================
INSERT INTO ChiTietDoiTra (maChiTietDoiTra, soLuong, maDoiTra, maLoThuoc)
VALUES
('CTDT01', 10, 'DT001', 'L001'),
('CTDT02', 5, 'DT002', 'L002');

-- ==========================
-- 17. Insert TaiKhoan (sau cùng vì FK tới NhanVien)
-- ==========================
-- BCrypt hash cho mật khẩu '123' là:
-- $2a$10$3oYb3Tgj3v92hF7Gv9O2I.4dHjYqN81ZpM7Z9NtnnCD7cDNbz65Ai
INSERT INTO TaiKhoan (tenDangNhap, matKhau, vaiTro, trangThai, maNhanVien)
VALUES
('admin', '$2a$10$3oYb3Tgj3v92hF7Gv9O2I.4dHjYqN81ZpM7Z9NtnnCD7cDNbz65Ai', 'Quản trị', 'Kích Hoạt', 'NV001'),
('user1', '$2a$10$3oYb3Tgj3v92hF7Gv9O2I.4dHjYqN81ZpM7Z9NtnnCD7cDNbz65Ai', 'Nhân viên', 'Kích Hoạt', 'NV002');

ALTER TABLE NhanVien DROP CONSTRAINT CK__NhanVien__chucVu__37A5467C;

ALTER TABLE NhanVien
ADD CONSTRAINT CK_NhanVien_ChucVu
CHECK (chucVu IN (N'Quản Lý', N'Bán Hàng'));
INSERT INTO NhanVien (maNhanVien, tenNhanVien, soDienThoai, diaChi, chucVu)
VALUES
('NV001', N'Nguyễn Văn A', '0901234567', N'Hà Nội', N'Quản Lý'),
('NV002', N'Trần Thị B', '0912345678', N'Hồ Chí Minh', N'Bán Hàng'),
('NV003', N'Lê Văn C', '0923456789', N'Đà Nẵng', N'Bán Hàng'),
('NV004', N'Phạm Thị D', '0934567890', N'Cần Thơ', N'Quản Lý');
