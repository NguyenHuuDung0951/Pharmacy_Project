package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.connectDB.ConnectDB;
import com.example.pharmacy_project.dao.ChucVuDao;
import com.example.pharmacy_project.dao.NhanVienDAO;
import com.example.pharmacy_project.dao.taiKhoanDao;
import com.example.pharmacy_project.entities.NhanVien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.awt.*;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ThemNhanVienController {

    @FXML
    private ComboBox<String> cmbChucVu;
    @FXML
    private ComboBox<String> cmbTaiKhoan;
    @FXML
    private TextField txtTenNhanVien;
    @FXML
    private TextField txtSoDienThoai;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private ImageView imgNhanVien;
    @FXML
    private File selectedFile;
    private ChucVuDao chucVuDao = new ChucVuDao();
    private taiKhoanDao tkdao = new taiKhoanDao();
    private NhanVienDAO nvDao = new NhanVienDAO();

    @FXML
    public void initialize() {
        cmbChucVu.setItems(chucVuDao.getAllChucVu());
        cmbTaiKhoan.setItems(tkdao.getAllTenDangNhap());
    }
    @FXML
    private void handleChonAnh(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn ảnh nhân viên");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png","*.jpg","*jpeg")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null){
            selectedFile = file;
            Image image = new Image(file.toURI().toString());
            imgNhanVien.setImage(image);
        }
    }
    @FXML
    private void handleLuu(ActionEvent event){
        String diaChi = txtDiaChi.getText().trim();
        String ten = txtTenNhanVien.getText().trim();
        String sdt = txtSoDienThoai.getText().trim();
        String chucVu = cmbChucVu.getValue();
        String taiKhoan = cmbTaiKhoan.getValue();

        if (ten.isEmpty() || sdt.isEmpty() || diaChi.isEmpty() || chucVu == null || taiKhoan == null ) {
            showAlert(Alert.AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        if (!sdt.matches("0\\d{9}")){
            showAlert(Alert.AlertType.WARNING,"Số điện thoại phải bắt đầu bằng 0 và 10 số!");
            return;
        }
        String maNv = nvDao.generateMaNV();
        String anhDaiDien = selectedFile != null ? selectedFile.getName() : "";
        NhanVien nv = new NhanVien(maNv,ten,sdt,diaChi,anhDaiDien,chucVu,taiKhoan);
        boolean success = nvDao.insertNhanVien(nv);
        if (success){
            showAlert(Alert.AlertType.INFORMATION,"Thêm nhân viên thành công! Mã nhân viên: "+ maNv);
            clearForm();
        }
        else {
            showAlert(Alert.AlertType.ERROR,"Thêm nhân viên thất bại!");
        }
    }
    @FXML
    private void handleQuayLai(ActionEvent event){
        try{
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/com/example/pharmacy_project/gui/MainView2.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.scene.Scene scene = new javafx.scene.Scene(root);
            stage.setScene(scene);
            stage.setTitle("Quản lý nhân viên");
            stage.show();

        }
        catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Không thể quay lại trang tìm nhân viên!");

        }
    }
    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    private void clearForm(){
        txtTenNhanVien.clear();
        txtSoDienThoai.clear();
        txtDiaChi.clear();
        cmbTaiKhoan.setValue(null);
        cmbChucVu.setValue(null);
        selectedFile = null;
    }
}
