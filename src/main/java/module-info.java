module hi.Interview.vidmot {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens hi.Interview.vidmot to javafx.fxml;
    exports hi.Interview.vidmot;
}