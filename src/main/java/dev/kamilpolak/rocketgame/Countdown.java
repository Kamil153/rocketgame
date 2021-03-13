package dev.kamilpolak.rocketgame;

public class Countdown {
    private float time;

    public Countdown(float time) {
        this.time = time;
    }

    public Countdown() {
        this(0);
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getTime() {
        return time;
    }

    public void passTime(float time) {
        this.time -= time;
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
}
