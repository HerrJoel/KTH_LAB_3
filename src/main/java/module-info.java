module jbl {
    requires javafx.controls;
    requires javafx.fxml;


    exports jbl.delA;
    exports jbl.delA.shape;
    opens jbl.delA to javafx.fxml;


    exports jbl.delB.enums;
    exports jbl.delB.exceptions;
    exports jbl.delB.matchers;
    exports jbl.delB.model;
    exports jbl.delB.ui;


    opens jbl.delB.ui to javafx.fxml;
}