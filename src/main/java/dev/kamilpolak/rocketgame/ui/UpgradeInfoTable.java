package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.Upgrade;

public class UpgradeInfoTable extends Table {
    private final Label titleLabel;
    private final Label descriptionLabel;

    public UpgradeInfoTable(Skin skin, Upgrade upgrade) {
        this(skin);
        setUpgrade(upgrade);
    }

    public UpgradeInfoTable(Skin skin) {
        titleLabel = new Label("", skin);
        descriptionLabel = new Label("", skin);
        add(titleLabel).expand().fill();
        row().space(10);
        add(descriptionLabel).expand().fill();
        pad(10);
    }

    public void setUpgrade(Upgrade upgrade) {
        titleLabel.setText(upgrade.getName());
        descriptionLabel.setText(upgrade.getDescription());
    }
}
