package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.graphics.Texture;

public enum Asset {
    ROCKET_TEXTURE("assets/textures/rocket.png", Texture.class),
    ROCKET_OFF_TEXTURE("assets/textures/rocket_off.png", Texture.class),
    PLUME_TEXTURE("assets/textures/plume.png", Texture.class);

    private final String path;
    private final Class<?> assetType;

    Asset(String path, Class<?> assetType) {
        this.path = path;
        this.assetType = assetType;
    }

    public String getPath() {
        return path;
    }

    public Class<?> getAssetType() {
        return assetType;
    }
}
