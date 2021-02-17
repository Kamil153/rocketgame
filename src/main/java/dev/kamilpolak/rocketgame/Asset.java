package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public enum Asset {
    ROCKET_TEXTURE("assets/textures/rocket.png", Texture.class),
    ROCKET_OFF_TEXTURE("assets/textures/rocket_off.png", Texture.class),
    PLUME_TEXTURE("assets/textures/plume.png", Texture.class),
    EARTH_TEXTURE("assets/textures/earth.png", Texture.class),
    LAUNCHPAD_TEXTURE("assets/textures/launchpad.png", Texture.class),
    MOUNTAINS_TEXTURE("assets/textures/mountains.png", Texture.class),
    TREES_TEXTURE("assets/textures/trees.png", Texture.class),
    UI_SKIN("assets/ui/uiskin.json", Skin.class),
    ROCKET_BODY("assets/body/rocket.xml", BodyData.class),
    LAUNCHPAD_BODY("assets/body/rocket.xml", BodyData.class);

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
