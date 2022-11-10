package com.snake;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.layout.TilePane;

public class GameLoop {
    static Direction direction = Direction.RIGHT;
    static GameController gc = new GameController();

    public static void Update(TilePane rootCanvas) throws IOException {
        if (direction == Direction.UP)
            Snake.headPos.y--;
        else if (direction == Direction.DOWN)
            Snake.headPos.y++;
        else if (direction == Direction.LEFT)
            Snake.headPos.x--;
        else if (direction == Direction.RIGHT)
            Snake.headPos.x++;

        if (isEnd(rootCanvas))
            Platform.exit();
        
        Snake.pos.add(new Point(Snake.headPos.x, Snake.headPos.y));
        FillCell(rootCanvas, Snake.headPos, Snake.tailPos = Snake.pos.poll());
        
        if (isEat())
            Eat(rootCanvas);
    }

    private static void FillCell(TilePane rootCanvas, Point _new, Point _old) {
        rootCanvas.getChildren().get(_new.y * 20 + _new.x).setStyle("-fx-background-color: #CCCCCC;");
        if (_old != null)
            rootCanvas.getChildren().get(_old.y * 20 + _old.x).setStyle("-fx-background-color: #333333; -fx-border-color: #444;");
    }

    private static boolean isEnd(TilePane _rootCanvas) throws IOException {
        if (Snake.headPos.x < 0 || Snake.headPos.y < 0 || Snake.headPos.x > 19 || Snake.headPos.y > 19) {
            System.out.println("Crashed to wall");
            return true;
        }
        for (Point items: Snake.pos) {
            if (items == Snake.headPos) continue;
            if (items.x == Snake.headPos.x && items.y == Snake.headPos.y) {
                System.out.println("Crashed to body");
                return true;
            }
        }
        return false;        
    }

    private static boolean isEat() {
        if (Snake.headPos.x == Board.seed.x && Snake.headPos.y == Board.seed.y)
            return true;
        return false;
    }

    private static void Eat(TilePane _rootCanvas) {
        Snake.length++;
        Snake.pos.add(Snake.tailPos);
    }
}
