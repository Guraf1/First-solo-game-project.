package org.gameproject.events;

import java.util.EventObject;

public class GameEvent extends EventObject {

    protected final EventType type;

    public enum EventType {
        CREATURE_DEATH,
        CREATURE_MOVE,
        PLAYER_MOVE,
        ITEM_PICKUP,
        ITEM_DROP,
        MAP_CHANGE,
        COLLISION,
        GAME_START,
        GAME_END
    }

    public GameEvent(Object source, EventType type){
        super(source);
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

}
