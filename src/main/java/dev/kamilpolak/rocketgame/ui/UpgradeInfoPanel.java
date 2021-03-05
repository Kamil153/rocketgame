package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import dev.kamilpolak.rocketgame.Upgrade;

public class UpgradeInfoPanel extends Panel {
    private final Label descriptionLabel;
    private final Button buyButton;

    public UpgradeInfoPanel(Skin skin, Upgrade upgrade) {
        this(skin);
        setUpgrade(upgrade);
    }

    public UpgradeInfoPanel(Skin skin) {
        super(skin, "");
        descriptionLabel = new Label("", skin);
        descriptionLabel.setWrap(true);
        setContent(descriptionLabel);
        buyButton = new TextButton("Buy", skin);
        setTitleExtra(buyButton);
        setVisible(false);
    }

    public void setUpgrade(Upgrade upgrade) {
        setTitle(upgrade.getName());
        descriptionLabel.setText(upgrade.getDescription());
        setVisible(true);
    }
}
