package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.files.FileHandle;

public interface IAsset <T> {
    AssetDescriptor<T> getDescriptor();
    FileHandle getFile();
}
