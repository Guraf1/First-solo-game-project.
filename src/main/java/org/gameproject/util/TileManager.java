package org.gameproject.util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.gameproject.entities.map.Tile;
import org.gameproject.view.Game;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TileManager {
    private Game game;
    private List<Tile> listOfTiles;


    public TileManager(Game game){
        this.game = game;
        listOfTiles = new ArrayList<>();
        appendTileImage();
    }

    public void appendTileImage(){
        String tileImagePath = "/tiles/images/";
        Image grassTileImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                tileImagePath + "grassland.png")));
        listOfTiles.add(new Tile(16, 16, grassTileImage));
        }

    public List<Tile> getListOfTiles(){
        return listOfTiles;
    }
}
