package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MoneyLabel extends Label {
    private static final String MONEY_FORMAT = "$%d";

    public MoneyLabel(CharSequence text, Skin skin) {
        super(String.format(MONEY_FORMAT, 0), skin);
    }

    public void setMoney(int money) {
        setText(String.format(MONEY_FORMAT, money));
    }
}
