create database QLNHATHUOC
GO
use QLNHATHUOC
GO
create table ChucVu
(
	maChucVu varchar(20) primary key,
	tenChucVu nvarchar(20) not null
)

create table TaiKhoan
(
	maTaiKhoan varchar(20) primary key,
	tenDangNhap varchar(20) not null,
	matKhau varchar(150) not null,
	vaiTro varchar(40) not null,
	trangThai varchar(20) check ( trangThai in('Kích Hoạt','Khóa')),
)

create table NhanVien
(
	maNhanVien varchar(20) primary key,
	tenNhanVien nvarchar(50) not null,
	soDienThoai varchar(11) not null,
	diaChi nvarchar(50) not null,
	anhDaiDien varchar(100) not null,
	maChucVu varchar(20) not null,
	maTaiKhoan varchar(20) not null,
	foreign key (maChucVu) references ChucVu(maChucVu),
	foreign key (maTaiKhoan) references TaiKhoan(maTaiKhoan)
)
create table NhaCungCap
(
	maNhaCungCap varchar(20) primary key ,
	tenNhaCungCap nvarchar(50) not null,
	soDienThoai varchar(11) not null,
	diaChi nvarchar(50) not null
)
create table PhieuNhap 
(
	maPhieuNhap varchar (20) primary key,
	ngayNhap date not null,
	trangThai nvarchar(20) not null check (trangThai in ('Phiếu tạm', 'Đã nhập hàng', 'Đã hủy')),
	maNhaCungCap varchar(20) not null,
	maNhanVien varchar(20) not null,
	foreign key (maNhanVien) references NhanVien(maNhanVien),
	foreign key (maNhaCungCap) references NhaCungCap(maNhaCungCap)
)

create table LoThuoc
(
	maLoThuoc varchar (20) primary key,
	soLo varchar(10) not null,
	ngaySanXuat date not null,
	ngayHetHan date not null,
	giaNhap float not null,
	soLuongTon int not null,
	tenLo nvarchar(20) not null
)

create table ChiTietPhieuNhap 
(
	soLuong int not null,
	donGiaNhap float not null,
	maPhieuNhap varchar(20),
	maLoThuoc varchar(20),
	primary key (maPhieuNhap, maLoThuoc),
	foreign key (maPhieuNhap) references PhieuNhap(maPhieuNhap),
	foreign key (maLoThuoc) references LoThuoc(maLoThuoc)
)
create table KhachHang
(
	maKhachHang varchar(20) primary key,
	soDienThoai varchar(11) not null,
	tenKhachHang nvarchar(50) not null,
	hangThanhVien varchar(20) not null,
	gioiTinh bit not null,
	thoiGianTao date not null,
	maNhanVien varchar(20) not null,
	foreign key (maNhanVien) references NhanVien(maNhanVien)
)
create table PhieuDoiTra
(
	maDoiTra varchar(20) primary key,
	thoiDiem date not null,
	lyDo nvarchar(200) not null,
	loaiDoiTra nvarchar(20) check (loaiDoiTra in ('Đổi','Trả')),
	thoiGianTao date not null,
	maNhanVien varchar(20),
	maKhachHang varchar(20),
	foreign key(maNhanVien) references NhanVien(maNhanVien),
	foreign key (maKhachHang) references KhachHang(maKhachHang)
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
	hetHieuLuc date not null,
	phanTramThue float not null
)
create table KeThuoc
(
	maKe varchar(20) primary key,
	viTri nvarchar(50) not null,
	loaiKe nvarchar(20) not null,
	sucChua int not null
)

