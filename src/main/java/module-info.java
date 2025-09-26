module jbl {
    requires javafx.controls;
    requires javafx.fxml;

    opens jbl to javafx.fxml;
    exports jbl;
    exports jbl.shape;
}
