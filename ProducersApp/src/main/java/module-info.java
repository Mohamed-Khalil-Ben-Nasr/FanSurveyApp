module com.mycompany.producersapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.mycompany.producersapp to javafx.fxml;
    exports com.mycompany.producersapp;
}
