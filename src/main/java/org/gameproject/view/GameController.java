package org.gameproject.view;

import javafx.scene.canvas.GraphicsContext;
import org.gameproject.entities.creatures.Creature;
import org.gameproject.entities.creatures.Player;
import org.gameproject.events.EventHandler;
import org.gameproject.events.GameEvent;
import org.gameproject.events.GameEventListener;
import org.gameproject.events.OnPlayerMoveEvent;
import org.gameproject.util.TileManager;

public class GameController implements GameEventListener {

    private final Game game;
    private final Renderer renderer;

    public GameController(Game game, TileManager tileManager) {
        this.game = game;
        this.renderer = new Renderer(this.game,
                null,
                game.getPlayer(),
                tileManager);
        EventHandler.get().addListener(this);
    }

    public void renderFrame() {
        prepareRenderer();
        clearOldFrame();
        drawTiles();
        drawCreature(game.getPlayer());
        drawMoney();
    }

    public void prepareRenderer() {
        GraphicsContext gc = game.getCanvas().getGraphicsContext2D();
        this.renderer.setGraphicsContext(gc);
    }

    public void drawCreature(Creature creature) {
        GraphicsContext gc = game.getCanvas().getGraphicsContext2D();
        this.renderer.setGraphicsContext(gc);
        this.renderer.setCreature(creature);
        renderer.drawCreature();

    }

    public void clearOldFrame() {
        GraphicsContext gc = game.getCanvas().getGraphicsContext2D();
        this.renderer.setGraphicsContext(gc);
//        Renderer renderer = new Renderer(this.game, gc, null);
        renderer.clear();
    }

    public void drawTiles() {
        GraphicsContext gc = game.getCanvas().getGraphicsContext2D();
        this.renderer.setGraphicsContext(gc);
        this.renderer.drawTiles();
    }

    public void changeMap(String mapToLoad) {
        this.renderer.setMapToLoad(mapToLoad);
        this.renderer.loadMap();
    }

    public void drawMoney() {

        this.renderer.drawMoney();
    }


    @Override
    public void handleGameEvent(GameEvent event) {
        switch (event.getType()) {
            case CREATURE_DEATH -> ((Creature) event.getSource()).onDeath();
            case COLLISION -> game.getCollisionChecker().checkCollision((Creature) event.getSource());
            case PLAYER_MOVE -> {
                if (event.getSource() instanceof Player player && event instanceof OnPlayerMoveEvent moveEvent) {
                    player.updatePosition(moveEvent.getDeltaX(), moveEvent.getDeltaY());
                }
            }
        }
        }
    }

