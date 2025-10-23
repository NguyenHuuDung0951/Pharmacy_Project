/*
 * @ (#) ThemKhachHang         04 Oct 2025
 *
 *Copyright (c) 2025 IUH . All right reserved
 */
package com.example.pharmacy_project.controller;
import com.example.pharmacy_project.dao.KhachHangDAO;
import com.example.pharmacy_project.entities.KhachHang;
import com.example.pharmacy_project.entities.NhanVien;
import javafx.scene.control.*;
import javafx.fxml.FXML;

import java.time.LocalDate;

/*
 * @description:  This class represents a bank with many bank accounts
 * @author: Nguyen Van Sy
 * @version: 1.0
 * @created: 04 Oct 2025 23:56
 */
public class ThemKhachHangController {
    @FXML
    private ComboBox<String> cbGioiTinh;

    @FXML
    private ComboBox<String> cbHangThanhVien;

    @FXML
    private TextField txtMaKhachHang;

    @FXML
    private TextField txtMaNhanVien;

    @FXML
    private DatePicker txtNgayTao;

    @FXML
    private TextField txtSoDienThoai;

    @FXML
    private TextField txtTenKhachHang;
    @FXML
    private Button btnLuu;
    private  final KhachHangDAO kh=new KhachHangDAO();
    public void initialize()
    {
        cbGioiTinh.getItems().addAll("Nam","Nữ");
        cbHangThanhVien.getItems().addAll("Bạc","Vàng");
        btnLuu.setOnAction(e->themKhachHang());
    }
    public void themKhachHang()
    {
        try
        {
            String maKhachHang=txtMaKhachHang.getText();
            String tenKhachHang=txtTenKhachHang.getText();
            String soDienThoai=txtSoDienThoai.getText();
            String hangThanhVien=cbHangThanhVien.getValue();
            String gioiTinhStr=cbGioiTinh.getValue();
            boolean gioiTinhBl=gioiTinhStr.equalsIgnoreCase("Nam".trim());

            LocalDate ngayTao=txtNgayTao.getValue();
            String maNhanVien=txtMaNhanVien.getText();
            if(maKhachHang.isEmpty()||tenKhachHang.isEmpty()||soDienThoai.isEmpty()||hangThanhVien.isEmpty()||
            gioiTinhStr.isEmpty()||ngayTao.isAfter(LocalDate.now())||maNhanVien.isEmpty())

            {
            showMessage(Alert.AlertType.WARNING,"Vui Lòng Nhập Đầy Đủ Thông Tin");
            clearForm();
            return;
            }
            NhanVien nv=new NhanVien(maNhanVien);
            KhachHang newKh=new KhachHang(maKhachHang,soDienThoai,tenKhachHang,hangThanhVien,gioiTinhBl,ngayTao,nv);
            boolean resurt=kh.themKhachHang(newKh);
            if(resurt)
            {
                showMessage(Alert.AlertType.INFORMATION,"Thêm Thành Công Khách Hàng");
                clearForm();

            }
            else
            {
                showMessage(Alert.AlertType.ERROR,"Thêm That Bai Khách Hàng");
                clearForm();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void showMessage(Alert.AlertType type,String message)
    {
        Alert alert =new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void clearForm()
    {
        txtMaKhachHang.setText("");
        txtMaNhanVien.setText("");
        txtTenKhachHang.setText("");
        txtSoDienThoai.setText("");
        txtNgayTao.setValue(null);
        cbHangThanhVien.getSelectionModel().clearSelection();
        cbGioiTinh.getSelectionModel().clearSelection();
    }




}
