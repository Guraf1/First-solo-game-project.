package org.gameproject.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.gameproject.entities.creatures.Creature;
import org.gameproject.entities.map.Tile;
import org.gameproject.util.KeyHandler;
import org.gameproject.util.TileManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Renderer {

    private Game game;
    private Creature creature;
    private GraphicsContext gc;
    private TileManager tileManager;
    private Map<String, Image> cachedCreatureSprites;
    private List<Image> cachedTileImages;

    public Renderer(Game game, GraphicsContext gc, Creature creature, TileManager tileManager){
        this.game = game;
        this.creature = creature;
        this.gc = gc;
        this.tileManager = tileManager;
        this.cachedCreatureSprites = new HashMap<>();
        this.cachedTileImages = new ArrayList<>();
    }

    public void drawCreature() {
        if (creature == null) {
            return;
        } //Exit method if there is no creature.
        if (cachedCreatureSprites.isEmpty()) {
            for (String key : creature.getAllSpriteKeys()) {
                this.cachedCreatureSprites.put(key, SwingFXUtils.toFXImage(creature.getSprite(key), null));
            }
        }

        int xPos = creature.getxPos();
        int yPos = creature.getYPos();
        this.gc.setImageSmoothing(false);

        String direction = creature.getLastDirection();
        int spriteNumber = creature.getSpriteNum();

        String spriteKey;
        if (creature.isMoving()) {
            spriteKey = direction + spriteNumber; //e.g. left1 (to get the correct sprite)
        } else {
            switch (direction) {
                case "up":
                    spriteKey = "idleUp";
                    break;
                case "left":
                    spriteKey = "idleLeft";
                    break;
                case "right":
                    spriteKey = "idleRight";
                    break;
                case "down":
                    spriteKey = "idleDown";
                default:
                    spriteKey = "idle" + capitalize(direction);
            }
        }
        gc.drawImage(
                cachedCreatureSprites.get(spriteKey),
                xPos,
                yPos,
                game.getTileSize(),
                game.getTileSize());
    }
    private String capitalize(String string){
        if (string == null || string.isEmpty()) return string;
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public void drawTiles() {
            if (this.cachedTileImages.isEmpty()) {
                for (Tile tile : tileManager.getListOfTiles()){
                    cachedTileImages.add(SwingFXUtils.toFXImage(tile.getTileImage(), null));
                }
            }

        int column = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (column < game.getMaxScreenColumn() && row < game.getMaxScreenRow()) {
            gc.drawImage(cachedTileImages.getFirst(), x, y, game.getTileSize(), game.getTileSize());
            column++;
            x += game.getTileSize();

            if (column == game.getMaxScreenColumn()) {
                column = 0;
                x = 0;
                row++;
                y += game.getTileSize();
            }
        }
    }
    public void clear(){
        gc.clearRect(0,0, game.getScreenWidth(), game.getScreenHeight());
    }

    public void setGraphicsContext(GraphicsContext gc){
        this.gc = gc;
    }

    public void setCreature(Creature creature){
        this.creature = creature;
    }


}
