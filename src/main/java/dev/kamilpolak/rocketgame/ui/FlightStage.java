package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.Countdown;
import dev.kamilpolak.rocketgame.EntityData;
import dev.kamilpolak.rocketgame.components.BodyComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class FlightStage extends Stage {
    private final Entity rocket;
    private final Table table;
    private Countdown countdown = null;
    private final CountdownLabel countdownLabel;
    private final TelemetryTable telemetryTable;

    public FlightStage(Entity rocket, Skin skin) {
        super();
        this.rocket = rocket;
        table = new Table();
        table.setFillParent(true);
        addActor(table);

        countdownLabel = new CountdownLabel(skin);
        countdownLabel.setVisible(false);
        telemetryTable = new TelemetryTable(skin);
        table.top();
        table.add(countdownLabel).expandX().top();
        table.row().expand();
        table.add(telemetryTable).bottom().left();
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
