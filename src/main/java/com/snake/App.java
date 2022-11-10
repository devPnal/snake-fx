package com.snake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * JavaFX App
 */
public class App extends Application {
    Timer gameTimer;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("board.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        GameController controller = fxmlLoader.getController();
        gameTimer = new Timer();
        
        fxmlLoader.setController(fxmlLoader);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        scene.setOnKeyPressed(e -> controller.OnKeyPress(e.getCode()));
        stage.setOnCloseRequest(e -> gameTimer.cancel());
        Snake.pos.add(new Point(6, 10));
        Snake.pos.add(new Point(7, 10));
        Snake.pos.add(new Point(8, 10));
        Snake.pos.add(new Point(9, 10));
        Snake.pos.add(new Point(10, 10));
        Board.seed = new Point(0, 0);
        GameLoop.putSeed(controller.rootCanvas);
        Snake.headPos = new Point(12, 10);
        TimerTask gameLoop = new TimerTask() {
            @Override
            public void run() {
                try {
                    GameLoop.Update(controller.rootCanvas);
                } catch (IOException e) { }
            }
        };
        gameTimer.schedule(gameLoop, 0, 100);
    }

    @Override
    public void stop(){
        System.out.println("Stage is closing. Length: " + Snake.length);
        gameTimer.cancel();
    }

    public static void main(String[] args) {
        launch();
    }

}