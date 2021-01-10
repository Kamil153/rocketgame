package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Game";
        config.width = 800;
        config.height = 480;
        new LwjglApplication(new RocketGame(), config);
    }
}
