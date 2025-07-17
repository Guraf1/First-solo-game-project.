package org.gameproject.observer;

import org.gameproject.util.KeyHandler;

import java.util.ArrayList;
import java.util.List;

public class ObserverHandler {

    List<Observer> observers = new ArrayList<>();
    private static ObserverHandler instance;


    private ObserverHandler(){}


    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Object event) {
        for (Observer observer : observers) {
            observer.notify();
        }
    }

    public void updateObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public static ObserverHandler get(){
        if (ObserverHandler.instance == null) {
            ObserverHandler.instance = new ObserverHandler();
        }
        return ObserverHandler.instance;

    }
}
