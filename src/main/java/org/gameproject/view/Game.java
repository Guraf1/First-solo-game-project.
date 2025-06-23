package org.gameproject.view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.gameproject.entities.Player;
import org.gameproject.util.KeyHandler;
import javafx.scene.canvas.Canvas;

public class Game extends Application {

    //Screen settings
    final int originalTileSize = 32; // 32x32 tiles
    final int scale = 3; // Scale factor for the tiles

    final int tileSize = originalTileSize * scale; // 96x96 tiles after scaling
    final int maxScreenCol = 16; // Maximum columns on the screen
    final int maxScreenRow = 12; // Maximum rows on the screen
    final int screenWidth = tileSize * maxScreenCol; // 1536 pixels wide
    final int screenHeight = tileSize * maxScreenRow; // 1152 pixels tall

    //Game loop
    private AnimationTimer gameLoop;
    private final int FPS = 144; // Frames per second

    //Canvas for displaying the game
    private Canvas canvas;

    //Key handler instance
    private final KeyHandler keyHandler = KeyHandler.get();

    //Player instance
    private Player player = new Player(this);

    public Game() {

    }

    @Override
    public void start(Stage window) {
        this.canvas = new Canvas(this.screenWidth, this.screenHeight);
        startGameLoop();
        Pane root = new Pane();
        root.getChildren().add(this.canvas);
        Scene gameScene = new Scene(root, this.screenWidth, this.screenHeight);

        //Setup key event handlers
        gameScene.setOnKeyPressed(event -> {
            keyHandler.handleKeyPress(event.getCode());
        });
        gameScene.setOnKeyReleased(event -> {
            keyHandler.handleKeyRelease(event.getCode());
        });


        window.setScene(gameScene);
        window.setResizable(true);
        window.setTitle("Game");
        window.setOnCloseRequest(event -> {
            System.out.println("Window is closing");
            Platform.exit();
        });

        window.centerOnScreen();
        window.show();

        root.requestFocus();

    }
    /**
     * Starts the game loop that updates and draws the game at a fixed frame rate.
     * This method initializes the AnimationTimer and starts it.
     */
    public void startGameLoop() {
        this.gameLoop = new AnimationTimer() {
            private long lastTime = System.nanoTime(); // Initialize lastTime to the current time
            private double delta = 0;
            private int drawCount = 0;
            private long timer = 0;

            @Override
            public void handle(long now) {


                double drawInterval = 1_000_000_000.0 / FPS; // Interval in nanoseconds
                delta += (now - lastTime) / drawInterval; // Calculate delta time
                timer += (now - lastTime);
                lastTime = now; // Update lastTime to the current time


                if (delta >= 1) { // If enough time has passed
                    update();
                    draw();
                    delta--; // Decrease delta by 1 to reset for the next frame
                    drawCount++;
                }
                if (timer >= 1_000_000_000) { // If 1 second has passed
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0; // Reset draw count for the next second
                    timer = 0; // Reset timer
                }
            }
        };
        this.gameLoop.start();
    }

    /**
     * Draws the game elements on the canvas.
     * This method is called in a loop to continuously update the game visuals.
     */
    public void draw() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        player.draw(gc);

    }

    public void update(){
        player.update();
    }

    public static void appMain(String[] args) {
        launch(args);
    }

    /**
     * Returns the tile size used in the game.
     *
     * @return the size of each tile in pixels
     */
    public int getTileSize() {
        return this.tileSize;
    }

    /**
     * Returns the width of the game screen.
     *
     * @return the width of the game screen in pixels
     */
    public int getScreenWidth() {
        return this.screenWidth;
    }

    /**
     * Returns the height of the game screen.
     *
     * @return the height of the game screen in pixels
     */
    public int getScreenHeight() {
        return this.screenHeight;
    }


}
