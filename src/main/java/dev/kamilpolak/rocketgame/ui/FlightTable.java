package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.Countdown;
import dev.kamilpolak.rocketgame.EntityData;
import dev.kamilpolak.rocketgame.components.BodyComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class FlightTable extends Table {
    private final Entity rocket;
    private Countdown countdown = null;
    private final CountdownLabel countdownLabel;
    private final TelemetryTable telemetryTable;

    public FlightTable(Entity rocket, Skin skin) {
        super();
        this.rocket = rocket;
        setFillParent(true);

        countdownLabel = new CountdownLabel(skin);
        countdownLabel.setVisible(false);
        telemetryTable = new TelemetryTable(skin);
        top();
        add(countdownLabel).expandX().top();
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
        Body body = rocket.getComponent(BodyComponent.class).body;
        telemetryTable.setSpeed(body.getLinearVelocity().len());
        telemetryTable.setAltitude(body.getPosition().y - EntityData.ROCKET_POSITION.y);
        if(countdown != null) {
            countdownLabel.setTime(countdown.isPastT0(), countdown.getMinutes(), countdown.getSeconds());
        }
        super.act(delta);
    }
}
