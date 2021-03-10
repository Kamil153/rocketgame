package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import dev.kamilpolak.rocketgame.components.*;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ui.FlightTable;

import java.util.ArrayList;
import java.util.Collection;

public class FlightController {
    private final Entity rocket;
    private final FlightTable flightTable;
    private final Countdown countdown = new Countdown();
    private final OrthographicCamera camera;
    private boolean initiated = false;
    private boolean launched = false;
    private boolean controllable = false;
    private float initialCameraHeight;
    private final Collection<ITerminationListener> listeners = new ArrayList<>();

    private static final float COUNTDOWN_TIME = 5.0f;
    private static final float CAMERA_HEIGHT_FLIGHT = 350.0f;
    private static final float ZOOM_OUT_TIME = 3.0f;

    public FlightController(Entity rocket, FlightTable flightTable, OrthographicCamera camera) {
        this.rocket = rocket;
        this.flightTable = flightTable;
        this.camera = camera;
        flightTable.setCountdown(countdown);
    }

    public void initiate() {
        if(initiated) {
            throw new IllegalStateException("Flight already initiated");
        }
        countdown.setTime(COUNTDOWN_TIME);
        rocket.removeComponent(ThrustNoiseComponent.class);
        FuelComponent fuelComponent = rocket.getComponent(FuelComponent.class);
        fuelComponent.fuel = fuelComponent.maxFuel;
        initiated = true;
    }

    public void update(float deltaTime) {
        countdown.update(deltaTime);
        if(countdown.isPastT0() && initiated) {
            launched = true;
            rocket.getComponent(EngineStateComponent.class).running = true;
            initialCameraHeight = camera.viewportHeight;
        }
        if(launched && !controllable) {
            float progress = Math.min(1.0f, Math.abs(countdown.getTime())/ZOOM_OUT_TIME);
            float viewportHeight = Interpolation.smooth.apply(initialCameraHeight, CAMERA_HEIGHT_FLIGHT, progress);
            camera.viewportHeight = viewportHeight;
            camera.viewportWidth = Util.calculateViewportWidth(
                    Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight(),
                    viewportHeight);
            if(viewportHeight == CAMERA_HEIGHT_FLIGHT) {
                controllable = true;
                rocket.addComponent(new ControlComponent());
                rocket.addComponent(new ThrustNoiseComponent());
            }
        }
        if(initiated && rocket.hasComponent(FlightTerminationComponent.class)) {
            initiated = false;
            launched = false;
            controllable = false;
            notifyTerminationListeners();
        }
    }

    public void addListener(ITerminationListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ITerminationListener listener) {
        listeners.remove(listener);
    }

    public void notifyTerminationListeners() {
        for(ITerminationListener listener: listeners) {
            listener.flightTerminated();
        }
    }

    public boolean isInitiated() {
        return initiated;
    }

    public boolean isLaunched() {
        return launched;
    }

    public boolean isControllable() {
        return controllable;
    }
}
