package dev.kamilpolak.rocketgame.components;

import com.badlogic.gdx.physics.box2d.Body;
import dev.kamilpolak.rocketgame.ecs.IComponent;

public class BodyComponent implements IComponent {
    public final Body body;

    public BodyComponent(Body body) {
        this.body = body;
    }
}
