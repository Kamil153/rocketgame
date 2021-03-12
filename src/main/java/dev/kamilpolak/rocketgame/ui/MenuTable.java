package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import dev.kamilpolak.rocketgame.Player;
import dev.kamilpolak.rocketgame.upgrades.Upgrade;
import dev.kamilpolak.rocketgame.ecs.Entity;

import java.util.ArrayList;
import java.util.Collection;

public class MenuTable extends Table {
    private final Entity rocket;
    private final TextButton launchButton;
    private final UpgradeListPanel upgradeList;
    private final UpgradeInfoPanel upgradeInfo;
    private final MoneyLabel moneyLabel;
    private final Collection<ILaunchListener> launchListeners = new ArrayList<>();

    private static final float SIDEBAR_WIDTH_PERCENT = 0.35f;
    private static final float UPGRADE_LIST_HEIGHT_PERCENT = 0.65f;

    public MenuTable(Entity rocket, Skin skin) {
        super();
        this.rocket = rocket;
        this.upgradeList = new UpgradeListPanel(skin);
        this.upgradeInfo = new UpgradeInfoPanel(skin);
        setFillParent(true);
        pad(10);
        Table leftSidebar = new Table(skin);
        moneyLabel = new MoneyLabel(skin);
        leftSidebar.add(moneyLabel);
        launchButton = new TextButton("Launch", skin);
        launchButton.pad(5, 20, 5, 20);
        add(leftSidebar).expandY().width(Value.percentWidth(SIDEBAR_WIDTH_PERCENT, this));
        add(launchButton).top().expandX();
        Table rightSidebar = new Table(skin);
        rightSidebar.add(upgradeList).top().expand().fill()
                .maxHeight(Value.percentHeight(UPGRADE_LIST_HEIGHT_PERCENT, this));
        rightSidebar.row().space(10);
        rightSidebar.add(upgradeInfo).bottom().expand().fill();
        add(rightSidebar).top().width(Value.percentWidth(SIDEBAR_WIDTH_PERCENT, this));
        launchButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                notifyLaunchListeners();
            }
        });
    }

    private void notifyLaunchListeners() {
        for(ILaunchListener listener: launchListeners) {
            listener.clickedLaunch();
        }
    }

    public void addLaunchListener(ILaunchListener listener) {
        launchListeners.add(listener);
    }

    public UpgradeInfoPanel getUpgradeInfo() {
        return upgradeInfo;
    }

    public UpgradeListPanel getUpgradeList() {
        return upgradeList;
    }

    public MoneyLabel getMoneyLabel() {
        return moneyLabel;
    }
}
