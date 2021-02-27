package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import dev.kamilpolak.rocketgame.Upgrade;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class MenuStage extends Stage {
    private final Entity rocket;
    private final Table table;
    private final TextButton launchButton;
    private final UpgradeList upgradeList;

    private static final float SIDEBAR_WIDTH_PERCENT = 0.25f;

    public MenuStage(Entity rocket, Skin skin) {
        super();
        this.rocket = rocket;
        this.table = new Table();
        this.upgradeList = new UpgradeList(skin);
        upgradeList.addUpgrade(new Upgrade("Fins", 100));
        upgradeList.addUpgrade(new Upgrade("TVC", 50000000));
        addActor(table);
        table.setFillParent(true);
        table.pad(10);
        launchButton = new TextButton("Launch", skin);
        launchButton.pad(5, 20, 5, 20);
        table.add(new Widget()).expandY().width(Value.percentWidth(SIDEBAR_WIDTH_PERCENT, table));
        table.add(launchButton).top().expand();
        table.add(upgradeList).expandY().fill().width(Value.percentWidth(SIDEBAR_WIDTH_PERCENT, table));
    }

    public void addLaunchListener(EventListener listener) {
        launchButton.addListener(listener);
    }
}
