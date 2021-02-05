package dev.kamilpolak.rocketgame;

public enum AssetPath {
    ROCKET_TEXTURE("assets/textures/rocket.png"),
    ROCKET_OFF_TEXTURE("assets/textures/rocket_off.png"),
    PLUME_TEXTURE("assets/textures/plume.png");

    private final String path;

    AssetPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
