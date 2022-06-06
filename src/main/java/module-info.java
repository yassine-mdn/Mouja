module com.projet.mouja {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.projet.mouja to javafx.fxml;
    exports com.projet.mouja;
    exports com.projet.mouja.uiContollers;
    opens com.projet.mouja.uiContollers to javafx.fxml;
}