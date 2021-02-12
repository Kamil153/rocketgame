package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class UpgradeListItem extends Label {
    private final String name;

    public UpgradeListItem(String name, Skin skin) {
        super(name, skin);
        this.name = name;
    }
}
