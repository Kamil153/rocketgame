package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.models.IPlayerListener;
import dev.kamilpolak.rocketgame.models.Player;
import dev.kamilpolak.rocketgame.ui.*;
import dev.kamilpolak.rocketgame.upgrades.FinsUpgrade;
import dev.kamilpolak.rocketgame.upgrades.TVCUpgrade;
import dev.kamilpolak.rocketgame.upgrades.Upgrade;

import java.util.HashSet;
import java.util.Set;

public class UpgradeController implements IUpgradeSelectionListener, IBuyListener, IPlayerListener {
    private final Entity rocket;
    private final UpgradeInfoPanel upgradeInfoView;
    private final UpgradeListPanel upgradeListView;
    private final Set<Upgrade> upgrades = new HashSet<>();
    private final Set<Upgrade> installedUpgrades = new HashSet<>();
    private final AssetManager assets;
    private final Player player;

    private static final Color PRICE_COLOR = Color.WHITE;
    private static final Color NOT_ENOUGH_MONEY_COLOR = Color.RED;

    public UpgradeController(Entity rocket, MenuTable menuView, AssetManager assets, Player player) {
        this.rocket = rocket;
        this.upgradeInfoView = menuView.getUpgradeInfo();
        this.upgradeListView = menuView.getUpgradeList();
        this.assets = assets;
        this.player = player;
        player.addListener(this);
        upgradeInfoView.addBuyButtonListener(this);
        upgradeListView.addUpgradeSelectionListener(this);
        initializeUpgrades();
    }

    private void initializeUpgrades() {
        upgrades.add(new TVCUpgrade(assets.get(Asset.TVC_UPGRADE.getPath())));
        upgrades.add(new FinsUpgrade(assets.get(Asset.FINS_UPGRADE.getPath())));
        for(Upgrade upgrade: upgrades) {
            UpgradeListItem itemView = upgradeListView.addUpgrade(upgrade);
            updatePriceColor(itemView, player.getMoney(), upgrade);
        }
    }

    @Override
    public void selected(Upgrade upgrade) {
        upgradeInfoView.setUpgrade(upgrade);
        upgradeInfoView.setBuyButtonVisible(!installedUpgrades.contains(upgrade));
    }

    @Override
    public void clickedBuy(Upgrade upgrade) {
        if(!installedUpgrades.contains(upgrade) && player.getMoney() >= upgrade.getPrice()) {
            player.subtractMoney(upgrade.getPrice());
            upgrade.install(rocket);
            installedUpgrades.add(upgrade);
            upgradeListView.getListItem(upgrade).setPriceVisible(false);
            upgradeInfoView.setBuyButtonVisible(false);
        }
    }

    @Override
    public void moneyChanged(int oldMoney, int newMoney) {
        for(Upgrade upgrade: upgrades) {
            updatePriceColor(upgradeListView.getListItem(upgrade), newMoney, upgrade);
        }
    }

    private void updatePriceColor(UpgradeListItem itemView, int money, Upgrade upgrade) {
        itemView.setPriceColor(money < upgrade.getPrice() ? NOT_ENOUGH_MONEY_COLOR : PRICE_COLOR);
    }
}
