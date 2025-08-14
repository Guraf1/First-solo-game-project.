package org.gameproject.events;

import org.gameproject.util.KeyHandler;

public class OnPlayerMoveEvent extends GameEvent {

    private int deltaX;
    private int deltaY;

    public OnPlayerMoveEvent(Object source, int deltaX, int deltaY) {
        super(source, GameEvent.EventType.PLAYER_MOVE);
        this.deltaX = deltaX;
        this.deltaY = deltaY;


    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}