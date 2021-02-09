package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;

public class RocketGame extends Game {
    private static final String FONT_PATH = "assets/retro_gaming.ttf";
    private static final int FONT_SIZE = 20;

    private final AssetManager assets = new AssetManager();
    private MainScreen mainScreen;

    @Override
    public void create() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assets.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assets.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreeTypeFontLoaderParameter fontParams = new FreeTypeFontLoaderParameter();
        fontParams.fontFileName = FONT_PATH;
        fontParams.fontParameters.size = FONT_SIZE;
        assets.load("font.ttf", BitmapFont.class, fontParams);
        assets.finishLoadingAsset("font.ttf");

        for(Asset asset: Asset.values()) {
            assets.load(asset.getPath(), asset.getAssetType());
        }
        setScreen(new LoadingScreen(this));
    }

    public AssetManager getAssets() {
        return assets;
    }

    public BitmapFont getFont() {
        return assets.get("font.ttf");
    }

    public void startGame() {
        mainScreen = new MainScreen(this);
        setScreen(mainScreen);
    }
}
