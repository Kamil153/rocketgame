package dev.kamilpolak.rocketgame.components;

import com.badlogic.gdx.math.Vector2;
import dev.kamilpolak.rocketgame.ecs.IComponent;

public class ThrustComponent implements IComponent {
    public float thrust = 1000.0f; // kN
    public final Vector2 offset = new Vector2();
}
