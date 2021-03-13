package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import dev.kamilpolak.rocketgame.upgrades.Upgrade;

public class UpgradeListItem extends Table {
    private final Upgrade upgrade;
    private final Label priceLabel;
    private final Skin skin;

    public UpgradeListItem(Upgrade upgrade, Skin skin) {
        super(skin);
        this.upgrade = upgrade;
        this.skin = skin;
        setTouchable(Touchable.enabled);
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
            setBackground((Drawable) null);
        }
    }

    public Upgrade getUpgrade() {
        return upgrade;
    }

    public void setPriceVisible(boolean hidden) {
        priceLabel.setVisible(hidden);
    }
}
