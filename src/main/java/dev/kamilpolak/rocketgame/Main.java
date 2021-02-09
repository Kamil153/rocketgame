package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Game";
        config.width = 1200;
        config.height = 600;
        //config.fullscreen = true;
        new LwjglApplication(new RocketGame(), config);
    }
}
