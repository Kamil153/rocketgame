package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import dev.kamilpolak.rocketgame.ecs.Engine;

public class RocketGame extends Game {
    private final AssetManager assets = new AssetManager();
    private LoadingScreen loadingScreen;
    private final Engine ecs = new Engine();

    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        assets.load("assets/textures/rocket.png", Texture.class);
        FileHandle assetDir = Gdx.files.internal("assets");
        setScreen(loadingScreen);
    }

    public Engine getEngine() {
        return ecs;
    }

    public AssetManager getAssets() {
        return assets;
    }
}
