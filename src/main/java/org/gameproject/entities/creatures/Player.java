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

    /**
     * Loads the images related to character movement.
     */
    public void loadPlayerImage() {
        String path = "/player/images/";
            up1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerUp1.png")));
            up2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerUp2.png")));
            down1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerDown1.png")));
            down2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerDown2.png")));
            left1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerLeft1.png")));
            left2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerLeft2.png")));
            right1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerRight1.png")));
            right2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerRight2.png")));
            idleDown = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path + "idleDown.png")));
            idleLeft= new Image(Objects.requireNonNull(getClass().getResourceAsStream(path + "idleLeft.png")));
            idleRight = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path + "idleRight.png")));
            idleUp = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path + "idleUp.png")));

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
        spriteCounter++;

        if (spriteCounter > 25) {  // Change sprite every 10 frames
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0; // Reset counter
        }

    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.DARKBLUE);

        gc.fillRect(0,0, gameWindow.getScreenWidth(), gameWindow.getScreenHeight());
        gc.setImageSmoothing(false); //Looks like shit with smoothing on.

        if (keyHandler.isLeftPressed()){
            if (spriteNum == 1){
                gc.drawImage(left1, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            } else if (spriteNum == 2) {
                gc.drawImage(left2, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            }

        } else if (keyHandler.isDownPressed()){
            if (spriteNum == 1){
                gc.drawImage(down1, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            } else if (spriteNum == 2) {
                gc.drawImage(down2, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            }
        } else if (keyHandler.isRightPressed()){
            if (spriteNum == 1){
                gc.drawImage(right1, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            } else if (spriteNum == 2) {
                gc.drawImage(right2, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            }

        } else if (keyHandler.isUpPressed()){
            if (spriteNum == 1){
                gc.drawImage(up1, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            } else if (spriteNum == 2) {
                gc.drawImage(up2, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            }
        }
        else {
            switch (lastDirection){
            case "up" -> gc.drawImage(idleUp, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            case "down" -> gc.drawImage(idleDown, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            case "left" -> gc.drawImage(idleLeft, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            case "right" -> gc.drawImage(idleRight, playerXPos, playerYPos, gameWindow.getTileSize(), gameWindow.getTileSize());
            }
        }
    }










}
