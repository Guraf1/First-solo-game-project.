package org.gameproject.entities;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public abstract class Creature {

    private final String name;
    private final double health;
    private final double attackPower;
    private final double defence;
    private final int level;
    private int x;
    private int y;
    private boolean isAlive;
    private int speed;
    protected Image up1, up2, down1, down2, left1, left2, right1, right2; //walk sprites
    protected Image idleUp, idleDown, idleLeft, idleRight;
    protected String direction;
    protected int spriteCounter = 0;
    protected int spriteNum = 1;


    public Creature(String name, double health, double attackPower, double defence, int level) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.defence = defence;
        this.level = level;
    }

    public void attack() {
        // Method to perform an attack
    }

    // Getters and setters for the attributes can be added here

}
