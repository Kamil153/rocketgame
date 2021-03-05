package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dev.kamilpolak.rocketgame.Upgrade;

import java.util.ArrayList;
import java.util.Collection;


public class UpgradeListPanel extends Panel {
    private final Table upgradesWidget = new Table();
    private final Skin skin;
    private UpgradeListItem selected = null;
    private final Collection<UpgradeSelectionListener> selectionListeners = new ArrayList<>();

    public UpgradeListPanel(Skin skin) {
        super(skin, "Upgrades");
        this.skin = skin;
        ScrollPane scrollPane = new ScrollPane(upgradesWidget, skin);
        scrollPane.setFlickScroll(false);
        setContent(scrollPane);
        upgradesWidget.left();
        pad(10);
    }

    public void addUpgrade(Upgrade upgrade) {
        UpgradeListItem item = new UpgradeListItem(upgrade, skin);
        upgradesWidget.add(item).grow();
        upgradesWidget.row();
        item.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(selected != null) {
                    selected.setHighlighted(false);
                }
                item.setHighlighted(true);
                selected = item;
                for(UpgradeSelectionListener listener: selectionListeners) {
                    listener.selected(item.getUpgrade());
                }
            }
        });
    }

    private void notifySelectionListeners(Upgrade upgrade) {
        for(UpgradeSelectionListener listener: selectionListeners) {
            listener.selected(upgrade);
        }
    }

    public void addUpgradeSelectionListener(UpgradeSelectionListener listener) {
        selectionListeners.add(listener);
    }
}
