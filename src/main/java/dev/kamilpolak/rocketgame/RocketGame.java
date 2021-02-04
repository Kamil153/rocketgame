package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class RocketGame extends Game {
    private AssetManager assets = new AssetManager();
    private LoadingScreen loadingScreen;

    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }
}
