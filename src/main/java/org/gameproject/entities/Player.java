package org.gameproject.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.gameproject.util.KeyHandler;
import org.gameproject.view.Game;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents the playable character in the game.
 * This class contains attributes and methods related to the player.
 */
public class Player extends Creature {

    Game gameWindow;
    KeyHandler keyHandler = KeyHandler.get();
    //Default position
    private int playerXPos = 100; // Default player X position
    private  int playerYPos = 100; // Default player Y position
    private final int playerSpeed = 4; // Default player speed
    private String lastDirection = "down"; // Last direction the player moved


    public Player(Game gameWindow){
        super("Stig", 100, 12, 5, 1);
        this.gameWindow = gameWindow;
        loadPlayerImage(); // Load player images
    }

    public void loadPlayerImage() {
            up1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/playerUp1.png")));
            up2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/playerUp2.png")));
            down1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/playerDown1.png")));
            down2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/playerDown2.png")));
            left1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/playerLeft1.png")));
            left2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/playerLeft2.png")));
            right1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/playerRight1.png")));
            right2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/playerRight2.png")));
        }


    public void attack(){

    }
    /**
     * Updates the game state based on user input.
     * This method is called in a loop to continuously update the game logic.
     */
    public void update(){
        if (keyHandler.isUpPressed()) {
            playerYPos -= playerSpeed; // Move player up
            lastDirection = "up";
        }
        if (keyHandler.isDownPressed()) {
            playerYPos += playerSpeed; // Move player down
            lastDirection = "down";
        }
        if (keyHandler.isLeftPressed()) {
            playerXPos -= playerSpeed; // Move player left
            lastDirection = "left";
        }
        if (keyHandler.isRightPressed()) {
            playerXPos += playerSpeed; // Move player right
            lastDirection = "right";
        }
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITESMOKE);

        gc.fillRect(0,0, gameWindow.getScreenWidth(), gameWindow.getScreenHeight());
        gc.setImageSmoothing(false); //Looks like shit with smoothing on.

        if (keyHandler.isLeftPressed()){
            gc.drawImage(left1, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
        } else if (keyHandler.isDownPressed()){
            gc.drawImage(down1, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
        } else if (keyHandler.isRightPressed()){
            gc.drawImage(right1, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
        } else if (keyHandler.isUpPressed()){
            gc.drawImage(up1, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
        }
        else {
            switch (lastDirection){
            case "up" -> gc.drawImage(up1, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            case "down" -> gc.drawImage(down1, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            case "left" -> gc.drawImage(left1, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            case "right" -> gc.drawImage(right1, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            }
        }


//        gc.drawImage(up1, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
//
    }










}
