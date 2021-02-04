package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public enum TextureAsset implements IAsset<Texture> {
    ROCKET("rocket.png"),
    ROCKET_OFF("rocket_off.png"),
    PLUME("plume.png");

    private final FileHandle file;
    private final AssetDescriptor<Texture> descriptor;

    TextureAsset(String filename) {
        file = Gdx.files.internal("textures/" + filename);
        descriptor = new AssetDescriptor<>(file, Texture.class);
    }

    @Override
    public AssetDescriptor<Texture> getDescriptor() {
        return descriptor;
    }

    @Override
    public FileHandle getFile() {
        return file;
    }
}
