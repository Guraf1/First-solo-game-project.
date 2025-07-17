package org.gameproject.observer;

public interface Observer {
    /**
     * Method to update the observer with the latest state of the creature.
     * This method should be called whenever the creature's state changes.
     */
    void update();

    /**
     * Method to notify the observer of a specific event or change in the creature's state.
     * @param event The event that occurred.
     */
    void notify(Object event);
}
