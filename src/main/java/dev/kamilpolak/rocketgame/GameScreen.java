package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import dev.kamilpolak.rocketgame.components.BodyComponent;
import dev.kamilpolak.rocketgame.components.FuelComponent;
import dev.kamilpolak.rocketgame.components.TextureComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Engine;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.systems.DebugRenderSystem;
import dev.kamilpolak.rocketgame.systems.PhysicsSystem;
import dev.kamilpolak.rocketgame.systems.RenderingSystem;

public class GameScreen implements Screen {
    private RocketGame parent;
    private SpriteBatch batch;
    private World world;
    private final Engine ecs = new Engine();
    private final BodyFactory bodyFactory;

    public GameScreen(RocketGame game) {
        parent = game;
        batch = new SpriteBatch();
        world = new World(new Vector2(0, -10), true);
        bodyFactory = new BodyFactory(world);

        Entity rocket = createRocket();
        ecs.addEntity(rocket);

        Entity ground = createGroundEntity();
        ecs.addEntity(ground);

        RenderingSystem renderingSystem = new RenderingSystem(10, batch);
        PhysicsSystem physicsSystem = new PhysicsSystem(15, world);
        DebugRenderSystem debugSystem = new DebugRenderSystem(5, renderingSystem.getCamera(), world);
        ecs.addSystem(renderingSystem);
        ecs.addSystem(physicsSystem);
        ecs.addSystem(debugSystem);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        ecs.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private Entity createRocket() {
        Entity rocket = new Entity();
        rocket.addComponent(new FuelComponent());
        TextureComponent textureComponent = new TextureComponent();
        TextureRegion region = new TextureRegion(parent.getAssets().get(Asset.ROCKET_OFF_TEXTURE.getPath(), Texture.class));
        TransformComponent transform = new TransformComponent();
        transform.position.x = 0;
        transform.position.y = region.getRegionHeight()/2.0f;
        textureComponent.region = region;
        Body body = bodyFactory.createDynamicRectangle(
                transform.position.x, transform.position.y,
                region.getRegionWidth(), region.getRegionHeight());
        rocket.addComponent(new BodyComponent(body));
        rocket.addComponent(transform);
        rocket.addComponent(textureComponent);
        return rocket;
    }

    private Entity createGroundEntity() {
        // TODO: use ground texture instead of pixmap
        int width = 50;
        int height = 30;
        float x = 0.0f;
        float y = -height/2.0f;
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        TextureComponent texComponent = new TextureComponent();
        texComponent.region = new TextureRegion(new Texture(pixmap));
        Body body = bodyFactory.createStaticRectangle(x, y, width, height);
        TransformComponent transform = new TransformComponent();
        transform.position.x = x;
        transform.position.y = y;

        Entity ground = new Entity();
        ground.addComponent(new BodyComponent(body));
        ground.addComponent(texComponent);
        ground.addComponent(transform);
        return ground;
    }
}
