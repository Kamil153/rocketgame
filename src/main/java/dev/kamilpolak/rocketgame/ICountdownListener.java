package dev.kamilpolak.rocketgame;

import java.util.EventListener;

public interface ICountdownListener extends EventListener {
    void timePassed(float passed, float newTime);
}
