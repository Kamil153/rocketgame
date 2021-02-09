package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class FlightStage extends Stage {
    private final Entity rocket;
    private final Table table;

    public FlightStage(Entity rocket) {
        this.rocket = rocket;

        table = new Table();
        table.setFillParent(true);
        addActor(table);

        table.setDebug(true);
    }
}
