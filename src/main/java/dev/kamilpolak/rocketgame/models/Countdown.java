package dev.kamilpolak.rocketgame.models;

import java.util.ArrayList;
import java.util.Collection;

public class Countdown {
    private float time;
    private final Collection<ICountdownListener> listeners = new ArrayList<>();

    public Countdown(float time) {
        this.time = time;
    }

    public Countdown() {
        this(0);
    }

    public void setTime(float time) {
        float oldTime = this.time;
        this.time = time;
        notifyTimeChanged(oldTime, time - oldTime, oldTime);
    }

    public float getTime() {
        return time;
    }

    public void passTime(float time) {
        float oldTime = this.time;
        this.time -= time;
        notifyTimeChanged(oldTime, time, this.time);
    }

    public int getSeconds() {
        return (int)Math.abs(Math.ceil(time)) % 60;
    }

    public int getMinutes() {
        return (int)Math.abs(Math.ceil(time)) / 60;
    }

    public boolean isPastT0() {
        return time < 0;
    }

    public void addListener(ICountdownListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ICountdownListener listener) {
        listeners.remove(listener);
    }

    public void notifyTimeChanged(float oldTime, float deltaTime, float newTime) {
        for(ICountdownListener listener: listeners) {
            listener.timeChanged(oldTime, deltaTime, newTime);
        }
    }
}
