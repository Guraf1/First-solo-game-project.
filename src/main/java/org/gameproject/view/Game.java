package org.gameproject.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.gameproject.util.KeyHandler;

public class Game extends Application {

    //Screen settings
    final int originalTileSize = 32; // 32x32 tiles
    final int scale = 3; // Scale factor for the tiles

    final int tileSize = originalTileSize * scale; // 96x96 tiles after scaling
    final int maxScreenCol = 16; // Maximum columns on the screen
    final int maxScreenRow = 12; // Maximum rows on the screen
    final int screenWidth = tileSize * maxScreenCol; // 1536 pixels wide
    final int screenHeight = tileSize * maxScreenRow; // 1152 pixels tall

    //Key handler instance
    private final KeyHandler keyHandler = KeyHandler.get();

    //Default position
    private int playerX = 100; // Default player X position
    private int playerY = 100; // Default player Y position
    private int playerSpeed = 4; // Default player speed

    public Game(){

    }

    @Override
    public void start(Stage window) {
        Pane root = new Pane();
        Scene gameScene = new Scene(root, this.screenWidth, this.screenHeight);
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
            // Handle window close request if needed
            System.out.println("Window is closing");
            Platform.exit();
        });

        window.centerOnScreen();
        window.show();

        root.requestFocus();

    }

    public void update(){

    }

    public void draw(){

    }

    public static void appMain(String[] args) {
        launch(args);
    }
}
