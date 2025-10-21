package com.example.pharmacy_project.controller;

import com.example.pharmacy_project.dao.NhanVienDAO;
import com.example.pharmacy_project.entities.NhanVien;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class DanhSachNhanVienController {
    @FXML
    private BorderPane rootPane;
    @FXML
    private TableColumn<NhanVien, String> celMaNv;
    @FXML
    private TableColumn<NhanVien, String> cellTenNv;
    @FXML
    private TableColumn<NhanVien, String> cellSdt;
    @FXML
    private TableColumn<NhanVien, String> cellDiaChi;
    @FXML
    private TableColumn<NhanVien, String> cellAnhDaiDien;
    @FXML
    private TableView<NhanVien> tblNhanVien;
    @FXML
    private ComboBox<String> cmbChucDanh;
    @FXML
    private TextField txtSearch;

    private final NhanVienDAO nhanVienDAO = new NhanVienDAO();
    private ObservableList<NhanVien> list;

    public void initialize() {
        // Gán giá trị cho cột
        celMaNv.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
        cellTenNv.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));
        cellSdt.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        cellDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        cellAnhDaiDien.setCellValueFactory(new PropertyValueFactory<>("anhDaiDien"));

        // Tải dữ liệu ban đầu
        list = FXCollections.observableArrayList(nhanVienDAO.getAllTblNhanVien());
        tblNhanVien.setItems(list);

        // Thiết lập ComboBox
        cmbChucDanh.getItems().addAll("Quản Lý", "Nhân Viên");
        cmbChucDanh.getSelectionModel().selectFirst();

        // Tạo pause transition để tránh query liên tục
        PauseTransition pause = new PauseTransition(Duration.millis(300));

        // Tìm kiếm theo thời gian thực
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            pause.setOnFinished(event -> {
                String keyword = newValue.trim();
                if (keyword.isEmpty()) {
                    list.setAll(nhanVienDAO.getAllTblNhanVien());
                } else {
                    list.setAll(nhanVienDAO.searchNhanVien(keyword));
                }
            });
            pause.playFromStart();
        });
    }

    public void handle_addEmpl(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("/com/example/pharmacy_project/gui/themNhanVien.fxml"));
            rootPane.setCenter(loader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
