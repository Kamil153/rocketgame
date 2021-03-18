package dev.kamilpolak.rocketgame.models;

import java.util.EventListener;

public interface IRocketListener extends EventListener {
    void positionChanged(float x, float y);
    void fuelChanged(float fuel);
    void velocityChanged(float velocity);
}
