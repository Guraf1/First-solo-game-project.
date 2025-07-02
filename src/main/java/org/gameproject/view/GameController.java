package org.gameproject.view;

import javafx.scene.canvas.GraphicsContext;
import org.gameproject.entities.creatures.Creature;
import org.gameproject.util.TileManager;

public class GameController {

    private Game game;
    private Renderer renderer;
    private TileManager tileManager;

    public GameController(Game game){
        this.game = game;
        this.tileManager = new TileManager(game);
        this.renderer = new Renderer(this.game,
                null,
                null,
                this.tileManager);
    }

    public void drawCreature(Creature creature){
        GraphicsContext gc = game.getCanvas().getGraphicsContext2D();
        this.renderer.setGraphicsContext(gc);
        this.renderer.setCreature(creature);
        renderer.drawCreature();

    }

    public void clearOldFrame(){
        GraphicsContext gc = game.getCanvas().getGraphicsContext2D();
        this.renderer.setGraphicsContext(gc);
//        Renderer renderer = new Renderer(this.game, gc, null);
        renderer.clear();
    }

    public void drawTiles(){
        GraphicsContext gc = game.getCanvas().getGraphicsContext2D();
        this.renderer.setGraphicsContext(gc);
        this.renderer.drawTiles();
    }


}
