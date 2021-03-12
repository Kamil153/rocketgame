package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import dev.kamilpolak.rocketgame.IPlayerListener;
import dev.kamilpolak.rocketgame.Player;

public class MoneyLabel extends Label implements IPlayerListener {
    private static final String MONEY_FORMAT = "$%d";

    public MoneyLabel(Skin skin, Player player) {
        super(String.format(MONEY_FORMAT, 0), skin);
        player.addListener(this);
    }

    @Override
    public void moneyChanged(int oldMoney, int newMoney) {
        setText(String.format(MONEY_FORMAT, newMoney));
    }
}
