package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.Upgrade;

public class UpgradeListItem extends Table {
    private final Upgrade upgrade;
    private final Label priceLabel;

    public UpgradeListItem(Upgrade upgrade, Skin skin) {
        this.upgrade = upgrade;
        Label nameLabel = new Label(upgrade.getName(), skin);
        add(nameLabel).expandX().left();
        priceLabel = new Label("$" + upgrade.getPrice(), skin);
        add(priceLabel);
    }

    public void setPriceColor(Color color) {
        priceLabel.setColor(color);
    }
}
