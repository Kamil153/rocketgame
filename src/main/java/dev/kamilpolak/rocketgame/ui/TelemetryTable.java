package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.IRocketListener;
import dev.kamilpolak.rocketgame.Rocket;

public class TelemetryTable extends Table implements IRocketListener {
    private static final String SPEED_FORMAT = "%.0f m/s";
    private static final String ALTITUDE_FORMAT_M = "%.0f m";
    private static final String ALTITUDE_FORMAT_KM = "%.1f km";
    private static final String FUEL_FORMAT = "%.0fl";

    private final Label speedLabel;
    private final Label altitudeLabel;
    private final Label fuelLabel;
    private final Rocket rocket;

    TelemetryTable(Rocket rocket, Skin skin) {
        this.rocket = rocket;
        rocket.addListener(this);
        speedLabel = new Label(String.format(SPEED_FORMAT, rocket.getVelocity()), skin);
        altitudeLabel = new Label(String.format(ALTITUDE_FORMAT_M, rocket.getPosition().y), skin);
        fuelLabel = new Label(String.format(FUEL_FORMAT, rocket.getFuel()), skin);
        add(new Label("Speed", skin)).right();
        add(speedLabel).left().spaceLeft(20);
        row();
        add(new Label("Altitude", skin)).right();
        add(altitudeLabel).left().spaceLeft(20);
        row();
        add(new Label("Fuel", skin)).right();
        add(fuelLabel).left().spaceLeft(20);

    }

    @Override
    public void positionChanged(float x, float y) {
        String text;
        if(Math.abs(y) < 1000) {
            text = String.format(ALTITUDE_FORMAT_M, y);
        }
        else {
            text = String.format(ALTITUDE_FORMAT_KM, y/1000.0f);
        }
        altitudeLabel.setText(text);
    }

    @Override
    public void fuelChanged(float fuel) {
        fuelLabel.setText(String.format(FUEL_FORMAT, fuel));
    }

    @Override
    public void velocityChanged(float velocity) {
        speedLabel.setText(String.format(SPEED_FORMAT, velocity));
    }
}
