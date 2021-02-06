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

    private static final float CAMERA_WIDTH = 800;
    private static final float CAMERA_HEIGHT = 450;

    private final PriorityQueue<Entity> renderingQueue = new PriorityQueue<>(new ZComparator());

    private final OrthographicCamera cam;
    private final SpriteBatch batch;

    public RenderingSystem(int priority, SpriteBatch batch) {
        super(priority, query);
        this.batch = batch;
        cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {
        renderingQueue.add(entity);
    }

    @Override
    protected void update(float deltaTime) {
        super.update(deltaTime);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        while(!renderingQueue.isEmpty()) {
            Entity entity = renderingQueue.poll();
            TransformComponent transform = entity.getComponent(TransformComponent.class);
            if(!transform.hidden) {
                TextureRegion tex = entity.getComponent(TextureComponent.class).region;
                float originX = tex.getRegionWidth() / 2.0f;
                float originY = tex.getRegionHeight() / 2.0f;
                batch.draw(tex,
                        transform.position.x - originX, transform.position.y - originY,
                        originX, originY,
                        tex.getRegionWidth(), tex.getRegionHeight(),
                        transform.scale.x, transform.scale.y,
                        transform.rotation * 180.0f / (float) Math.PI  // conversion from radians to degrees
                );
            }
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

    public OrthographicCamera getCamera() {
        return cam;
    }
}
