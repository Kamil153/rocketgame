package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.physics.box2d.World;

public class EntityFactory {
    private final World world;
    private final BodyFactory bodyFactory;

    public EntityFactory(World world) {
        this.world = world;
        this.bodyFactory = new BodyFactory(world);
    }
}
