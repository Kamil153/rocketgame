package dev.kamilpolak.rocketgame.systems;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.kamilpolak.rocketgame.components.TextureComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.Query;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RenderingSystem extends IteratingSystem {
    private static final Query query = (new Query())
            .include(TextureComponent.class)
            .include(TransformComponent.class);

    private final PriorityQueue<Entity> renderingQueue = new PriorityQueue<>(new ZComparator());

    private final OrthographicCamera cam = new OrthographicCamera();
    private final SpriteBatch batch;

    public RenderingSystem(int priority, SpriteBatch batch) {
        super(priority, query);
        this.batch = batch;
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {
        renderingQueue.add(entity);
    }

    @Override
    protected void update(float deltaTime) {
        super.update(deltaTime);
        batch.begin();
        while(!renderingQueue.isEmpty()) {
            Entity entity = renderingQueue.poll();
            TextureRegion tex = entity.getComponent(TextureComponent.class).region;
        }
        batch.end();
    }

    static class ZComparator implements Comparator<Entity> {

        @Override
        public int compare(Entity entity1, Entity entity2) {
            float z1 = entity1.getComponent(TransformComponent.class).position.z;
            float z2 = entity2.getComponent(TransformComponent.class).position.z;
            return Float.compare(z1, z2);
        }
    }

}
