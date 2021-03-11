package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SummaryTable extends Table {
    private final TextButton continueButton;

    public SummaryTable(Skin skin) {
        continueButton = new TextButton("Continue", skin);
        add(continueButton).center();
    }

    public TextButton getContinueButton() {
        return continueButton;
    }
}
