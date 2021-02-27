package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import dev.kamilpolak.rocketgame.Upgrade;

public class UpgradeListItem extends Label {
    private final Upgrade upgrade;

    public UpgradeListItem(Upgrade upgrade, Skin skin) {
        super(upgrade.getName(), skin);
        this.upgrade = upgrade;
    }
}
