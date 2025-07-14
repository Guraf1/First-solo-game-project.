package org.gameproject.util;

import javafx.animation.AnimationTimer;

import java.util.HashMap;
import java.util.Map;

import net.java.games.input.*;

public class ControllerHandler {
    private static ControllerHandler instance;
    private final Map<Integer, Boolean> controllerButtons =  new HashMap<>();
    private double leftStickX = 0;
    private double leftStickY = 0;
    private final double deadzone = 0.15;

    public ControllerHandler(){
        scanForControllers();
    }

    public static ControllerHandler get() {
        if (instance == null) {
            instance = new ControllerHandler();
        }
        return instance;
        }

        private void scanForControllers(){
            Event event = new Event();

            /* Get the available controllers */
            Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
            for (Controller controller : controllers) {
                /* Remember to poll each one */
                controller.poll();

                /* Get the controllers event queue */
                EventQueue queue = controller.getEventQueue();

                /* For each object in the queue */
                while (queue.getNextEvent(event)) {
                    /* Get event component */
                    Component comp = event.getComponent();

                    /* Process event (your awesome code) */


                }
            }
        }

    }

