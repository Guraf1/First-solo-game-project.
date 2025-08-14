package org.gameproject.entities.creatures;

import org.gameproject.events.EventHandler;
import org.gameproject.events.GameEvent;
import org.gameproject.events.OnDeathEvent;
import org.gameproject.events.OnPlayerMoveEvent;
import org.gameproject.util.CollisionChecker;
import org.gameproject.util.KeyHandler;
import org.gameproject.view.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Represents the playable character in the game.
 * This class contains attributes and methods related to the player.
 */
public class Player extends Creature {
    private static Player instance;

    Game gameWindow;
    KeyHandler keyHandler = KeyHandler.get();
    private final int playerSpeed = 2; // Default player speed
    private int money = 0;
    CollisionChecker collisionChecker;






    private Player(Game gameWindow){
        super("Stig", 100, 12, 5, 1);
        this.gameWindow = gameWindow;
        loadPlayerImage(); // Load player images
        worldX = gameWindow.getWorldWidth()/2;
        worldY = gameWindow.getWorldHeight()/2;
        this.hitbox = new Rectangle(
                8, // Hitbox x position relative to the player
                36,  // Hitbox y position relative to the player
                gameWindow.getTileSize() - (gameWindow.getTileSize()/2),
                gameWindow.getTileSize() - (gameWindow.getTileSize()/2));
        this.collisionChecker = gameWindow.getCollisionChecker();
        this.creatureType = "Player";
        this.speed = playerSpeed;
    }

    public static void initialize(Game gameWindow){
        if (instance == null) {
            instance = new Player(gameWindow);
        }
    }

    public static Player get(){
        if (instance == null) {
            throw new IllegalStateException("Player has not been initialized. Call Player.initialize() first.");
        }
        return instance;

    }

    @Override
    public void notify(Object event) {
        if (event instanceof OnDeathEvent onDeathEvent) {
        setMoney(onDeathEvent.getMoneyReward());
        }
    }

    @Override
    public void onDeath() {
        System.out.println("You died...");
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
        Graphics2D graphics = fallback.createGraphics();
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
//        if (keyHandler.isUpPressed()) {
//            isMoving = true;
//            lastDirection = "up";
//        }
//        if (keyHandler.isDownPressed()) {
//            isMoving = true;
//            lastDirection = "down";
//        }
//        if (keyHandler.isLeftPressed()) {
//            isMoving = true;
//            lastDirection = "left";
//        }
//        if (keyHandler.isRightPressed()) {
//            isMoving = true;
//            lastDirection = "right";
//        }
        this.collisionState = false;
        collisionChecker.checkCollision(this);
        processMovement();


//        if(!collisionState && isMoving){
//            switch (lastDirection) {
//                case "up" -> worldY -= speed;
//                case "down" -> worldY += speed;
//                case "left" -> worldX -= speed;
//                case "right" -> worldX += speed;
//            }
//        }

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

    private void processMovement(){
        int deltaX = 0;
        int deltaY = 0;

        isMoving = false;

        if (keyHandler.isRightPressed()) {
            deltaX += speed;
            isMoving = true;
            lastDirection = "right";
        }
        if (keyHandler.isLeftPressed()) {
            deltaX -= speed;
            isMoving = true;
            lastDirection = "left";
        }
        if (keyHandler.isUpPressed()) {
            deltaY -= speed;
            isMoving = true;
            lastDirection = "up";
        }
        if (keyHandler.isDownPressed()) {
            deltaY += speed;
            isMoving = true;
            lastDirection = "down";
        }

        if (isMoving) {
            this.collisionState = false;
            boolean wouldCollide = collisionChecker.checkFutureCollision(this, deltaX, deltaY);
            if (!wouldCollide) {
                OnPlayerMoveEvent moveEvent = new OnPlayerMoveEvent(this, deltaX, deltaY);
                EventHandler.get().fireEvent(moveEvent);
            }
        }

    }

    public void updatePosition(int newX, int newY) {
        if (!collisionState && isMoving){
            this.worldX += newX;
            this.worldY += newY;
        }

    }

    public void setMoney(int deathEventMoney){
        this.money += deathEventMoney;
    }


}











