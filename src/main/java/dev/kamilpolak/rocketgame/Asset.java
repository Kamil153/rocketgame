package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public enum Asset {
    ROCKET_TEXTURE("textures/rocket.png", Texture.class),
    ROCKET_OFF_TEXTURE("textures/rocket_off.png", Texture.class),
    PLUME_TEXTURE("textures/plume.png", Texture.class),
    EARTH_TEXTURE("textures/earth.png", Texture.class),
    LAUNCHPAD_TEXTURE("textures/launchpad.png", Texture.class),
    MOUNTAINS_TEXTURE("textures/mountains.png", Texture.class),
    TREES_TEXTURE("textures/trees.png", Texture.class),
    UI_SKIN("ui/uiskin.json", Skin.class),
    ROCKET_BODY("body/rocket.xml", BodyData.class),
    LAUNCHPAD_BODY("body/launchpad.xml", BodyData.class),
    TVC_UPGRADE("upgrades/tvc.xml", UpgradeData.class),
    FINS_UPGRADE("upgrades/fins.xml", UpgradeData.class);

    private static final String prefix = "assets/";

    private final String path;
    private final Class<?> assetType;

    Asset(String path, Class<?> assetType) {
        this.path = prefix + path;
        this.assetType = assetType;
    }

    public String getPath() {
        return path;
    }

    public Class<?> getAssetType() {
        return assetType;
    }
}
