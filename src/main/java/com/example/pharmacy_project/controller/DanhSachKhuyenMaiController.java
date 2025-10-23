package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.KhachHangDAO;
import com.example.pharmacy_project.dao.KhuyenMaiDAO;
import com.example.pharmacy_project.entities.KhuyenMai;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class DanhSachKhuyenMaiController {
    @FXML
    private TableColumn<KhuyenMai, String> colBatDau;

    @FXML
    private TableColumn<KhuyenMai, String> colDieuKien;

    @FXML
    private TableColumn<KhuyenMai, String> colKetThuc;

    @FXML
    private TableColumn<KhuyenMai, String> colMaKhuyenMai;

    @FXML
    private TableColumn<KhuyenMai, String > colPhanTram;

    @FXML
    private TableColumn<KhuyenMai, String> colTenChuongTrinh;

    @FXML
    private TableView<KhuyenMai> tblKhuyenMai;
    @FXML
    private Button btnXoa;
    @FXML
    private Button btnSua;
    @FXML
    private TextField txtTimKiem;
    KhuyenMaiDAO khuyenmaiDao=new KhuyenMaiDAO();
    public void initialize()
    {
        colMaKhuyenMai.setCellValueFactory(new PropertyValueFactory<>("maKM"));
        colTenChuongTrinh.setCellValueFactory(new PropertyValueFactory<>("tenCT"));
        colBatDau.setCellValueFactory(new PropertyValueFactory<>("tuNgay"));
        colKetThuc.setCellValueFactory(new PropertyValueFactory<>("denNgay"));
        colDieuKien.setCellValueFactory(new PropertyValueFactory<>("dieuKienApDung"));
        colPhanTram.setCellValueFactory(new PropertyValueFactory<>("phanTramGiamGia"));
        loadDuLieuBangKhuyenMai();
        btnXoa.setOnAction(e->xoaKhuyenMai());
        btnSua.setOnAction(e->capNhatThongTin());
        timKiem();
    }
    public void loadDuLieuBangKhuyenMai()
    {
        ObservableList<KhuyenMai> dsKhuyenMai=FXCollections.observableArrayList(khuyenmaiDao.getAllTblKhuyenMai());
        tblKhuyenMai.setItems(dsKhuyenMai);
    }
    public void xoaKhuyenMai()
    {
        KhuyenMai km=tblKhuyenMai.getSelectionModel().getSelectedItem();
        if(km==null)
        {
            showmessage(Alert.AlertType.WARNING,"Vui Lòng Chọn Hàng Cần Xóa");

        }
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Xóa");
        alert.setHeaderText("Bạn Có Chắc Chắn Muốn Xóa");
        if(alert.showAndWait().get()== ButtonType.OK)
        {
            if(khuyenmaiDao.xoaKhuyenMai(km.getMaKM()))
            {
                showmessage(Alert.AlertType.INFORMATION,"Xóa Thành Công");
                tblKhuyenMai.getItems().remove(km);
            }
        }

    }
    public void showmessage(Alert.AlertType type ,String messgae)
    {
        Alert alert=new Alert(type);
        alert.setHeaderText("Thông Báo");
        alert.setContentText(messgae);
        alert.showAndWait();
    }
    public void capNhatThongTin()
    {
        KhuyenMai km=tblKhuyenMai.getSelectionModel().getSelectedItem();
        if(km==null)
        {
            showmessage(Alert.AlertType.WARNING,"Vui lòng chọn hàng cần sửa!");
        }
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setTitle("Chỉnh Sửa");
        dialog.setHeaderText("Chình Sửa Thông Tin Khuyến Mãi");
        TextField txtMaKhuyenMai=new TextField(km.getMaKM());
        TextField txtTenKhuyenMai=new TextField(km.getTenCT());
        DatePicker txtNgayBatDau=new DatePicker(km.getTuNgay());
        DatePicker txtNgayKetThuc=new DatePicker(km.getDenNgay());
        ComboBox<Integer> comboxDieuKien=new ComboBox();
        comboxDieuKien.getItems().addAll(100,200,300);
        comboxDieuKien.setValue(100);
        String stringPhanTram=new String(String.valueOf(km.getPhanTramGiamGia()));
        TextField txtPhanTramGiamGia=new TextField(stringPhanTram);
        GridPane pane=new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.addRow(1,new Label("Mã Khuyến Mại"),txtMaKhuyenMai);
        pane.addRow(2,new Label("Tên Khuyến Mại"),txtTenKhuyenMai);
        pane.addRow(3,new Label("Ngày Bắt Đầu"),txtNgayBatDau);
        pane.addRow(4,new Label("Ngày Kết Thúc"),txtNgayKetThuc);
        pane.addRow(5,new Label("Điều Kiện Áp Dụng"),comboxDieuKien);
        pane.addRow(6,new Label("Phần Trăm Giảm Giá"),txtPhanTramGiamGia);
        dialog.getDialogPane().setContent(pane);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        dialog.setResultConverter(button->
        {
            if(button==ButtonType.OK)
            {
                km.setTenCT(txtTenKhuyenMai.getText());
                km.setTuNgay(txtNgayBatDau.getValue());
                km.setDenNgay(txtNgayKetThuc.getValue());
                km.setDieuKienApDung(comboxDieuKien.getValue());
                km.setPhanTramGiamGia(Double.parseDouble(txtPhanTramGiamGia.getText()));
                if(khuyenmaiDao.capNhatKhuyenMai(km))
                {
                    showmessage(Alert.AlertType.INFORMATION,"Cập Nhật Thành Công");
                    tblKhuyenMai.refresh();
                }
            }
            return null;
        });
        dialog.showAndWait();
    }
    public void timKiem()
    {
        ObservableList<KhuyenMai> dskm=FXCollections.observableArrayList(khuyenmaiDao.getAllTblKhuyenMai());
        FilteredList<KhuyenMai> list=new FilteredList(dskm,p->true);
        tblKhuyenMai.setItems(list);
        txtTimKiem.textProperty().addListener((kieu,giatridau,giatricuoi)->
        {

            list.setPredicate(km->
            {
                if(giatricuoi==null||giatricuoi.isBlank())
                {
                    return true;
                }
                String tuKhoa=giatricuoi.toLowerCase();
                return km.getMaKM().toLowerCase().contains(tuKhoa);
            });
        });
    }
}
