package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.assets.AssetManager;

public class CustomAssetManager extends AssetManager {
    public synchronized <T> T get(IAsset<T> asset) {
        return super.get(asset.getDescriptor());
    }
}
