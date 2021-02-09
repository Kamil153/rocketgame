package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class FlightStage extends Stage {
    private final Entity rocket;
    private final Table table;
    private final BitmapFont font;

    public FlightStage(Entity rocket, BitmapFont font) {
        this.rocket = rocket;
        this.font = font;

        table = new Table();
        table.setFillParent(true);
        addActor(table);

        table.setDebug(true);
    }
}
