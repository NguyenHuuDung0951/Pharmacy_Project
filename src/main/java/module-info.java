module com.example.pharmacy_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;

    opens com.example.pharmacy_project to javafx.fxml;
    opens com.example.pharmacy_project.controller to javafx.fxml;

    exports com.example.pharmacy_project;
    exports com.example.pharmacy_project.controller;
}