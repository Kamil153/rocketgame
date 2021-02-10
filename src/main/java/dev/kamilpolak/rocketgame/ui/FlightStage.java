package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.Countdown;
import dev.kamilpolak.rocketgame.components.BodyComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class FlightStage extends Stage {
    private final Entity rocket;
    private final Table table;
    private final BitmapFont font;
    private Countdown countdown = null;
    private final CountdownLabel countdownLabel;
    private final TelemetryTable telemetryTable;

    public FlightStage(Entity rocket, BitmapFont font) {
        this.rocket = rocket;
        this.font = font;

        table = new Table();
        table.setFillParent(true);
        addActor(table);

        table.debug();

        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font;
        countdownLabel = new CountdownLabel(labelStyle);
        countdownLabel.setVisible(false);
        telemetryTable = new TelemetryTable(labelStyle);
        table.add(telemetryTable);
        table.add(countdownLabel).expand().top();
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
        telemetryTable.setAltitude(body.getPosition().y);
        if(countdown != null) {
            countdownLabel.setTime(countdown.isPastT0(), countdown.getMinutes(), countdown.getSeconds());
        }
        super.act(delta);
    }
}
