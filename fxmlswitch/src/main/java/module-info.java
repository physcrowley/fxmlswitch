module edu.djc.fxmlswitch {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.djc.fxmlswitch to javafx.fxml;
    exports edu.djc.fxmlswitch;
}