package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class CountdownLabel extends Label {
    private static final String COUNTDOWN_TEXT = "T%c%d:%d";
    private Countdown countdown;


    public CountdownLabel(Countdown countdown, LabelStyle style) {
        super("", style);
        this.countdown = countdown;
        setText(getFormattedText());
    }

    private String getFormattedText() {
        return String.format(COUNTDOWN_TEXT,
                countdown.isPastT0() ? '+' : '-',
                countdown.getMinutes(),
                countdown.getSeconds());
    }

    public void setCountdown(Countdown countdown) {
        this.countdown = countdown;
    }

    @Override
    public void act(float delta) {
        setText(getFormattedText());
        super.act(delta);
    }
}
