package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.physics.box2d.*;

public class BodyFactory {
    private final World world;

    public BodyFactory(World world) {
        this.world = world;
    }

    public Body createRectangularBody(float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width/2.0f, height/2.0f);

        /*
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.0f;
        body.createFixture(fixtureDef);
         */
        poly.dispose();

        return body;
    }
}
