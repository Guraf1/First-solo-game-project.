package org.gameproject.events;

import java.util.EventListener;

public interface GameEventListener extends EventListener {

    /**
     * Method to handle game events.
     * This method will be called when a game event occurs.
     *
     * @param event the game event that occurred
     */
    void handleGameEvent(GameEvent event);

}
