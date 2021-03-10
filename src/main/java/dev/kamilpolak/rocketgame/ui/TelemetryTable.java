package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TelemetryTable extends Table {
    private static final String SPEED_FORMAT = "%.0f m/s";
    private static final String ALTITUDE_FORMAT_M = "%.0f m";
    private static final String ALTITUDE_FORMAT_KM = "%.1f km";
    private static final String FUEL_FORMAT = "%.0f%%";

    private final Label speedLabel;
    private final Label altitudeLabel;
    private final Label fuelLabel;

    TelemetryTable(Skin skin) {
        super();
        speedLabel = new Label(String.format(SPEED_FORMAT, 0.0f), skin);
        altitudeLabel = new Label(String.format(ALTITUDE_FORMAT_M, 0.0f), skin);
        fuelLabel = new Label(String.format(FUEL_FORMAT, 0.0f), skin);
        add(new Label("Speed", skin)).right();
        add(speedLabel).left().spaceLeft(20);
        row();
        add(new Label("Altitude", skin)).right();
        add(altitudeLabel).left().spaceLeft(20);
        row();
        add(new Label("Fuel", skin)).right();
        add(fuelLabel).left().spaceLeft(20);

    }

    public void setSpeed(float speed) {
        speedLabel.setText(String.format(SPEED_FORMAT, speed));
    }

    public void setAltitude(float altitude) {
        String text;
        if(Math.abs(altitude) < 1000) {
            text = String.format(ALTITUDE_FORMAT_M, altitude);
        }
        else {
            text = String.format(ALTITUDE_FORMAT_KM, altitude/1000.0f);
        }
        altitudeLabel.setText(text);
    }

    public void setFuel(float fuelPercent) {
        fuelLabel.setText(String.format(FUEL_FORMAT, fuelPercent*100));
    }
}
