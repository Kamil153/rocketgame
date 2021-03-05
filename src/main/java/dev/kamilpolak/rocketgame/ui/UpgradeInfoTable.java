package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.Upgrade;

public class UpgradeInfoTable extends Panel {
    private final Label descriptionLabel;

    public UpgradeInfoTable(Skin skin, Upgrade upgrade) {
        this(skin);
        setUpgrade(upgrade);
    }

    public UpgradeInfoTable(Skin skin) {
        super(skin, "");
        descriptionLabel = new Label("", skin);
        descriptionLabel.setWrap(true);
        setContent(descriptionLabel);
    }

    public void setUpgrade(Upgrade upgrade) {
        setTitle(upgrade.getName());
        descriptionLabel.setText(upgrade.getDescription());
    }
}
