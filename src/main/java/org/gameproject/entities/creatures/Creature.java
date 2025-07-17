package org.gameproject.entities.creatures;

import org.gameproject.events.OnDeathEvent;
import org.gameproject.observer.Observer;
import org.gameproject.observer.ObserverHandler;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Creature implements Updateable, Observer{

    ObserverHandler observerHandler = ObserverHandler.get();
    private final String name;
    private final double health;
    private final double attackPower;
    private final double defence;
    private final int level;
    protected boolean isAlive;
    private int speed;
    private final List<String> allSpriteKeys;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; //walk sprites;
    protected BufferedImage idleUp, idleDown, idleLeft, idleRight;
    protected String lastDirection = "down"; // Last direction a creature moved
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    protected int worldX;
    protected int worldY;
    protected boolean isMoving;


    public Creature(String name, double health, double attackPower, double defence, int level) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.defence = defence;
        this.level = level;
        this.allSpriteKeys = new ArrayList<>();
        this.allSpriteKeys.addAll(Arrays.asList(
                "up1", "up2", "down1", "down2",
                "left1", "left2", "right1", "right2",
                "idleUp", "idleDown", "idleLeft", "idleRight"
        ));
    }

    public void attack() {
        // Method to perform an attack
    }

    public abstract void update();
    public abstract void notify(Object event);



    /**
     * Returns the sprite number indicating the walk cycle frames.
     * @return Which frame of the animation cycle is active.
     */
    public int getSpriteNum() {
        return this.spriteNum;
    }

    /**
     * Returns the sprite image as requested.
     * <p>
     * <b>Available sprite options:</b>
     * <ul>
     *   <li><em>Walking sprites:</em> up1, up2, down1, down2, left1, left2, right1, right2</li>
     *   <li><em>Idle sprites:</em> idleUp, idleDown, idleLeft, idleRight</li>
     * </ul>
     * <p>
     * @param spriteName The image name of a sprite.
     * @return Sprite image with direction indicated by the param.
     */
    public BufferedImage getSprite(String spriteName) {
        return switch (spriteName) {
            case "up1" -> up1;
            case "up2" -> up2;
            case "down1" -> down1;
            case "down2" -> down2;
            case "left1" -> left1;
            case "left2" -> left2;
            case "right1" -> right1;
            case "right2" -> right2;
            case "idleUp" -> idleUp;
            case "idleDown" -> idleDown;
            case "idleLeft" -> idleLeft;
            case "idleRight" -> idleRight;
            default -> idleDown;
        };
    }

    /**
     * Returns the last direction the creature moved.
     * @return "up", "left", "right" or "down".
     */
    public String getLastDirection(){
        return this.lastDirection;
    }

    /**
     * Returns the x position of the creature.
     * @return x position of the creature.
     */
    public int getWorldX() {
        return worldX;
    }

    /**
     * Returns the y position of the creature.
     * @return y position of the creature.
     */
    public int getWorldY(){
        return worldY;
    }
    /**
     * Returns this class object.
     * @return this class object.
     */
    public Creature getCreatureClass(){
        return this;
    }

    public List<String> getAllSpriteKeys() {
        return this.allSpriteKeys;
    }

    public boolean isMoving(){
        return this.isMoving;
    }

    public abstract void onDeath();





}
