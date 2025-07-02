package org.gameproject.util;

import javafx.scene.image.Image;
import org.gameproject.entities.map.Tile;
import org.gameproject.view.Game;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TileManager {
    private Game game;
    private List<Tile> listOfTiles;
    private List<BufferedImage> listOfSprites;


    public TileManager(Game game){
        this.game = game;
        listOfTiles = new ArrayList<>();
        listOfSprites = new ArrayList<>();
        appendTileImages();
    }

    public void appendTileImages() {
        try {
            String tileImagePath = "/tiles/images/";
            BufferedImage grassTileImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                    tileImagePath + "grassland.png")));
            listOfTiles.add(new Tile(16, 16, grassTileImage));
            listOfSprites.add(grassTileImage);
        } catch (Exception e) {

        }
    }
    public List<Tile> getListOfTiles(){
        return listOfTiles;
    }
}
