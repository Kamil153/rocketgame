package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dev.kamilpolak.rocketgame.upgrades.Upgrade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class UpgradeListPanel extends Panel {
    private final Table upgradesWidget = new Table();
    private final Skin skin;
    private UpgradeListItem selected = null;
    private final Collection<IUpgradeSelectionListener> selectionListeners = new ArrayList<>();
    private final Map<Upgrade, UpgradeListItem> items = new HashMap<>();

    public UpgradeListPanel(Skin skin) {
        super(skin, "Upgrades");
        this.skin = skin;
        ScrollPane scrollPane = new ScrollPane(upgradesWidget, skin);
        scrollPane.setFlickScroll(false);
        setContent(scrollPane);
        upgradesWidget.left();
        pad(10);
    }

    public UpgradeListItem addUpgrade(Upgrade upgrade) {
        if(items.containsKey(upgrade)) {
            throw new IllegalArgumentException("Upgrade has already been added");
        }
        UpgradeListItem item = new UpgradeListItem(upgrade, skin);
        items.put(upgrade, item);
        upgradesWidget.add(item).top().expandX().fill();
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
                notifySelectionListeners(item.getUpgrade());
            }
        });
        return item;
    }

    private void notifySelectionListeners(Upgrade upgrade) {
        for(IUpgradeSelectionListener listener: selectionListeners) {
            listener.selected(upgrade);
        }
    }

    public void addUpgradeSelectionListener(IUpgradeSelectionListener listener) {
        selectionListeners.add(listener);
    }

    public UpgradeListItem getListItem(Upgrade upgrade) {
        return items.get(upgrade);
    }
}
