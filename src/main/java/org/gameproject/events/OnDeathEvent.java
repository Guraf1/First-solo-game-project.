package org.gameproject.events;

import org.gameproject.entities.creatures.Creature;

public class OnDeathEvent {
    private Creature creature;
    private int moneyReward;
    private int experienceReward;

    public OnDeathEvent(Creature creature, int moneyReward, int experienceReward) {
        this.creature = creature;
        this.moneyReward = moneyReward;
        this.experienceReward = experienceReward;
    }

    public Creature getCreature() {
        return creature;
    }

    public int getMoneyReward() {
        return moneyReward;
    }

    public int getExperienceReward() {
        return experienceReward;
    }
}
