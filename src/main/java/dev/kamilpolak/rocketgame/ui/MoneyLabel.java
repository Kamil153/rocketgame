package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import dev.kamilpolak.rocketgame.IPlayerListener;

public class MoneyLabel extends Label implements IPlayerListener {
    private static final String MONEY_FORMAT = "$%d";

    public MoneyLabel(Skin skin) {
        super(String.format(MONEY_FORMAT, 0), skin);
    }

    @Override
    public void moneyChanged(int oldMoney, int newMoney) {
        setText(String.format(MONEY_FORMAT, newMoney));
    }
}
