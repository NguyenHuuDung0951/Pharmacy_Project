/*
 * @ (#) ThemKhuyenMai         04 Oct 2025
 *
 *Copyright (c) 2025 IUH . All right reserved
 */
package com.example.pharmacy_project.controller;

/*
 * @description:  This class represents a bank with many bank accounts
 * @author: Nguyen Van Sy
 * @version: 1.0
 * @created: 04 Oct 2025 23:48
 */

import com.example.pharmacy_project.dao.KhuyenMaiDAO;
import com.example.pharmacy_project.entities.KhuyenMai;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class ThemKhuyenMaiController {
    @FXML
    private Button btnLuu;

    @FXML
    private ComboBox<Integer> cbDieuKien;

    @FXML
    private TextField txtMaKhuyenMai;

    @FXML
    private DatePicker txtNgayBatDau;

    @FXML
    private DatePicker txtNgayKetThuc;

    @FXML
    private TextField txtPhanTramGiamGia;

    @FXML
    private TextField txtTenKhuyenMai;
    KhuyenMaiDAO khuyenmaiDao=new KhuyenMaiDAO();
    public void initialize()
    {
    btnLuu.setOnAction(e->themKhachHang());
    cbDieuKien.getItems().addAll(100,200,300);
    }
    public void themKhachHang()
    {
        try{
            String maKhuyenMai=txtMaKhuyenMai.getText();
            String tenKhuyenMai=txtTenKhuyenMai.getText();
            LocalDate ngayBatDau=txtNgayBatDau.getValue();
            LocalDate ngayKetThuc=txtNgayKetThuc.getValue();
            Integer dieuKhienApDung= cbDieuKien.getValue();
            String phanTram=txtPhanTramGiamGia.getText();
            double phanTramDouble=Double.parseDouble(phanTram);
           if(maKhuyenMai.isEmpty()||tenKhuyenMai.isEmpty()||ngayBatDau.isAfter(LocalDate.now())||ngayKetThuc.isBefore(LocalDate.now())||
           phanTram.isEmpty())
           {
               showmessage(Alert.AlertType.WARNING,"Vui Lòng Nhập Đầy Đủ Thông Tin");
               clerForm();
               return;
           }
            KhuyenMai kmnew=new KhuyenMai(maKhuyenMai,tenKhuyenMai,ngayBatDau,ngayKetThuc,phanTramDouble,dieuKhienApDung);
           if(khuyenmaiDao.themKhuyenMai(kmnew))
           {
               showmessage(Alert.AlertType.INFORMATION,"Thêm Thành Công Khuyến Mại");
                clerForm();

           }
           else {
               showmessage(Alert.AlertType.WARNING,"Thêm Thất Bại ");
               clerForm();

           }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void showmessage(Alert.AlertType type,String message)
    {
        Alert alert=new Alert(type);
        alert.setHeaderText("Thông báo");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void clerForm()
    {
        txtMaKhuyenMai.setText("");
        txtTenKhuyenMai.setText("");
        txtPhanTramGiamGia.setText("");
        txtNgayBatDau.setValue(null);
        txtNgayKetThuc.setValue(null);
        cbDieuKien.setValue(null);
    }

}
