package org.gameproject.entities.creatures;

import org.gameproject.util.KeyHandler;
import org.gameproject.view.Game;

import javax.imageio.ImageIO;
import java.awt.*;
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
    private final int playerSpeed = 3; // Default player speed




    public Player(Game gameWindow){
        super("Stig", 100, 12, 5, 1);
        this.gameWindow = gameWindow;
        loadPlayerImage(); // Load player images
        xPos = 100;
        yPos = 100;
    }

    /**
     * Loads the images related to character movement.
     */
    public void loadPlayerImage() {
        boolean methodPassed = false;
        int numberOfAttempts = 0;
        int maxAttempts = 3;
        while (!methodPassed && numberOfAttempts < maxAttempts)
            try {
                String path = "/player/images/";
                up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerUp1.png")));
                up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerUp2.png")));
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerDown1.png")));
                down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerDown2.png")));
                left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerLeft1.png")));
                left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerLeft2.png")));
                right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerRight1.png")));
                right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "playerRight2.png")));
                idleDown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "idleDown.png")));
                idleLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "idleLeft.png")));
                idleRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "idleRight.png")));
                idleUp = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "idleUp.png")));
                methodPassed = true;

            } catch (Exception e) {
            numberOfAttempts++;
            if (numberOfAttempts == maxAttempts){
                System.err.println("Reading images failed after " + numberOfAttempts + " attempts");
                System.err.println("Likely cause: images are " + e.getMessage());
                handleImageLoadError(e);
            }
            }
    }

    private void handleImageLoadError(Exception e){
        System.err.println("Error details: " + e.getClass().getName());

        loadPlayerImageFallback();

        gameWindow.writeErrorToFile("Failed to load player assets", e);

    }
    private void loadPlayerImageFallback(){
        BufferedImage fallback = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D graphics = fallback.createGraphics();
        graphics.setColor(Color.MAGENTA);
        graphics.fillRect(0,0,gameWindow.getTileSize(), gameWindow.getTileSize());

        up1 = up2 = down1 = down2 = left1 = left2 = right1 = right2 =
                idleDown = idleLeft = idleRight = idleUp = fallback;
        graphics.dispose();

        System.err.println("Using fallback images due to load error in loadPlayerImage()");


    }
    public void attack(){

    }

    /**
     * Updates the game state based on user input.
     * This method is called in a loop to continuously update the game logic.
     */
    @Override
    public void update(){
        if (keyHandler.isUpPressed()) {
            isMoving = true;
            yPos -= playerSpeed; // Move player up
            lastDirection = "up";
        }
        if (keyHandler.isDownPressed()) {
            isMoving = true;
            yPos += playerSpeed; // Move player down
            lastDirection = "down";
        }
        if (keyHandler.isLeftPressed()) {
            isMoving = true;
            xPos -= playerSpeed; // Move player left
            lastDirection = "left";
        }
        if (keyHandler.isRightPressed()) {
            isMoving = true;
            xPos += playerSpeed; // Move player right
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

        if (!keyHandler.isUpPressed() && !keyHandler.isDownPressed()
                && !keyHandler.isLeftPressed() && !keyHandler.isRightPressed()) {
            isMoving = false;
        }

    }
}











