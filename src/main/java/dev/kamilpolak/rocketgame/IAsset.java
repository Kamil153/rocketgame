package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.assets.AssetDescriptor;

public interface IAsset <T> {
    AssetDescriptor<T> getDescriptor();
    String getPath();
}
