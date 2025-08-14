package org.gameproject.entities.objects;

public class Boots extends GameObject {

    private int speedBoost;

    public Boots(String name, int x, int y, int width, int height, int speedBoost) {
        super(name, x, y, width, height);
        this.speedBoost = speedBoost;
        this.setInteractable(true);
    }

    public int getSpeedBoost() {
        return speedBoost;
    }
}
