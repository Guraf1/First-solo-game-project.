package org.gameproject.view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.gameproject.entities.creatures.Player;
import org.gameproject.util.CollisionChecker;
import org.gameproject.util.KeyHandler;
import javafx.scene.canvas.Canvas;
import org.gameproject.util.TileManager;

public class Game extends Application {

    //Screen settings
    private final int originalTileSize = 32; // 32x32 tiles
    private final int scale = 2; // Scale factor for the tiles

    private final int tileSize = originalTileSize * scale; // 96x96 tiles after scaling
    private final int maxScreenColumn = 20; // Maximum columns on the screen
    private final int maxScreenRow = 12; // Maximum rows on the screen
    private final int screenWidth = tileSize * maxScreenColumn; // 1920 pixels wide
    private final int screenHeight = tileSize * maxScreenRow; // 1152 pixels tall

    //World settings
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;
    private final int worldWidth = tileSize * maxWorldCol; //96*50 = 4800 pixels
    private final int worldHeight = tileSize * maxWorldRow; //96*50 = 4800 pixels


    //Controller
    private GameController gameController;

    //Game loop
    private AnimationTimer gameLoop;
    private final int FPS = 144; // Frames per second

    //Canvas for displaying the game
    private Canvas canvas;

    //Key handler instance
    private KeyHandler keyHandler;

    //Player instance
    private Player player;

    private CollisionChecker collisionChecker;
    private TileManager tileManager;

    public Game() {

    }

    @Override
    public void start(Stage window) {
        this.keyHandler = KeyHandler.get();
        this.canvas = new Canvas(this.screenWidth, this.screenHeight);
        this.tileManager = new TileManager(this);
        this.collisionChecker = new CollisionChecker(this, this.tileManager);
        Player.initialize(this);
        this.player = Player.get();
        this.gameController = new GameController(this, this.tileManager);

        startGameLoop();
        Pane root = new Pane();
        root.getChildren().add(this.canvas);
        Scene gameScene = new Scene(root, this.screenWidth, this.screenHeight);

        gameScene.setOnKeyPressed(event -> {
            keyHandler.handleKeyPress(event.getCode());
        });
        gameScene.setOnKeyReleased(event -> {
            keyHandler.handleKeyRelease(event.getCode());
        });

        //Plan was to make window resizeable without it screwing up the gameplay.
//        root.widthProperty().addListener((obs, oldVal, newVal) -> {
//            canvas.setWidth(newVal.doubleValue());
//            int newMaxScreenColumn = (int) (newVal.doubleValue() / tileSize);
//            updateVisibleArea(newMaxScreenColumn, maxScreenRow);
//        });


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
            private long lastTime = System.nanoTime();
            private double updateAccumulator = 0;
            private double frameAccumulator = 0;
            private long fpsTimer = 0;
            private int frameCount = 0;

            @Override
            public void handle(long now) {
                long elapsedNanos = now - lastTime;
                lastTime = now;
                fpsTimer += elapsedNanos;
                double updateInterval = 1_000_000_000.0 / FPS;
                double frameInterval = 1_000_000_000.0 / FPS; // Set frame rate equal to update rate
                updateAccumulator += elapsedNanos;
                int updateCount = 0;
                while (updateAccumulator >= updateInterval && updateCount < 5) {
                    update();
                    updateAccumulator -= updateInterval;
                    updateCount++;
                }
                frameAccumulator += elapsedNanos;
                if (frameAccumulator >= frameInterval) {
                    draw();
                    frameAccumulator -= frameInterval;
                    frameCount++;
                }

                // Display FPS counter
                if (fpsTimer >= 1_000_000_000) {
                    final int finalCount = frameCount;
                    Platform.runLater(() -> System.out.println("FPS: " + finalCount));
                    frameCount = 0;
                    fpsTimer = 0;
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
        gameController.renderFrame();
    }

    public void update() {
        player.update();
    }

    public void writeErrorToFile(String message, Exception e) {
        //TODO: Kanskje sjå på seinare...
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

    /**
     * Returns the maximum number of tile sizes for the height of the program's window.
     *
     * @return Max number of tile sizes for the height of the program's window.
     */
    public int getMaxScreenColumn() {
        return this.maxScreenColumn;
    }

    /**
     * Returns the maximum number of tile sizes for the width of the program's window.
     *
     * @return Max number of tile sizes for the width of the program's window.
     */
    public int getMaxScreenRow() {
        return this.maxScreenRow;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }


    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getWorldWidth() {
        return this.worldWidth;
    }

    public int getWorldHeight() {
        return this.worldHeight;
    }

    public CollisionChecker getCollisionChecker() {
        return this.collisionChecker;
    }
}
