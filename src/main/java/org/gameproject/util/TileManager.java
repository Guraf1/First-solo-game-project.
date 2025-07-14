package org.gameproject.util;

import javafx.scene.image.Image;
import org.gameproject.entities.map.Tile;
import org.gameproject.view.Game;


import javax.imageio.ImageIO;
import java.awt.*;
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
        boolean methodSuccessful = false;
        int numberOfAttempts = 0;

        while (!methodSuccessful && numberOfAttempts < 3) {
            try {
                String tileImagePath = "/tiles/images/";
                BufferedImage grassTileImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                        tileImagePath + "grassland.png")));
                listOfTiles.add(new Tile.TileBuilder(16, 16, grassTileImage).build());
                listOfSprites.add(grassTileImage);

                BufferedImage mossyRoadImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                        tileImagePath + "mossyRoad.png")));
                listOfTiles.add(new Tile.TileBuilder(16,16,mossyRoadImage).build());
                listOfSprites.add(mossyRoadImage);

                BufferedImage stoneRoadImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                        tileImagePath + "stoneRoad.png")));
                listOfTiles.add(new Tile.TileBuilder(16,16, stoneRoadImage).build());
                listOfSprites.add(stoneRoadImage);

                BufferedImage oceanImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                        tileImagePath + "ocean.png")));
                listOfTiles.add(new Tile.TileBuilder(16,16,oceanImage).build());
                listOfSprites.add(oceanImage);

                BufferedImage seaImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                        tileImagePath + "sea.png")));
                listOfTiles.add(new Tile.TileBuilder(16,16,seaImage).build());
                listOfSprites.add(seaImage);

                BufferedImage sandImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                        tileImagePath + "sand.png")));
                listOfTiles.add(new Tile.TileBuilder(16,16,sandImage).build());
                listOfSprites.add(sandImage);

                BufferedImage grassWithTreeImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                        tileImagePath + "grassWithTree.png")));
                listOfTiles.add(new Tile.TileBuilder(16,16,grassWithTreeImage).build());
                listOfSprites.add(grassWithTreeImage);

                BufferedImage grassWithRockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                        tileImagePath + "grassWithRock.png")));
                listOfTiles.add(new Tile.TileBuilder(16,16,grassWithRockImage).build());
                listOfSprites.add(grassWithRockImage);

                BufferedImage sandWithRockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                        tileImagePath + "sandWithRock.png")));
                listOfTiles.add(new Tile.TileBuilder(16,16,sandWithRockImage).build());
                listOfSprites.add(sandWithRockImage);


                methodSuccessful = true; //Method succeeds at the end of the loop if no exception is thrown.

            } catch (Exception e) {
                numberOfAttempts++;
                if (numberOfAttempts == 3) {
                    System.err.println("Reading tile images failed after " + numberOfAttempts + " attempts");
                    e.printStackTrace();
                    loadTileFallbackImages();
                }
            }
        }
    }

    /**
     * Handles the error when loading tile images fails.
     * This method is called when the tile images cannot be loaded after multiple attempts.
     */
    private void loadTileFallbackImages(){
        System.err.println("Loading fallback images for tiles.");
        listOfTiles.clear();
        BufferedImage fallbackImagePink = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D pinkTileGraphics = fallbackImagePink.createGraphics();
        pinkTileGraphics.setColor(Color.PINK);
        pinkTileGraphics.fillRect(0, 0, 16, 16);
        listOfTiles.add(new Tile.TileBuilder(16,16,fallbackImagePink).build());
        pinkTileGraphics.dispose();

        BufferedImage fallbackImageBlack = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D blackTileGraphics = fallbackImageBlack.createGraphics();
        blackTileGraphics.setColor(Color.BLACK);
        blackTileGraphics.fillRect(0, 0, 16, 16);
        listOfTiles.add(new Tile.TileBuilder(16,16,fallbackImageBlack).build());
        blackTileGraphics.dispose();
        System.err.println("Fallback image loaded successfully.");
    }

    public List<Tile> getListOfTiles(){
        return listOfTiles;
    }
}
