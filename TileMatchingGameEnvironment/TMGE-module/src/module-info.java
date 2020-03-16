module TMGE.module {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    exports com.INF122.TMGE;
    opens com.INF122.TMGE to javafx.fxml;
}