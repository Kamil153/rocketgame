package dev.kamilpolak.rocketgame.models;

import java.util.EventListener;

public interface ICountdownListener extends EventListener {
    void timeChanged(float oldTime, float deltaTime, float newTime);
}
