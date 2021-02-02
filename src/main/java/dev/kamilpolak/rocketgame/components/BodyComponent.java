package dev.kamilpolak.rocketgame.components;

import com.badlogic.gdx.physics.box2d.Body;

public class BodyComponent {
    public final Body body;

    public BodyComponent(Body body) {
        this.body = body;
    }
}
