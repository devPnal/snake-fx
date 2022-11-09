package com.snake;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.layout.TilePane;

public class GameLoop {
    static Direction direction = Direction.RIGHT;
    static GameController gc = new GameController();

    public static void Update(TilePane rootCanvas) throws IOException {
        if (direction == Direction.UP)
            Snake.y--;
        else if (direction == Direction.DOWN)
            Snake.y++;
        else if (direction == Direction.LEFT)
            Snake.x--;
        else if (direction == Direction.RIGHT)
            Snake.x++;

        
        if (!checkEnd(rootCanvas))
        {
            Snake.pos.add(new Point(Snake.x, Snake.y));
            fillCell(rootCanvas, new Point(Snake.x, Snake.y), Snake.pos.poll());
        }
        else 
        Platform.exit();
    }

    private static void fillCell(TilePane rootCanvas, Point _new, Point _old) {
        rootCanvas.getChildren().get(_new.y * 20 + _new.x).setStyle("-fx-background-color: #555555;");
        rootCanvas.getChildren().get(_old.y * 20 + _old.x).setStyle("-fx-background-color: #333333; -fx-border-color: #444;");
    }

    private static boolean checkEnd(TilePane _rootCanvas) throws IOException {
        if (Snake.x < 0 || Snake.y < 0 || Snake.x > 19 || Snake.y > 19) {
            return true;
        }
        return false;
                        
    }
}
