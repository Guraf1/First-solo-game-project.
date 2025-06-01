package org.gameproject.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage window) {
        window.setResizable(true);
        window.setTitle("Game");

        window.centerOnScreen();
        window.show();

    }

    public static void appMain(String[] args) {
        launch(args);
    }
}
