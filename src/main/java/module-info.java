module com.example.spareui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.compiler;


    opens com.example.spareui to javafx.fxml;
    exports com.example.spareui;
}