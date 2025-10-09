module com.example.pharmacy_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    requires com.dlsc.formsfx;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;
    requires jbcrypt;

    opens com.example.pharmacy_project to javafx.fxml;
    opens com.example.pharmacy_project.controller to javafx.fxml;
    opens com.example.pharmacy_project.entities to javafx.base;
    exports com.example.pharmacy_project;
    exports com.example.pharmacy_project.controller;
}