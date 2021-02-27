package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dev.kamilpolak.rocketgame.Upgrade;


public class UpgradeList extends Table {
    private final Table upgradesWidget = new Table();
    private final Skin skin;

    public UpgradeList(Skin skin) {
        this.skin = skin;
        add(new Label("Upgrades", skin)).expand().fill();
        row().space(10);
        ScrollPane scrollPane = new ScrollPane(upgradesWidget, skin);
        scrollPane.setFlickScroll(false);
        add(scrollPane).expand().fill();
        upgradesWidget.left();
        pad(10);
    }

    public void addUpgrade(Upgrade upgrade) {
        upgradesWidget.add(new UpgradeListItem(upgrade, skin)).grow();
        upgradesWidget.row();
    }
}
