package dev.kamilpolak.rocketgame;

public enum Asset {
    ROCKET_TEXTURE("assets/textures/rocket.png"),
    ROCKET_OFF_TEXTURE("assets/textures/rocket_off.png"),
    PLUME_TEXTURE("assets/textures/plume.png");

    private final String path;

    Asset(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
