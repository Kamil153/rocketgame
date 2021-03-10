package dev.kamilpolak.rocketgame;

import java.util.EventListener;

public interface ITerminationListener extends EventListener {
    void flightTerminated();
}
