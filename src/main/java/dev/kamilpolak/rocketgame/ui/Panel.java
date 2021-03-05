package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class Panel extends Table {
    private final Label titleLabel;
    private final Cell<?> contentCell;

    public Panel(Skin skin, String title) {
        titleLabel = new Label(title, skin);
        add(titleLabel).expandX().fill().top();
        row().space(10);
        contentCell = add(new Widget());
        contentCell.expand().fill();
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
