package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class MenuStage extends Stage {
    private final Entity rocket;
    private final Table table;
    private final TextButton launchButton;

    public MenuStage(Entity rocket, Skin skin) {
        super();
        this.rocket = rocket;
        this.table = new Table();
        table.setFillParent(true);
        addActor(table);
        launchButton = new TextButton("Launch", skin);
        table.add(launchButton);
    }

    public void addLaunchListener(EventListener listener) {
        launchButton.addListener(listener);
    }
}
