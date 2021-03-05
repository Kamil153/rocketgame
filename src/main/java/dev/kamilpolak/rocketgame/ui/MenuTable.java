package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import dev.kamilpolak.rocketgame.Upgrade;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class MenuTable extends Table implements UpgradeSelectionListener {
    private final Entity rocket;
    private final TextButton launchButton;
    private final UpgradeListPanel upgradeList;
    private final UpgradeInfoPanel upgradeInfo;

    private static final float SIDEBAR_WIDTH_PERCENT = 0.25f;
    private static final float UPGRADE_LIST_HEIGHT_PERCENT = 0.65f;

    public MenuTable(Entity rocket, Skin skin) {
        super();
        this.rocket = rocket;
        this.upgradeList = new UpgradeListPanel(skin);
        this.upgradeInfo = new UpgradeInfoPanel(skin);
        upgradeList.addUpgradeSelectionListener(this);
        setFillParent(true);
        pad(10);
        launchButton = new TextButton("Launch", skin);
        launchButton.pad(5, 20, 5, 20);
        add(new Widget()).expandY().width(Value.percentWidth(SIDEBAR_WIDTH_PERCENT, this));
        add(launchButton).top().expandX();
        Table rightSidebar = new Table(skin);
        rightSidebar.add(upgradeList).top().expand().fill()
                .height(Value.percentHeight(UPGRADE_LIST_HEIGHT_PERCENT, this));
        rightSidebar.row().space(10);
        rightSidebar.add(upgradeInfo).expand().fill();
        add(rightSidebar).top().width(Value.percentWidth(SIDEBAR_WIDTH_PERCENT, this));

    }

    public void addLaunchListener(EventListener listener) {
        launchButton.addListener(listener);
    }

    @Override
    public void selected(Upgrade upgrade) {
        upgradeInfo.setUpgrade(upgrade);
    }

    public UpgradeInfoPanel getUpgradeInfo() {
        return upgradeInfo;
    }

    public UpgradeListPanel getUpgradeList() {
        return upgradeList;
    }
}
