package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.Upgrade;

public class UpgradeListItem extends Table {
    private final Upgrade upgrade;
    private final Label priceLabel;
    private final Skin skin;

    public UpgradeListItem(Upgrade upgrade, Skin skin) {
        this.upgrade = upgrade;
        this.skin = skin;
        Label nameLabel = new Label(upgrade.getName(), skin);
        add(nameLabel).expandX().left();
        priceLabel = new Label("$" + upgrade.getPrice(), skin);
        add(priceLabel);
        pad(5);
    }

    public void setPriceColor(Color color) {
        priceLabel.setColor(color);
    }

    public void setHighlighted(boolean highlighted) {
        if(highlighted) {
            setBackground("selection");
        }
        else {
            setBackground("default");
        }
    }
}
