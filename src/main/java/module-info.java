module jbl {
    requires javafx.controls;
    requires javafx.fxml;

    opens jbl to javafx.fxml;
    exports jbl;
    exports jbl.delA.shape;
    exports jbl.delB.matchers;
    opens jbl.delB.matchers to javafx.fxml;
    exports jbl.delA;
    opens jbl.delA to javafx.fxml;
}
