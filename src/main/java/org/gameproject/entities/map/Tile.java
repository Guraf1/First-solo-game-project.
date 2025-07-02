package org.gameproject.entities.map;


import javafx.scene.image.Image;

public class Tile {
    protected int tileWidth;
    protected int tileHeight;
    protected boolean interactable = false;
    protected boolean collision = false;

    protected Image tileImage; //Base background image
    protected Image tileImageFlavour; //Additional spice on top of the base image

    /**
     * Constructor for setting all the fields.
     * @param tileWidth The width of one tile in pixels.
     * @param tileHeight The height of one tile in pixels.
     * @param interactable Defines whether a tile is interactable or not.
     * @param collision Defines if entities can move on top of this tile or not.
     * @param tileImage The image a tile should represent.
     */
    public Tile(int tileWidth, int tileHeight, boolean interactable, boolean collision, Image tileImage,
                Image tileImageFlavour){
    this.tileWidth = tileWidth;
    this.tileHeight = tileHeight;
    this.interactable = interactable;
    this.collision = collision;
    this.tileImage = tileImage;
    this.tileImageFlavour = tileImageFlavour;
    }

    /**
     * Constructor for setting fields without default values.
     * @param tileWidth The width of the tile in pixels.
     * @param tileHeight The height of the tile in pixels.
     * @param tileImage The image a tile should represent.
     * @param tileImageFlavour An image to put on top of tileImage.
     */
    public Tile(int tileWidth, int tileHeight, Image tileImage, Image tileImageFlavour){
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileImage = tileImage;
        this.tileImageFlavour = tileImageFlavour;
    }

    /**
     * Barebone version of tile, only the necessary parts.
     * @param tileWidth The width of the tile in pixels.
     * @param tileHeight The height of the tile in pixels.
     * @param tileImage The image of the tile.
     */
    public Tile(int tileWidth, int tileHeight, Image tileImage){
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileImage = tileImage;
    }

    public Image getTileImage(){
        return this.tileImage;
    }
}
