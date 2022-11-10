module com.snake {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.snake to javafx.fxml;
    exports com.snake;
}
