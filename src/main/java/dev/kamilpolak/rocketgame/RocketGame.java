package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class RocketGame extends Game {
    private final AssetManager assets = new AssetManager();
    private GameScreen gameScreen;

    @Override
    public void create() {
        for(Asset asset: Asset.values()) {
            assets.load(asset.getPath(), asset.getAssetType());
        }
        setScreen(new LoadingScreen(this));
    }

    public AssetManager getAssets() {
        return assets;
    }

    public void startGame() {
        gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }
}
