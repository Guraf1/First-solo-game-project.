package org.gameproject.view;

import javafx.scene.canvas.GraphicsContext;
import org.gameproject.entities.creatures.Creature;
import org.gameproject.entities.creatures.Player;
import org.gameproject.util.KeyHandler;

public class CreatureRenderer {

    private Game game;
    private Creature creature;
    private GraphicsContext gc;
    private final KeyHandler keyHandler = KeyHandler.get();

    public CreatureRenderer(Game game, GraphicsContext gc, Creature creature){
        this.game = game;
        this.creature = creature;
        this.gc = gc;
    }

    public void drawCreature(){
        if (creature == null) {return;} //Exit method if there is no creature.

        int xPos = creature.getxPos();
        int yPos = creature.getYPos();
        this.gc.setImageSmoothing(false);

        String direction = creature.getLastDirection();
        int spriteNumber = creature.getSpriteNum();
        boolean isMoving = creature.isMoving();

        String spriteKey;
        if (isMoving){
            spriteKey = direction + spriteNumber; //e.g. left1 (to get the correct sprite)
        } else {
            spriteKey = "idle" + capitalize(direction);
        }

        gc.drawImage(creature.getSprite(spriteKey),
                xPos,
                yPos,
                game.getTileSize(),
                game.getTileSize()
        );
    }

    private String capitalize(String string){
        if (string == null || string.isEmpty()) return string;
        return string.substring(0, 1).toUpperCase() + string.substring(1);

    }
}
