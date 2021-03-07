package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import dev.kamilpolak.rocketgame.upgrades.Upgrade;

public class UpgradeInfoPanel extends Panel {
    private final Label descriptionLabel;
    private final Button buyButton;
    private Upgrade upgrade = null;

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
        this.upgrade = upgrade;
    }

    public Upgrade getUpgrade() {
        return upgrade;
    }

    public void addBuyButtonListener(EventListener listener) {
        buyButton.addListener(listener);
    }

    public void setBuyButtonVisible(boolean visible) {
        buyButton.setVisible(false);
    }
}
