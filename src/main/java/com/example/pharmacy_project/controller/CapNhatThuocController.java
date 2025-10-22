package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.KeThuocDAO;
import com.example.pharmacy_project.dao.NhanVienDAO;
import com.example.pharmacy_project.dao.NhomThuocDAO;
import com.example.pharmacy_project.dao.ThuocDAO;
import com.example.pharmacy_project.entities.KeThuoc;
import com.example.pharmacy_project.entities.NhanVien;
import com.example.pharmacy_project.entities.NhomThuoc;
import com.example.pharmacy_project.entities.Thuoc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

import java.io.File;
import java.util.ArrayList;

public class CapNhatThuocController {
    @FXML private Button btnChonAnh;
    @FXML private Button btn_capNhat;

    @FXML private ComboBox<String> cbxDangThuoc;
    @FXML private ComboBox<String> cbxDonViTinh;
    @FXML private ComboBox<KeThuoc> cbxKe;
    @FXML private ComboBox<String> cbxNhaSanXuat;
    @FXML private ComboBox<NhanVien> cbxNhanVien;
    @FXML private ComboBox<NhomThuoc> cbxNhom;

    @FXML private ImageView imgPreview;

    @FXML private TextField txtGiaTien;
    @FXML private TextField txtHamLuong;
    @FXML private TextField txtMaThuoc;
    @FXML private TextField txtTenThuoc;

    @FXML private String anhDaiDienPath;

    private final ThuocDAO thuoc_dao = new ThuocDAO();
    private final KeThuocDAO ke_dao = new KeThuocDAO();
    private final NhomThuocDAO nhom_dao = new NhomThuocDAO();
    private final NhanVienDAO nv_dao = new NhanVienDAO();
    private Thuoc editing;
    private boolean updated = false;

    public boolean isUpdated() { return updated; }
    public void initialize() {
        txtMaThuoc.setEditable(false);
        loadCbxKe();
        loadCbxNhom();
        loadCbxNhanVien();

        cbxDangThuoc.getItems().addAll("Viên nén", "Viên nang", "Gói bột", "Lọ tiêm", "Bình xịt", "Viên sủi", "Siro", "Viên nhai", "Kem bôi", "Dung dich", "Miếng dán");
        cbxDonViTinh.getItems().addAll("viên", "gói", "vỉ", "hộp", "chai", "tuýp", "miếng", "bình");
        cbxNhaSanXuat.getItems().addAll("Dược Minh An", "Dược Hòa Phát", "Dược Việt Đức", "Dược An Khang", "Dược Tâm An", "Dược Đại Nam", "Dược Trường An");

        cbxNhanVien.setConverter(new StringConverter<>() {
            @Override public String toString(NhanVien nv) { return nv == null ? "" : nv.getTenNhanVien(); }
            @Override public NhanVien fromString(String s) { return null; }
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
    public void setData(Thuoc thuoc) {
        this.editing = thuoc;
        txtMaThuoc.setText(thuoc.getMaThuoc());
        txtTenThuoc.setText(thuoc.getTenThuoc());
        txtHamLuong.setText(thuoc.getHamLuong());
        txtGiaTien.setText(String.valueOf(thuoc.getGiaThuoc()));
        cbxDangThuoc.setValue(thuoc.getDangThuoc());
        cbxDonViTinh.setValue(thuoc.getDonViTinh());
        cbxNhaSanXuat.setValue(thuoc.getNhaSanXuat());
        if (thuoc.getKeThuoc() != null) cbxKe.setValue(findInCombo(cbxKe, thuoc.getKeThuoc()));
        if (thuoc.getNhomThuoc() != null) cbxNhom.setValue(findInCombo(cbxNhom, thuoc.getNhomThuoc()));
        if (thuoc.getNhanVien() != null) cbxNhanVien.setValue(findInCombo(cbxNhanVien, thuoc.getNhanVien()));
        anhDaiDienPath = thuoc.getAnhDaiDien();
        if (anhDaiDienPath != null && !anhDaiDienPath.isBlank()) {
            File f = new File(anhDaiDienPath);
            if (f.exists()) imgPreview.setImage(new Image(f.toURI().toString()));
            else imgPreview.setImage(null);
        } else {
            imgPreview.setImage(null);
        }
    }
    private static <T> T findInCombo(ComboBox<T> cb, T target) {
        for (T it : cb.getItems()) {
            if (it == null && target == null) return null;
            if (it != null && it.equals(target)) return it;
        }
        return null;
    }
    public void handle_capNhat(ActionEvent actionEvent) {
        try {
            double gia = Double.parseDouble(txtGiaTien.getText().trim());
            editing.setTenThuoc(txtTenThuoc.getText().trim());
            editing.setHamLuong(txtHamLuong.getText().trim());
            editing.setDangThuoc(cbxDangThuoc.getValue());
            editing.setGiaThuoc(gia);
            editing.setDonViTinh(cbxDonViTinh.getValue());
            editing.setNhaSanXuat(cbxNhaSanXuat.getValue());
            editing.setAnhDaiDien(anhDaiDienPath != null ? anhDaiDienPath : "");

            editing.setKeThuoc(cbxKe.getValue());
            editing.setNhanVien(cbxNhanVien.getValue());
            editing.setNhomThuoc(cbxNhom.getValue());

            boolean ok = thuoc_dao.capNhatThuoc(editing);
            if (ok) {
                updated = true;
                alert(Alert.AlertType.INFORMATION, "Thành công", "Đã cập nhật thuốc.");
                closeModal();
            } else {
                alert(Alert.AlertType.ERROR, "Thất bại", "Không thể cập nhật thuốc. Vui lòng kiểm tra và thử lại sau");
            }
        } catch (NumberFormatException ex) {
            alert(Alert.AlertType.WARNING, "Dữ liệu không hợp lệ", "Giá tiền phải là số.");
        }
    }

    private void alert(Alert.AlertType type, String title, String content) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(content);
        a.showAndWait();
    }

    private void closeModal() {
        btn_capNhat.getScene().getWindow().hide();
    }

    public void handle_chonAnh(ActionEvent actionEvent) {
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
}
