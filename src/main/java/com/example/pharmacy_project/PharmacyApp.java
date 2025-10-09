package com.example.pharmacy_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class PharmacyApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PharmacyApp.class.getResource("/com/example/pharmacy_project/gui/MainView2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1366, 700);
        stage.setTitle("Trang chủ!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}