package org.gameproject.entities.objects;

import java.awt.image.BufferedImage;

public class GameObject {

    private String name;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean collision = false;
    private BufferedImage objectImage;
    protected boolean isInteractable = false;

    public GameObject(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setInteractable(boolean interactable) {
        this.isInteractable = interactable;
    }
}
