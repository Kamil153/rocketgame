package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class FlightStage extends Stage {
    private final Entity rocket;
    private final Table table;
    private final BitmapFont font;
    private final Countdown countdown = null;
    private final Label countdownLabel;

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
        table.add(countdownLabel);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
