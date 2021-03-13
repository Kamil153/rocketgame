package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.Countdown;
import dev.kamilpolak.rocketgame.ICountdownListener;
import dev.kamilpolak.rocketgame.Rocket;

public class FlightTable extends Table implements ICountdownListener {
    private Countdown countdown = null;
    private final CountdownLabel countdownLabel;
    private final TelemetryTable telemetryTable;

    public FlightTable(Rocket rocket, Skin skin) {
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
        if(this.countdown != null) {
            this.countdown.removeListener(this);
        }
        this.countdown = countdown;
        countdown.addListener(this);
        countdownLabel.setVisible(true);
    }

    public void removeCountdown() {
        if(this.countdown != null) {
            this.countdown.removeListener(this);
            this.countdown = null;
        }
        countdownLabel.setVisible(false);
    }

    @Override
    public void timeChanged(float oldTime, float deltaTime, float newTime) {
        countdownLabel.setTime(countdown.isPastT0(), countdown.getMinutes(), countdown.getSeconds());
    }
}
