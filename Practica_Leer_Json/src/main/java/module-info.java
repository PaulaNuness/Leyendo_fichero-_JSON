module com.example.practica_leer_json {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;

    opens com.example.practica_leer_json to javafx.fxml;
    exports com.example.practica_leer_json;
}