package dev.kamilpolak.rocketgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class CountdownLabel extends Label {
    private static final String COUNTDOWN_TEXT = "T%c%02d:%02d";


    public CountdownLabel(LabelStyle style) {
        super("", style);
        setText(formatCountdownText(false, 0, 0));
    }

    private String formatCountdownText(boolean pastT0, int minutes, int seconds) {
        return String.format(COUNTDOWN_TEXT,
                pastT0 ? '+' : '-',
                minutes,
                seconds);
    }

    public void setTime(boolean pastT0, int minutes, int seconds) {
        setText(formatCountdownText(pastT0, minutes, seconds));
    }
}
