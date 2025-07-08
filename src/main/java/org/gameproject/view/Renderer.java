package org.gameproject.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.gameproject.entities.creatures.Creature;
import org.gameproject.entities.map.Tile;
import org.gameproject.util.TileManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Renderer {

    private Game game;
    private Creature player; //Used to get coordinate logic based on specifically the player's coordinates.
    private Creature creature;
    private GraphicsContext gc;
    private final TileManager tileManager;
    private final Map<String, Image> cachedCreatureSprites;
    private final List<Image> cachedTileImages;
    private final int[][] mapTileNumber;
    private final int playerXPosition; //The player's x position on the screen.
    private final int playerYPosition; //The player's y position on the screen.

    public Renderer(Game game, GraphicsContext gc, Creature player, TileManager tileManager){
        this.game = game;
        this.player = player;
        this.gc = gc;
        this.tileManager = tileManager;
        this.cachedCreatureSprites = new HashMap<>();
        this.cachedTileImages = new ArrayList<>();
        this.mapTileNumber = new int[game.getMaxWorldCol()][game.getMaxWorldRow()];
        this.playerXPosition = (game.getScreenWidth()/2) - (game.getTileSize()/2); //Center to the middle
        this.playerYPosition = (game.getScreenHeight()/2) - (game.getTileSize()/2); //Center to the middle
        String mapToLoad = "/maps/worldMap01.txt";
        loadMap(mapToLoad);
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

        int xPos = creature.getWorldX();
        int yPos = creature.getWorldY();
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
                this.playerXPosition,
                this.playerYPosition,
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

        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn < game.getMaxWorldCol() && worldRow < game.getMaxWorldRow()) {
            int tileNumber = mapTileNumber[worldColumn][worldRow];

            int worldX = worldColumn * game.getTileSize();
            int worldY = worldRow * game.getTileSize();

            int screenX = worldX - player.getWorldX() + playerXPosition;
            int screenY = worldY - player.getWorldY() + playerYPosition;

            if (worldX + game.getTileSize()*2 > player.getWorldX() - playerXPosition &&
                worldX - game.getTileSize()*2 < player.getWorldX() + playerXPosition &&
                worldY + game.getTileSize()*2 > player.getWorldY() - playerYPosition &&
                worldY - game.getTileSize()*2 < player.getWorldY() + playerYPosition) {
                gc.drawImage(cachedTileImages.get(tileNumber),
                        screenX,
                        screenY,
                        game.getTileSize(),
                        game.getTileSize());
            }
                worldColumn++;

            if (worldColumn == game.getMaxWorldCol()) {
                worldColumn = 0;
                worldRow++;
            }
        }
    }

    public void loadMap(String mapToLoadPath){

        try (InputStream inputStream = getClass().getResourceAsStream("/maps/map01.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                 int column = 0;
                 int row = 0;

            while(column < game.getMaxScreenColumn() && row < game.getMaxScreenRow()) {
                     String line = reader.readLine();

                     while (column < game.getMaxScreenColumn()) {
                         String[] numbers = line.split(" ");
                         int number = Integer.parseInt(numbers[column]);

                         mapTileNumber[column][row] = number;
                         column++;
                     }
                     if (column == game.getMaxScreenColumn()) {
                         column = 0;
                         row++;
                     }
                 }
             } catch (Exception e) {
            e.printStackTrace();
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
