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
	matKhau varchar(100) not null,
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
