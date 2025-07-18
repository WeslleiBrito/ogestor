module com.example.ogestor {
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
    requires java.net.http;
    requires com.google.gson;
    requires java.sql;
    requires jbcrypt;
    requires io.github.cdimascio.dotenv.java;
    requires org.xerial.sqlitejdbc;
    requires jakarta.validation;
    requires org.hibernate.validator;
    requires java.desktop;
    requires annotations;
    requires jasperreports;
    requires org.slf4j;
    opens com.example.ogestor.model to com.google.gson, javafx.base;
    opens com.example.ogestor to javafx.fxml;
    exports com.example.ogestor;
    exports com.example.ogestor.controller;
    opens com.example.ogestor.controller;
    exports com.example.ogestor.componentes;
    opens com.example.ogestor.componentes;
    opens com.example.ogestor.DTO;
    exports com.example.ogestor.reports.vendas;
    opens com.example.ogestor.reports.vendas;

}