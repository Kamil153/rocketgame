package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

public class UpgradeList extends VerticalGroup {
    private final VerticalGroup upgrades;
    private final Skin skin;

    public UpgradeList(Skin skin) {
        this.skin = skin;
        addActor(new Label("Upgrades", skin));
        upgrades = new VerticalGroup();
        addActor(new ScrollPane(upgrades, skin));
    }

    public void addUpgrade(String name) {
        upgrades.addActor(new UpgradeListItem(name, skin));
    }
}
