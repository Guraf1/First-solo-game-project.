package org.gameproject.entities.map;


import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class Tile {
    protected int tileWidth;
    protected int tileHeight;
    protected boolean interactable = false;
    protected boolean collision = false;

    protected BufferedImage tileImage;
//    protected Image tileImage; //Base background image
//    protected Image tileImageFlavour; //Additional spice on top of the base image

    private Tile(TileBuilder blueprint){
        this.tileWidth = blueprint.tileWidth;
        this.tileHeight = blueprint.tileHeight;
        this.tileImage = blueprint.tileImage;
        this.interactable = blueprint.interactable;
        this.collision = blueprint.collision;
    }

    public static class TileBuilder{
        private final int tileWidth;
        private final int tileHeight;
        private final BufferedImage tileImage;
        private boolean collision = false;
        private boolean interactable = false;

        public TileBuilder(int tileWidth, int tileHeight, BufferedImage tileImage){
            this.tileWidth = tileWidth;
            this.tileHeight = tileHeight;
            this.tileImage = tileImage;
        }

        public TileBuilder interactable(boolean interactable){
            this.interactable = interactable;
            return this;
        }

        public TileBuilder collision(boolean collision){
            this.collision = collision;
            return this;
        }

        public Tile build(){
            return new Tile(this);
        }
    }

    public BufferedImage getTileImage(){
        return this.tileImage;
    }
}
