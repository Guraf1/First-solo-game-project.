package org.gameproject.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {

    //Screen settings
    final int originalTileSize = 32; // 32x32 tiles
    final int scale = 3; // Scale factor for the tiles

    final int tileSize = originalTileSize * scale; // 96x96 tiles after scaling
    final int maxScreenCol = 16; // Maximum columns on the screen
    final int maxScreenRow = 12; // Maximum rows on the screen
    final int screenWidth = tileSize * maxScreenCol; // 1536 pixels wide
    final int screenHeight = tileSize * maxScreenRow; // 1152 pixels tall

    public Game(){

    }

    @Override
    public void start(Stage window) {
        Pane root = new Pane();
        Scene gameScene = new Scene(root, this.screenWidth, this.screenHeight);
        window.setScene(gameScene);
        window.setResizable(true);
        window.setTitle("Game");
        window.setOnCloseRequest(event -> {
            // Handle window close request if needed
            System.out.println("Window is closing");
        });

        window.centerOnScreen();
        window.show();

    }

    public static void appMain(String[] args) {
        launch(args);
    }
}
