package dev.kamilpolak.rocketgame.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import dev.kamilpolak.rocketgame.ecs.IComponent;

public class TransformComponent implements IComponent {
    public final Vector3 position = new Vector3();
    public final Vector2 scale = new Vector2(1.0f, 1.0f);
    public float rotation = 0.0f;
    public boolean hidden = false;
}
