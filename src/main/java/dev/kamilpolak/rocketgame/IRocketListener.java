package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.math.Vector2;

import java.util.EventListener;

public interface IRocketListener extends EventListener {
    void positionChanged(Vector2 position);
    void fuelChanged(float fuel);
    void velocityChanged(float velocity);
}
