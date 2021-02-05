package dev.kamilpolak.rocketgame.components;

import com.badlogic.gdx.math.Vector3;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.IComponent;

public class BindComponent implements IComponent {
    public final Entity target;
    public final Vector3 offset;

    BindComponent(Entity target, Vector3 offset) {
        this.target = target;
        this.offset = offset.cpy();
    }

    BindComponent(Entity target) {
        this(target, new Vector3());
    }
}
