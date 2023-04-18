import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int CELL_SIZE = 20;

    private Canvas canvas;
    private GraphicsContext gc;
    private Timeline timeline;
    private List<Cell> snake;
    private Cell food;
    private Direction direction;
    private boolean gameOver;
    private int score;

    private VBox hud;
    private Label title;
    private Label scoreLabel;
    private Button startButton;
    private Button restartButton;

    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        BorderPane root = new BorderPane(canvas);

        // HUD
        hud = new VBox(10);
        hud.setAlignment(Pos.CENTER);
        title = new Label("Snake Game");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        startButton = new Button("Start");
        startButton.setOnAction(event -> {
            startGame();
            hud.setVisible(false);
            canvas.setVisible(true);
        });
        restartButton = new Button("Restart");
        restartButton.setOnAction(event -> {
            restartGame();
            hud.setVisible(true);
            canvas.setVisible(false);
        });
        restartButton.setVisible(false);
        hud.getChildren().addAll(title, startButton, restartButton, scoreLabel);
        root.setCenter(hud);

        snake = new ArrayList<>();
        direction = Direction.RIGHT;

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP && direction != Direction.DOWN) {
                direction = Direction.UP;
            } else if (event.getCode() == KeyCode.DOWN && direction != Direction.UP) {
                direction = Direction.DOWN;
            } else if (event.getCode() == KeyCode.LEFT && direction != Direction.RIGHT) {
                direction = Direction.LEFT;
            } else if (event.getCode() == KeyCode.RIGHT && direction != Direction.LEFT) {
                direction = Direction.RIGHT;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.show();
    }

    private void startGame() {
        snake.clear();
        snake.add(new Cell(WIDTH / 2, HEIGHT / 2));
        direction = Direction.RIGHT;
        spawnFood();
        score = 0;
        gameOver = false;
    
        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, WIDTH, HEIGHT);
    
            moveSnake();
            drawSnake();
            drawFood();
    
            if (gameOver) {
                timeline.stop();
                gc.setFill(Color.BLACK);
                gc.fillText("Game Over", WIDTH / 2 - 50, HEIGHT / 2);
                restartButton.setVisible(true);
                scoreLabel.setVisible(false);
                canvas.setVisible(false);
                hud.setVisible(true);
            }
    
            scoreLabel.setText("Score: " + score);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    
        drawSnake();
        drawFood();
    }

    private void restartGame() {
        timeline.stop();
        startGame();
    }

    private void moveSnake() {
        Cell head = snake.get(0);
        Cell newHead = new Cell(head.getX() + direction.getX(), head.getY() + direction.getY());

        if (newHead.getX() < 0 || newHead.getX() >= WIDTH || newHead.getY() < 0 || newHead.getY() >= HEIGHT) {
            gameOver = true;
            return;
        }

        if (newHead.equals(food)) {
            snake.add(0, food);
            score++;
            spawnFood();
        } else {
            snake.remove(snake.size() - 1);
            for (Cell cell : snake) {
                if (cell.equals(newHead)) {
                    gameOver = true;
                    return;
                }
            }
        }

        snake.add(0, newHead);
    }

    private void drawSnake() {
        gc.setFill(Color.GREEN);
        for (Cell cell : snake) {
            gc.fillRect(cell.getX(), cell.getY(), CELL_SIZE, CELL_SIZE);
        }
    }

    private void spawnFood() {
        Random random = new Random();
        int x = random.nextInt(WIDTH / CELL_SIZE) * CELL_SIZE;
        int y = random.nextInt(HEIGHT / CELL_SIZE) * CELL_SIZE;
        food = new Cell(x, y);
        }

        private void drawFood() {
            gc.setFill(Color.RED);
            gc.fillRect(food.getX(), food.getY(), CELL_SIZE, CELL_SIZE);
        }
        
        private enum Direction {
            UP(0, -CELL_SIZE),
            DOWN(0, CELL_SIZE),
            LEFT(-CELL_SIZE, 0),
            RIGHT(CELL_SIZE, 0);
        
            private final int x;
            private final int y;
        
            Direction(int x, int y) {
                this.x = x;
                this.y = y;
            }
        
            public int getX() {
                return x;
            }
        
            public int getY() {
                return y;
            }
        }
        
        private static class Cell {
            private final int x;
            private final int y;
        
            public Cell(int x, int y) {
                this.x = x;
                this.y = y;
            }
        
            public int getX() {
                return x;
            }
        
            public int getY() {
                return y;
            }
        
            @Override
            public boolean equals(Object obj) {
                if (obj instanceof Cell) {
                    Cell other = (Cell) obj;
                    return this.x == other.x && this.y == other.y;
                }
                return false;
            }
        }
        
        public static void main(String[] args) {
            launch(args);
        }
}        