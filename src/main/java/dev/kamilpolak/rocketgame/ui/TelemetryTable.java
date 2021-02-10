package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TelemetryTable extends Table {
    private static final String SPEED_FORMAT = "%d m/s";
    private static final String ALTITUDE_FORMAT = "%d m";

    private final Label speedLabel;
    private final Label altitudeLabel;

    TelemetryTable(LabelStyle labelStyle) {
        speedLabel = new Label(String.format(SPEED_FORMAT, 0), labelStyle);
        altitudeLabel = new Label(String.format(ALTITUDE_FORMAT, 0), labelStyle);
        add(new Label("Speed", labelStyle));
        add(speedLabel);
        row();
        add(new Label("Altitude", labelStyle));
        add(altitudeLabel);
    }
}
