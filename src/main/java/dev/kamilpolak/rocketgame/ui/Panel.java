package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Panel extends Table {
    private final Label titleLabel;
    private final Cell<?> contentCell;

    public Panel(Skin skin, String title) {
        titleLabel = new Label(title, skin);
        add(titleLabel).expand().fill();
        contentCell = row();
        contentCell.space(10);
        pad(10);
    }

    public Panel(Skin skin, String title, Actor content) {
        this(skin, title);
        setContent(content);
    }

    public void setContent(Actor actor) {
        contentCell.setActor(actor);
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }
}
