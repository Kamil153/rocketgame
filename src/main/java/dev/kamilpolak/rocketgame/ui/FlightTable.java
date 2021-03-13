package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.Countdown;
import dev.kamilpolak.rocketgame.Rocket;

public class FlightTable extends Table {
    private final Rocket rocket;
    private Countdown countdown = null;
    private final CountdownLabel countdownLabel;
    private final TelemetryTable telemetryTable;

    public FlightTable(Rocket rocket, Skin skin) {
        super();
        this.rocket = rocket;
        setFillParent(true);

        countdownLabel = new CountdownLabel(skin);
        countdownLabel.setVisible(false);
        telemetryTable = new TelemetryTable(rocket, skin);
        top();
        add(countdownLabel).expandX().top().colspan(2);
        row().expand();
        add(telemetryTable).bottom().left();
    }

    public void setCountdown(Countdown countdown) {
        this.countdown = countdown;
        countdownLabel.setVisible(true);
    }

    public void removeCountdown() {
        this.countdown = null;
        countdownLabel.setVisible(false);
    }

    @Override
    public void act(float delta) {
        if(countdown != null) {
            countdownLabel.setTime(countdown.isPastT0(), countdown.getMinutes(), countdown.getSeconds());
        }
        super.act(delta);
    }
}
