package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Collection;

public class Rocket {
    private float velocity = 0.0f;
    private final Vector2 position = new Vector2();
    private float fuel = 0.0f;
    private final Collection<IRocketListener> listeners = new ArrayList<>();

    public void addListener(IRocketListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IRocketListener listener) {
        listeners.remove(listener);
    }

    private void notifyVelocityChanged() {
        for(IRocketListener listener: listeners) {
            listener.velocityChanged(velocity);
        }
    }

    private void notifyFuelChanged() {
        for(IRocketListener listener: listeners) {
            listener.fuelChanged(fuel);
        }
    }

    private void notifyPositionChanged() {
        for(IRocketListener listener: listeners) {
            listener.positionChanged(position.x, position.y);
        }
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        if(this.velocity != velocity) {
            this.velocity = velocity;
            notifyVelocityChanged();
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        if(this.position != position) {
            this.position.set(position);
            notifyPositionChanged();
        }
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        if(this.fuel != fuel) {
            this.fuel = fuel;
            notifyFuelChanged();
        }
    }
}
