package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.Upgrade;

public class UpgradeInfoTable extends Table {
    public UpgradeInfoTable(Skin skin, Upgrade upgrade) {
        add(new Label(upgrade.getName(), skin)).expand().fill();
        row().space(10);
        add(new Label(upgrade.getDescription(), skin)).expand().fill();
        pad(10);
    }
}
