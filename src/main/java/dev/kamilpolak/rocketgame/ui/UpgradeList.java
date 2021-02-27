package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import dev.kamilpolak.rocketgame.Upgrade;


public class UpgradeList extends VerticalGroup {
    private final VerticalGroup upgradesWidget = new VerticalGroup();
    private final Skin skin;

    public UpgradeList(Skin skin) {
        this.skin = skin;
        grow();
        addActor(new Label("Upgrades", skin));
        addActor(new ScrollPane(upgradesWidget, skin));
        upgradesWidget.left();
        upgradesWidget.grow();
    }

    public void addUpgrade(Upgrade upgrade) {
        upgradesWidget.addActor(new UpgradeListItem(upgrade, skin));
    }
}
