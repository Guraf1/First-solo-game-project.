package org.gameproject.util;

import org.gameproject.entities.creatures.Creature;
import org.gameproject.entities.creatures.Player;
import org.gameproject.view.Game;

import java.awt.*;

public class CollisionChecker {

    private final Game game;
    private final TileManager tileManager;

    /**
     * CollisionChecker is responsible for checking collisions between entities in the game.
     * It uses the Game instance to access the player and other game entities.
     *
     * @param game The Game instance that contains the player and other entities.
     */
    public CollisionChecker(Game game, TileManager tileManager) {
        this.game = game;
        this.tileManager = tileManager;
    }

    public void checkCollision(Creature creature) {
        // Check if the creature collides with the player
        if (!(creature instanceof Player) && creature.getHitbox().intersects(game.getPlayer().getHitbox())) {
            // Handle collision logic here, e.g., reduce health, trigger events, etc.
            System.out.println("Collision detected between " + creature.getName() + " and Player.");
            // Example: game.getPlayer().reduceHealth(creature.getAttackDamage());
        }

        int entityLeftWorldX = creature.getWorldX() + creature.getHitbox().x;
        int entityRightWorldX = creature.getWorldX() + creature.getHitbox().x + creature.getHitbox().width;
        int entityTopWorldY = creature.getWorldY() + creature.getHitbox().y;
        int entityBottomWorldY = creature.getWorldY() + creature.getHitbox().y + creature.getHitbox().height;

        int entityLeftCollumn = entityLeftWorldX / game.getTileSize();
        int entityRightCollumn = entityRightWorldX / game.getTileSize();
        int entityTopRow = entityTopWorldY / game.getTileSize();
        int entityBottomRow = entityBottomWorldY / game.getTileSize();

        int tileNumber1;
        int tileNumber2;

        switch (creature.getLastDirection()) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - creature.getSpeed()) / game.getTileSize();
                tileNumber1 = tileManager.getMapTileNumber(entityLeftCollumn, entityTopRow);
                tileNumber2 = tileManager.getMapTileNumber(entityRightCollumn, entityTopRow);
                if (tileManager.getListOfTiles().get(tileNumber1).isCollision() ||
                        tileManager.getListOfTiles().get(tileNumber2).isCollision()) {
                    creature.setCollisionState(true);
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + creature.getSpeed()) / game.getTileSize();
                tileNumber1 = tileManager.getMapTileNumber(entityLeftCollumn, entityBottomRow);
                tileNumber2 = tileManager.getMapTileNumber(entityRightCollumn, entityBottomRow);
                if (tileManager.getListOfTiles().get(tileNumber1).isCollision() ||
                        tileManager.getListOfTiles().get(tileNumber2).isCollision()) {
                    creature.setCollisionState(true);
                }
            }
            case "left" -> {
                entityLeftCollumn = (entityLeftWorldX - creature.getSpeed()) / game.getTileSize();
                tileNumber1 = tileManager.getMapTileNumber(entityLeftCollumn, entityTopRow);
                tileNumber2 = tileManager.getMapTileNumber(entityLeftCollumn, entityBottomRow);
                if (tileManager.getListOfTiles().get(tileNumber1).isCollision() ||
                        tileManager.getListOfTiles().get(tileNumber2).isCollision()) {
                    creature.setCollisionState(true);

                }
            }
            case "right" -> {
                entityRightCollumn = (entityRightWorldX + creature.getSpeed()) / game.getTileSize();
                tileNumber1 = tileManager.getMapTileNumber(entityRightCollumn, entityTopRow);
                tileNumber2 = tileManager.getMapTileNumber(entityRightCollumn, entityBottomRow);
                if (tileManager.getListOfTiles().get(tileNumber1).isCollision() ||
                        tileManager.getListOfTiles().get(tileNumber2).isCollision()) {
                    creature.setCollisionState(true);
                }
            }
        }
    }

    /**
     * Checks if a creature will collide with any solid tiles, or creatures in the future based on its current position and movement.
     * This method calculates the future position of the creature and checks for collisions with solid tiles.
     *
     *
     * @param creature The creature to check for future collisions.
     * @param deltaX   The change in the X position (movement in the X direction).
     * @param deltaY   The change in the Y position (movement in the Y direction).
     * @return true if a collision is detected, false otherwise.
     */
    public boolean checkFutureCollision(Creature creature, int deltaX, int deltaY) {
        int futureX = creature.getWorldX() + deltaX;
        int futureY = creature.getWorldY() + deltaY;

        // Calculate future hitbox boundaries
        int entityLeftWorldX = futureX + creature.getHitbox().x;
        int entityRightWorldX = futureX + creature.getHitbox().x + creature.getHitbox().width;
        int entityTopWorldY = futureY + creature.getHitbox().y;
        int entityBottomWorldY = futureY + creature.getHitbox().y + creature.getHitbox().height;

        // Calculate the rows and columns these boundaries fall on
        int entityLeftColumn = entityLeftWorldX / game.getTileSize();
        int entityRightColumn = entityRightWorldX / game.getTileSize();
        int entityTopRow = entityTopWorldY / game.getTileSize();
        int entityBottomRow = entityBottomWorldY / game.getTileSize();

        // Check if any of the tiles at these positions are solid
        int tileNum1, tileNum2;
        boolean foundCollision = false;

        // Check each corner of the hitbox
        tileNum1 = tileManager.getMapTileNumber(entityLeftColumn, entityTopRow);
        tileNum2 = tileManager.getMapTileNumber(entityRightColumn, entityTopRow);
        if (tileManager.getListOfTiles().get(tileNum1).isCollision() ||
                tileManager.getListOfTiles().get(tileNum2).isCollision()) {
            foundCollision = true;
        }

        tileNum1 = tileManager.getMapTileNumber(entityLeftColumn, entityBottomRow);
        tileNum2 = tileManager.getMapTileNumber(entityRightColumn, entityBottomRow);
        if (tileManager.getListOfTiles().get(tileNum1).isCollision() ||
                tileManager.getListOfTiles().get(tileNum2).isCollision()) {
            foundCollision = true;
        }

        // Checks collision between a creature and the player
        if (!(creature instanceof Player)) {
            Rectangle futureHitbox = new Rectangle(
                    futureX + creature.getHitbox().x,
                    futureY + creature.getHitbox().y,
                    creature.getHitbox().width,
                    creature.getHitbox().height
            );
            return futureHitbox.intersects(game.getPlayer().getHitbox());
        }

        return foundCollision;
    }

    }

