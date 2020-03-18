module HomeScreen.module {
    requires DrMario.module;
    requires Tetris.module;
    requires TMGE.module;
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    exports com.INF122.HomeScreen;
    opens com.INF122.HomeScreen;

}