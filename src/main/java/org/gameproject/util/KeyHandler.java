package org.gameproject.util;

import javafx.scene.input.KeyCode;

public class KeyHandler {

    private static KeyHandler instance;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;


    private KeyHandler() {
    }

    /**
     * Returns the singleton instance of KeyHandler.
     * If the instance is null, it creates a new instance.
     *
     * @return the singleton instance of KeyHandler
     */
    public static KeyHandler get() {
        if (KeyHandler.instance == null) {
            KeyHandler.instance = new KeyHandler();
        }
        return KeyHandler.instance;
    }


    /**
     * Processes key events to update the state of the movement keys when pressed.
     */

    public void handleKeyPress(KeyCode keyCode) {

        switch (keyCode) {
            case W, UP:
                upPressed = true;
                break;
            case S, DOWN:
                downPressed = true;
                break;
            case A, LEFT:
                leftPressed = true;
                break;
            case D, RIGHT:
                rightPressed = true;
                break;
            default:
                break;
        }

    }


    /**
     * Processes key events to update the state of the movement keys when they are released.
     */

    public void handleKeyRelease(KeyCode keyCode) {

        switch (keyCode) {
            case W, UP:
                upPressed = false;
                break;
            case S, DOWN:
                downPressed = false;
                break;
            case A, LEFT:
                leftPressed = false;
                break;
            case D, RIGHT:
                rightPressed = false;
                break;
            default:
                break;
        }
    }


    /**
     * Checks if the upPressed key is currently pressed.
     * @return true if the up key is pressed, false otherwise.
     */
    public boolean isUpPressed() {
        return get().upPressed;
    }

    /**
     * Checks if the downPressed key is currently pressed.
     * @return true if the down key is pressed, false otherwise.
     */
    public boolean isDownPressed() {
        return get().downPressed;
    }

    /**
     * Checks if the leftPressed key is currently pressed.
     * @return true if the left key is pressed, false otherwise.
     */
    public boolean isLeftPressed() {
        return get().leftPressed;
    }

    /**
     * Checks if the rightPressed key is currently pressed.
     * @return true if the right key is pressed, false otherwise.
     */
    public boolean isRightPressed() {
        return get().rightPressed;
    }
}