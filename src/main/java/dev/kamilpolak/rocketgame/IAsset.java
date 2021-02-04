package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.assets.AssetDescriptor;

public interface IAsset <T> {
    AssetDescriptor<Class<T>> getDescriptor();
    String getPath();
}
