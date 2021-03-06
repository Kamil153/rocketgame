package dev.kamilpolak.rocketgame.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import dev.kamilpolak.rocketgame.components.EngineAngleComponent;
import dev.kamilpolak.rocketgame.components.ControlComponent;
import dev.kamilpolak.rocketgame.components.RocketComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.Query;

public class RocketTurnSystem extends IteratingSystem {
    private static final float TURN_ANGLE = 0.3f;

    private static final Query query = (new Query())
            .include(RocketComponent.class)
            .include(EngineAngleComponent.class);

    public RocketTurnSystem(int priority) {
        super(priority, query);
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {
        EngineAngleComponent angleComponent = entity.getComponent(EngineAngleComponent.class);
        if(entity.hasComponent(ControlComponent.class)) {
            boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
            boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
            if(leftPressed) {
                angleComponent.angle = -TURN_ANGLE;
            }
            else if(rightPressed) {
                angleComponent.angle = TURN_ANGLE;
            }
            else {
                angleComponent.angle = 0;
            }
        }
        else {
            angleComponent.angle = 0;
        }
    }
}
