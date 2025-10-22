package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.KeThuocDAO;
import com.example.pharmacy_project.dao.NhanVienDAO;
import com.example.pharmacy_project.dao.NhomThuocDAO;
import com.example.pharmacy_project.dao.ThuocDAO;
import com.example.pharmacy_project.entities.KeThuoc;
import com.example.pharmacy_project.entities.NhanVien;
import com.example.pharmacy_project.entities.NhomThuoc;
import com.example.pharmacy_project.entities.Thuoc;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

import java.io.File;
import java.util.ArrayList;

public class AddThuocController {
    @FXML
    private Button btnChonAnh;

    @FXML
    private Button btnLuu;

    @FXML
    private ComboBox<KeThuoc> cbxKe;

    @FXML
    private ComboBox<NhanVien> cbxNhanVien;

    @FXML
    private ComboBox<NhomThuoc> cbxNhom;

    @FXML
    private TextField txtGiaTien;

    @FXML
    private TextField txtHamLuong;

    @FXML
    private TextField txtMaThuoc;

    @FXML
    private TextField txtTenThuoc;
    @FXML
    private ImageView imgPreview;
    @FXML
    private String anhDaiDienPath;
    @FXML
    private ComboBox<String> cbxDangThuoc;

    @FXML
    private ComboBox<String> cbxDonViTinh;
    @FXML
    private ComboBox<String> cbxNhaSanXuat;
    private final ThuocDAO thuoc_dao = new ThuocDAO();
    @FXML
    private void handle_chonAnh(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Ảnh", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fc.showOpenDialog(txtMaThuoc.getScene().getWindow());
        if (file != null) {
            anhDaiDienPath = file.getAbsolutePath();
            imgPreview.setImage(new Image(file.toURI().toString()));
        }
    }

    @FXML
    private void handle_luu(ActionEvent event) {
        double gia = Double.parseDouble(txtGiaTien.getText().trim());
        Thuoc t = new Thuoc();
        t.setMaThuoc(txtMaThuoc.getText().trim());
        t.setTenThuoc(txtTenThuoc.getText().trim());
        t.setHamLuong(txtHamLuong.getText().trim());
        t.setDangThuoc(cbxDangThuoc.getValue());
        t.setGiaThuoc(gia);
        t.setDonViTinh(cbxDonViTinh.getValue());
        t.setNhaSanXuat(cbxNhaSanXuat.getValue());
        t.setAnhDaiDien(anhDaiDienPath != null ? anhDaiDienPath : "");
        KeThuoc ke = cbxKe.getValue();
        NhanVien nv = cbxNhanVien.getValue();
        NhomThuoc nhom = cbxNhom.getValue();

        t.setKeThuoc(ke);
        t.setNhanVien(nv);
        t.setNhomThuoc(nhom);
        boolean ok = thuoc_dao.themThuoc(t);
        if (ok) {
            alert(AlertType.INFORMATION, "Thành công", "Đã lưu thuốc.");
            clearForm();
        } else {
            // Bạn có thể xem log từ DAO (SQLException) để biết lỗi
            alert(AlertType.ERROR, "Thất bại", "Không thể lưu thuốc. Vui lòng kiểm tra log/khóa ngoại/trùng mã.");
        }
    }
    private void alert(Alert.AlertType type, String title, String content) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(content);
        a.showAndWait();
    }
    private void clearForm() {
        txtMaThuoc.clear();
        txtTenThuoc.clear();
        txtHamLuong.clear();
        cbxDangThuoc.getSelectionModel().clearSelection();
        cbxDonViTinh.getSelectionModel().clearSelection();
        cbxNhaSanXuat.getSelectionModel().clearSelection();
        txtGiaTien.clear();
        cbxKe.getSelectionModel().clearSelection();
        cbxNhom.getSelectionModel().clearSelection();
        cbxNhanVien.getSelectionModel().clearSelection();
        imgPreview.setImage(null);
        anhDaiDienPath = null;
    }

    private final KeThuocDAO ke_dao = new KeThuocDAO();
    private final NhomThuocDAO nhom_dao = new NhomThuocDAO();
    private final NhanVienDAO nv_dao = new NhanVienDAO();
    public void initialize() {

        loadCbxKe();
        loadCbxNhom();
        loadCbxNhanVien();
        cbxDangThuoc.getItems().addAll("Viên nén", "Viên nang", "Gói bột", "Lọ tiêm", "Bình xịt", "Viên sủi", "Siro", "Viên nhai", "Kem bôi", "Dung dich", "Miếng dán");
        cbxDonViTinh.getItems().addAll("viên", "gói", "vỉ", "hộp", "chai", "tuýp", "miếng", "bình");
        cbxNhaSanXuat.getItems().addAll("Dược Minh An", "Dược Hòa Phát", "Dược Việt Đức", "Dược An Khang", "Dược Tâm An", "Dược Đại Nam", "Dược Trường An");
        cbxNhanVien.setConverter(new StringConverter<>() {
            @Override public String toString(NhanVien nv) { return nv == null ? "" : nv.getTenNhanVien(); }
            @Override public NhanVien fromString(String s) { return null; } // không dùng
        });
        cbxNhom.setConverter(new StringConverter<>() {
            @Override public String toString(NhomThuoc n) { return n == null ? "" : n.getTenNhom(); }
            @Override public NhomThuoc fromString(String s) { return null; }
        });
        cbxKe.setConverter(new StringConverter<>() {
            @Override public String toString(KeThuoc k) { return k == null ? "" : k.getViTri(); }
            @Override public KeThuoc fromString(String s) { return null; }
        });
    }

    private void loadCbxNhanVien() {
        ArrayList<NhanVien> dsNV = nv_dao.getAllTblNhanVien();
        cbxNhanVien.getItems().setAll(dsNV);
    }

    private void loadCbxNhom() {
        ArrayList<NhomThuoc> dsNhom = nhom_dao.getAllTblNhomThuoc();
        cbxNhom.getItems().setAll(dsNhom);
    }

    private void loadCbxKe() {
        ArrayList<KeThuoc> dsKe = ke_dao.getAllTblKeThuoc();
        cbxKe.getItems().setAll(dsKe);
    }
}
