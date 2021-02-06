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

    public static final float MPP = 1; // meters per game pixel
    public static final float PIXEL_TO_METER_RATIO = 1/MPP;

    public static float pixelsToMeters(int pixels) {
        return pixels*MPP;
    }

    private final PriorityQueue<Entity> renderingQueue = new PriorityQueue<>(new ZComparator());

    private final OrthographicCamera cam;
    private final SpriteBatch batch;

    public RenderingSystem(int priority, SpriteBatch batch, OrthographicCamera camera) {
        super(priority, query);
        this.batch = batch;
        this.cam = camera;
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
                float width = pixelsToMeters(tex.getRegionWidth());
                float height = pixelsToMeters(tex.getRegionHeight());
                float originX = width / 2.0f;
                float originY = height / 2.0f;
                batch.draw(tex,
                        transform.position.x - originX, transform.position.y - originY,
                        originX, originY,
                        width, height,
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
