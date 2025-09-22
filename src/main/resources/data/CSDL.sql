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
	matKhau varchar(50) not null,
	vaiTro varchar(40) not null,
	trangThai varchar(20) check ( trangThai in('Kích Ho?t','Khóa')),
	maNhanVien varchar(10) not null,
	foreign key(maNhanVien) references NhanVien(maNhanVien)
)
