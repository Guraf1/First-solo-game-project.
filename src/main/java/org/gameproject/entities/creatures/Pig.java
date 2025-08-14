package org.gameproject.entities.creatures;

public class Pig extends Creature {

    public Pig(String name, double health, double attackPower, double defence, int level) {
        super(name, health, attackPower, defence, level);
        this.creatureType = "Pig";
    }

    public void update(){

    }

    @Override
    public void notify(Object event) {
        // Handle notification events specific to Pig
        System.out.println("Pig received event: " + event);
    }

    @Override
    public void onDeath(){
        this.isAlive = false;
        // Handle death logic for Pig
        System.out.println("Mr. Pig has died.");
        // You can trigger an event here if needed
    }



}
