package org.gameproject.entities.creatures;

import org.gameproject.events.OnDeathEvent;


public class Slime extends Creature implements Predator, Updateable{

    private int moneyReward = 10;
    private int experienceReward = 5;

    public Slime(String name, double health, double attackPower, double defence, int level) {
        super(name, health, attackPower, defence, level);
        this.creatureType = "Slime";
    }

    @Override
    public void update() {
        // Update logic for Slime
    }

    @Override
    public void attack(){
        // Slime attack logic
        System.out.println(" attacks with slime!");
    }

    @Override
    public void notify(Object event) {

    }

    @Override
    public void onDeath(){
        this.isAlive = false;
        OnDeathEvent event = new OnDeathEvent(this, moneyReward, experienceReward);
    }
}
