package dev.kamilpolak.rocketgame;

import java.util.Arrays;

public class FixtureData {
    public final float density;
    public final float friction;
    public final float restitution;
    public final float[] vertices;

    public FixtureData(float density, float friction, float restitution, float[] vertices) {
        this.density = density;
        this.friction = friction;
        this.restitution = restitution;
        this.vertices = Arrays.copyOf(vertices, vertices.length);
    }
}
