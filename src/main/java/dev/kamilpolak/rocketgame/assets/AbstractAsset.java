package dev.kamilpolak.rocketgame.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.files.FileHandle;

public abstract class AbstractAsset <T> implements IAsset<T> {
    private final FileHandle file;
    private final AssetDescriptor<T> descriptor;

    AbstractAsset(String path, AssetDescriptor<T> descriptor) {
        file = Gdx.files.internal("assets/" + path);
        this.descriptor = descriptor;
    }

    @Override
    public AssetDescriptor<T> getDescriptor() {
        return descriptor;
    }

    @Override
    public FileHandle getFile() {
        return file;
    }
}
