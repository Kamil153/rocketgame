package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.physics.box2d.FixtureDef;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BodyData {
    private final boolean dynamic;
    private final float linearDamping;
    private final float angularDamping;
    private final boolean allowSleep;
    private final ArrayList<FixtureData> fixtures;

    public BodyData(boolean dynamic, float linearDamping, float angularDamping, boolean allowSleep, Collection<FixtureData> fixtures) {
        this.dynamic = dynamic;
        this.linearDamping = linearDamping;
        this.angularDamping = angularDamping;
        this.allowSleep = allowSleep;
        this.fixtures = new ArrayList<>(fixtures);
    }

    public boolean isDynamic() {
        return dynamic;
    }

    public float getLinearDamping() {
        return linearDamping;
    }

    public float getAngularDamping() {
        return angularDamping;
    }

    public boolean isSleepAllowed() {
        return allowSleep;
    }

    public List<FixtureData> getFixtures() {
        return Collections.unmodifiableList(fixtures);
    }
}
