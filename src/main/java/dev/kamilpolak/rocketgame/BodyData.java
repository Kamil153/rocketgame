package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.physics.box2d.FixtureDef;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BodyData {
    public final boolean dynamic;
    public final float linearDamping;
    public final float angularDamping;
    public final boolean allowSleep;
    private final ArrayList<FixtureData> fixtures;

    public BodyData(boolean dynamic, float linearDamping, float angularDamping, boolean allowSleep, Collection<FixtureData> fixtures) {
        this.dynamic = dynamic;
        this.linearDamping = linearDamping;
        this.angularDamping = angularDamping;
        this.allowSleep = allowSleep;
        this.fixtures = new ArrayList<>(fixtures);
    }

    public List<FixtureData> getFixtures() {
        return Collections.unmodifiableList(fixtures);
    }
}
