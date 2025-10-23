/*
 * @ (#) DanhSachThuocController         04 Oct 2025
 *
 *Copyright (c) 2025 IUH . All right reserved
 */
package com.example.pharmacy_project.controller;
import com.example.pharmacy_project.dao.ThuocDAO;
import com.example.pharmacy_project.entities.Thuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/*
 * @description:  This class represents a bank with many bank accounts
 * @author: Nguyen Van Sy
 * @version: 1.0
 * @created: 04 Oct 2025 21:51
 */
public class DanhSachThuocController {
    @FXML
    private TableColumn<Thuoc, String> colDangThuoc;

    @FXML
    private TableColumn<Thuoc, String> colDonViTinh;

    @FXML
    private TableColumn<Thuoc, Double> colGiaThuoc;

    @FXML
    private TableColumn<Thuoc, String> colHamLuong;

    @FXML
    private TableColumn<Thuoc, String> colHinhAnh;

    @FXML
    private TableColumn<Thuoc, String> colMaThuoc;

    @FXML
    private TableColumn<Thuoc, String> colNhaSanXuat;

    @FXML
    private TableColumn<Thuoc, String> colTenThuoc;

    @FXML
    private TableView<Thuoc> tblThuoc;
    @FXML
    private Button btnCapNhat;
    @FXML
    private Button btnXoa;
    private final ThuocDAO thuocDAO = new ThuocDAO();
    public void initialize(){
        colMaThuoc.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
        colTenThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
        colHamLuong.setCellValueFactory(new PropertyValueFactory<>("hamLuong"));
        colDangThuoc.setCellValueFactory(new PropertyValueFactory<>("dangThuoc"));
        colGiaThuoc.setCellValueFactory(new PropertyValueFactory<>("giaThuoc"));
        colDonViTinh.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
        colNhaSanXuat.setCellValueFactory(new PropertyValueFactory<>("nhaSanXuat"));
        colHinhAnh.setCellValueFactory(new PropertyValueFactory<>("anhDaiDien"));

        loadDuLieuThuoc();
    }

    private void loadDuLieuThuoc() {
        ObservableList<Thuoc> ds = FXCollections.observableArrayList(thuocDAO.getAllTblThuoc());
        tblThuoc.setItems(ds);
    }

    public void handle_xoa(ActionEvent actionEvent) {
        Thuoc selected = tblThuoc.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Vui lòng chọn một thuốc để xóa.").showAndWait();
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Xác nhận");
        confirm.setHeaderText("Xóa thuốc?");
        confirm.setContentText("Bạn chắc chắc muốn xóa: ["
                + selected.getMaThuoc() + "] " + selected.getTenThuoc() + " ?");
        confirm.initOwner(btnXoa.getScene().getWindow());
        String maThuoc = selected.getMaThuoc();
        confirm.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.OK) {
                try {
                    boolean ok = thuocDAO.xoaThuoc(maThuoc);
                    if (ok) {
                        new Alert(Alert.AlertType.INFORMATION, "Đã xóa thuốc.").showAndWait();
                        refreshTable();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Xóa thất bại. Vui lòng thử lại.").showAndWait();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Lỗi cơ sở dữ liệu khi xóa.").showAndWait();
                }
            }
        });
    }

    public void handle_capNhat(ActionEvent actionEvent) throws IOException {
        Thuoc selected = tblThuoc.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Vui lòng chọn một thuốc trước khi cập nhật.").showAndWait();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/pharmacy_project/gui/capNhatThuoc.fxml")
            );
            Parent root = loader.load();

            CapNhatThuocController ctrl = loader.getController();
            ctrl.setData(selected);

            Stage modal = new Stage();
            modal.setTitle("Cập nhật thuốc");
            modal.setScene(new Scene(root));
            modal.initOwner(btnCapNhat.getScene().getWindow());
            modal.initModality(Modality.WINDOW_MODAL);
            modal.setResizable(false);
            modal.showAndWait();
            if (ctrl.isUpdated()) {
                refreshTable();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Không thể mở cửa sổ cập nhật.").showAndWait();
        }
    }
    private void refreshTable() {
        tblThuoc.setItems(FXCollections.observableArrayList(new ThuocDAO().getAllTblThuoc()));
        tblThuoc.refresh();
    }
}
