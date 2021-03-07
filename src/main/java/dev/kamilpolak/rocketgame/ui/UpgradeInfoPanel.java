package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import dev.kamilpolak.rocketgame.upgrades.Upgrade;

import java.util.ArrayList;
import java.util.Collection;

public class UpgradeInfoPanel extends Panel {
    private final Label descriptionLabel;
    private final Button buyButton;
    private Upgrade upgrade = null;
    private Collection<IBuyListener> buyListeners = new ArrayList<>();

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
        buyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                notifyBuyListeners(upgrade);
            }
        });
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

    public void addBuyButtonListener(IBuyListener listener) {
        buyListeners.add(listener);
    }

    private void notifyBuyListeners(Upgrade upgrade) {
        for(IBuyListener listener: buyListeners) {
            listener.clickedBuy(upgrade);
        }
    }

    public void setBuyButtonVisible(boolean visible) {
        buyButton.setVisible(false);
    }
}
