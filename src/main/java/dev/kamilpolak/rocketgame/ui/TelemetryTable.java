package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TelemetryTable extends Table {
    private static final String SPEED_FORMAT = "%f m/s";
    private static final String ALTITUDE_FORMAT = "%f m";

    private final Label speedLabel;
    private final Label altitudeLabel;

    TelemetryTable(LabelStyle labelStyle) {
        speedLabel = new Label(String.format(SPEED_FORMAT, 0.0f), labelStyle);
        altitudeLabel = new Label(String.format(ALTITUDE_FORMAT, 0.0f), labelStyle);
        add(new Label("Speed", labelStyle));
        add(speedLabel);
        row();
        add(new Label("Altitude", labelStyle));
        add(altitudeLabel);
    }

    public void setSpeed(float speed) {
        speedLabel.setText(String.format(SPEED_FORMAT, speed));
    }

    public void setAltitude(float altitude) {
        altitudeLabel.setText(String.format(ALTITUDE_FORMAT, altitude));
    }
}
