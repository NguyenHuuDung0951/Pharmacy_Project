package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.KhachHangDAO;
import com.example.pharmacy_project.entities.KhachHang;
import com.example.pharmacy_project.entities.NhanVien;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.time.LocalDate;

public class DanhSachKhachHangController {
    @FXML
    private TableView<KhachHang> tblKhachHang;
    private KhachHangDAO khdao=new KhachHangDAO();
    @FXML
    private TableColumn<KhachHang, String> colGioiTInh;

    @FXML
    private TableColumn<KhachHang, String> colHangThanhVien;

    @FXML
    private TableColumn<KhachHang, String> colMaKH;

    @FXML
    private TableColumn<KhachHang, String> colNguoiTao;

    @FXML
    private TableColumn<KhachHang, String> colSDT;

    @FXML
    private TableColumn<KhachHang, String> colTenKH;

    @FXML
    private TableColumn<KhachHang, String> colThoiGianTao;

    @FXML
    private ComboBox<Integer> sodong;
    @FXML
    private Button btnXoa;
    @FXML
    private Button btnSua;
    private final KhachHangDAO khachHangDAO = new KhachHangDAO();

    @FXML
    private TextField txtTimKiem;

    public void initialize() {
        colMaKH.setCellValueFactory(new PropertyValueFactory<>("maKhachHang"));
        colTenKH.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
        colSDT.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        colHangThanhVien.setCellValueFactory(new PropertyValueFactory<>("hangThanhVien"));
        colGioiTInh.setCellValueFactory(new PropertyValueFactory<>("gioiTinhString"));
        colThoiGianTao.setCellValueFactory(new PropertyValueFactory<>("thoiGianTao"));
        colNguoiTao.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));

        loadDuLieuKhachHang();
        btnXoa.setOnAction(e->xoaKhachHang());
        btnSua.setOnAction(i->capNhatKhachHang());
        int dongtrongbang=tblKhachHang.getItems().size();
        sodong.getItems().add(dongtrongbang);
    timkiem();

    }

    private void capNhatKhachHang() {
        KhachHang kh=tblKhachHang.getSelectionModel().getSelectedItem();

        if(kh == null)
        {
            showMessage(Alert.AlertType.WARNING,"Vui Lòng Chọn Hàng Cần Sửa");
            return;
        }
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setTitle("Sửa thông tin khách hàng");
        dialog.setHeaderText("Chỉnh sửa thông tin khách hàng");
        TextField TenKhachHangNew=new TextField(kh.getTenKhachHang());
        TextField MaKhachHangNew=new TextField(kh.getMaKhachHang());
        TextField soDienThoaiNew=new TextField(kh.getSoDienThoai());
        ComboBox<String> comBoxGTNew=new ComboBox();
        comBoxGTNew.getItems().addAll("Nam","Nữ");
        comBoxGTNew.setValue(kh.isGioiTinh()?"Nam":"Nữ");
        ComboBox<String> comBoxHangThanhVienNew=new ComboBox();
        comBoxHangThanhVienNew.getItems().addAll("Vàng","Bạc");
        comBoxHangThanhVienNew.setValue("Vàng");
        DatePicker dateNew=new DatePicker(kh.getThoiGianTao());
        TextField MaNhanVienNew=new TextField(kh.getMaNhanVien());
        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
       gridPane.addRow(0,new Label("Mã Khách Hàng"),MaKhachHangNew);
       gridPane.addRow(1,new Label("Tên Khách Hàng"),TenKhachHangNew);
       gridPane.addRow(2,new Label("Số Điện Thoại"),soDienThoaiNew);
       gridPane.addRow(3,new Label("Hạng Thành Viên"),comBoxHangThanhVienNew);
       gridPane.addRow(4,new Label("Giới Tính"),comBoxGTNew);
       gridPane.addRow(5,new Label("Thời Gian Tạo"),dateNew);
       gridPane.addRow(6,new Label("Mã Nhân Viên"),MaNhanVienNew);
        MaKhachHangNew.setDisable(true);
        MaNhanVienNew.setDisable(true);
       dialog.getDialogPane().setContent(gridPane);
       dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL,ButtonType.OK);
      dialog.setResultConverter(button->{
          if(button==ButtonType.OK)
          {

              kh.setTenKhachHang(TenKhachHangNew.getText());
              kh.setSoDienThoai(soDienThoaiNew.getText());
              kh.setHangThanhVien(comBoxHangThanhVienNew.getValue());
              kh.setGioiTinh(comBoxGTNew.getValue().equalsIgnoreCase("Nam"));
              kh.setThoiGianTao(dateNew.getValue());
              if(khachHangDAO.capNhatKhachHang(kh))
              {
                  showMessage(Alert.AlertType.INFORMATION,"Cập Nhật Thành Công");
                tblKhachHang.refresh();
              }
          }
          else{

          }
          return null;
      });

dialog.showAndWait();
    }

    private void loadDuLieuKhachHang() {
        ObservableList<KhachHang> ds = FXCollections.observableArrayList(khachHangDAO.getAllTbKhachHang());
        tblKhachHang.setItems(ds);
    }
    public void timkiem()
    {
       ObservableList<KhachHang> dsKhachHang=FXCollections.observableArrayList(khachHangDAO.getAllTbKhachHang());
       FilteredList<KhachHang> list=new FilteredList<>(dsKhachHang,p->true);
       tblKhachHang.setItems(list);
       txtTimKiem.textProperty().addListener((kieu,giatridau,giatricuoi)
               //textProperty lắng nghe ô nhập (biết được nhập tới đâu)
               ->{
           list.setPredicate(kh->
                   //setPredicate lọc ô tìm kiếm khi nhập vào thảo điều kiện đúng thì hiện thị
           {
               if(giatricuoi==null||giatricuoi.isBlank()) {
                   return true;
               }
               //hiện thị tất cả thông tin lên bảng nếu như chuỗi nhập rỗng
               String newValue=giatricuoi.toLowerCase();
               return kh.getMaKhachHang().toLowerCase().contains(newValue);
           });

       });
    }

    public void xoaKhachHang  ()
    {
        KhachHang kh=tblKhachHang.getSelectionModel().getSelectedItem();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Bạn có chắc chắn muốn xóa"+kh.getMaKhachHang()+"Không ?");
        if(alert.showAndWait().get()==ButtonType.OK)
        {
           boolean kt=khdao.xoaKhachHang(kh.getMaKhachHang());
           if(kt)
           {
               tblKhachHang.getItems().remove(kh);
               showMessage(Alert.AlertType.INFORMATION,"Xóa Thành Công");
           }
           else{
               showMessage(Alert.AlertType.INFORMATION,"Xóa Thất Bại");
           }
        }
    }
    public void showMessage(Alert.AlertType type, String message)
    {
        Alert aleart=new Alert(type);
        aleart.setHeaderText(null);
        aleart.setContentText(message);
        aleart.showAndWait();
    }




}