create table NhomThuoc
(
	maNhom varchar(20) primary key,
	tenNhom nvarchar(100) not null
)
create table Thuoc
(
	maThuoc varchar(20) primary key,
	tenThuoc nvarchar(50) not null,
	hamLuong nvarchar(20) not null,
	dangThuoc nvarchar(30) not null,
	giaThuoc float not null,
	donViTinh varchar(20) not null,
	nhaSanXuat nvarchar(50) not null,
	trangThai nvarchar(20) check (trangThai in ('Đang Kinh Doanh','Ngừng Kinh Doanh')),
	anhDaiDien varchar(100) not null,
	maThue varchar(20) not null,
	maKe varchar(20) not null,
	maNhanVien varchar(20) not null,
	maLoThuoc varchar(20) not null,
	maNhom varchar(20) not null,
	foreign key (maNhom) references NhomThuoc(maNhom),
	foreign key(maThue) references Thue(maThue),
	foreign key(maKe) references KeThuoc(maKe),
	foreign key(maNhanVien) references NhanVien(maNhanVien),
	foreign key (maLoThuoc) references LoThuoc(maLoThuoc)
)
create table ChiTietDoiTra
(
	soLuong int not null,
	maDoiTra varchar(20),
	maThuoc varchar(20),
	primary key (maDoiTra, maThuoc),
	foreign key(maDoiTra) references PhieuDoiTra(maDoiTra),
	foreign key(maThuoc) references Thuoc(maThuoc)
)
create table HoaDon
(
	maHoaDon varchar(20) primary key,
	ngayLap date not null,
	phuongThucThanhToan nvarchar(20) not null,
	yeuCauKeDon bit not null,
	maKhachHang varchar(20) not null,
	maNhanVien varchar(20) not null,
	maKhuyenMai varchar(20) not null,
	foreign key (maKhuyenMai) references KhuyenMai(maKhuyenMai),
	foreign key(maKhachHang) references KhachHang(maKhachHang),
	foreign key(maNhanVien) references NhanVien(maNhanVien)
)
create table ChiTietHoaDon
(
	soLuong int not null,
	maHoaDon varchar(20) not null,
	maThuoc varchar(20) not null,
	primary key (maHoaDon, maThuoc),
	foreign key(maHoaDon) references HoaDon(maHoaDon),
	foreign key(maThuoc) references Thuoc(maThuoc)
)
create table PhieuDat
(
	maPhieuDat varchar(20) primary key,
	thoiGianDat date not null,
	trangThai nvarchar(20) check (trangThai in ('Đã đặt', 'Đã nhận', 'Đã hủy')),
	ghiChu nvarchar(20),
	maKhachHang varchar(20) not null,
	maNhanVien varchar(20) not null,
	foreign key (maNhanVien) references NhanVien(maNhanVien),
	foreign key (maKhachHang) references KhachHang(maKhachHang),
)
create table ChiTietPhieuDat
(
	soLuong int not null,
	donGiaDat float not null,
	maPhieuDat varchar(20) not null,
	maThuoc varchar(20) not null,
	primary key (maPhieuDat, maThuoc),
	foreign key (maPhieuDat) references PhieuDat(maPhieuDat),
	foreign key (maThuoc) references Thuoc(maThuoc)
)

INSERT INTO KhachHang values 
('KH2', '0917774020', 'Lê Hoàng Khang', 'Vip', 0, '2025-02-26', 'NV001'),
('KH3', '0918011626', 'Nguyễn Văn Sỹ', 'Thường', 1, '2025-09-10', 'NV001');

insert into ChucVu values
('CV001','Quản lý'),
('CV002','Nhân viên')

insert into NhaCungCap values
('NCC001','Long Châu','0913343123','tphcm'),
('NCC002','Minh Châu','0913343456','tphcm'),
('NCC003','Pharmacity','0911343123','tphcm')
insert into NhanVien values
('NV001','Nguyễn Hữu Dũng','0935765186',N'Gò vấp, tphcm','asdf','CV001','TK001'),
('NV002','Nguyễn Văn Sỹ','0372145686','Q12, tphcm','asdf','CV001','TK002'),
('NV003','Nguyễn Đình Hùng','0945812321','Gò vấp, tphcm','asdf','CV001','TK003')
INSERT INTO LoThuoc VALUES
('LT01', 'L001', '2025-01-01', '2027-01-01', 10000, 500, N'Lô A'),
('LT02', 'L002', '2025-05-01', '2027-05-01', 12000, 300, N'Lô B');
insert into PhieuNhap values
('PN001','2025-8-10', N'Đã nhập','NCC001','NV001'),
('PN002','2025-8-10', N'Đã nhập','NCC002','NV002')
insert into ChiTietPhieuNhap values
(50,'132000','PN001','LT01'),
(80,'142000','PN002','LT02')
insert into KeThuoc values
('K001','A1.1','Kệ dài','100'),
('K002','A2.2','Kệ dài','120')

insert into Thue values
('T001','Thuế GTGT','2025-01-01','2030-01-01',5.0)
insert into KhuyenMai values
('KM001','Khuyến mãi khai trương','2025-01-01','2025-12-12',10,1)

insert into NhomThuoc values
('N001', N'Thuốc hô hấp')
insert into Thuoc values
('TH001', 'Ethambutol', '500mg', N'Rắn', 25000, 'Vien', 'Pharmacity', 'Đang Kinh Doanh', 'asdad', 'T001', 'K001', 'NV001', 'LT01', 'N001'),
('TH002', 'Turbezid', '500mg', N'Rắn', 25000, 'Vien', 'Pharmacity', 'Đang Kinh Doanh', 'asdad', 'T001', 'K001', 'NV001', 'LT01', 'N001')
