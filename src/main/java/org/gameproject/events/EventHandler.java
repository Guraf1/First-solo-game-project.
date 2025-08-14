package org.gameproject.events;

import org.gameproject.util.KeyHandler;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {

    List<GameEventListener> listeners = new ArrayList<>();
    private static EventHandler instance;


    private EventHandler(){}


    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public void removeListener(GameEventListener observer) {
        listeners.remove(observer);
    }

    public void notifyListener(Object event) {
        for (GameEventListener events : listeners) {
            event.notify();
        }
    }

    public void fireEvent(GameEvent event){
        for (GameEventListener listener : listeners) {
            listener.handleGameEvent(event);
        }
    }

    public static EventHandler get(){
        if (EventHandler.instance == null) {
            EventHandler.instance = new EventHandler();
        }
        return EventHandler.instance;

    }
}
