package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import dev.kamilpolak.rocketgame.components.*;
import dev.kamilpolak.rocketgame.ecs.Engine;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.systems.*;

public class MainScreen implements Screen {
    private final RocketGame parent;
    private final SpriteBatch batch;
    private final World world;
    private final OrthographicCamera camera;
    private final Engine ecs = new Engine();
    private final BodyFactory bodyFactory;

    private static final float CAMERA_HEIGHT = 350.0f;

    public MainScreen(RocketGame game) {
        parent = game;
        batch = new SpriteBatch();
        world = new World(new Vector2(0, -10), true);
        bodyFactory = new BodyFactory(world);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(CAMERA_HEIGHT * (w / h), CAMERA_HEIGHT);

        // TODO: set groundHeight to texture height
        int groundTextureHeight = 30;
        float cameraLowerBound = camera.viewportHeight/2.0f - RenderingSystem.pixelsToMeters(groundTextureHeight);

        Entity rocket = createRocket();
        Entity ground = createGroundEntity();
        Entity plume = createRocketPlume(rocket);

        ThrustSystem thrustSystem = new ThrustSystem(16);
        PhysicsSystem physicsSystem = new PhysicsSystem(15, world);
        BindSystem bindSystem = new BindSystem(14);
        CameraSystem cameraSystem = new CameraSystem(12, camera, cameraLowerBound);
        RenderingSystem renderingSystem = new RenderingSystem(10, batch, camera);
        PlumeSystem plumeSystem = new PlumeSystem(7);
        DebugRenderSystem debugSystem = new DebugRenderSystem(5, camera, world);

        ecs.addEntity(rocket);
        ecs.addEntity(ground);
        ecs.addEntity(plume);

        ecs.addSystem(renderingSystem);
        ecs.addSystem(physicsSystem);
        ecs.addSystem(debugSystem);
        ecs.addSystem(bindSystem);
        ecs.addSystem(plumeSystem);
        ecs.addSystem(thrustSystem);
        ecs.addSystem(cameraSystem);
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
        camera.viewportWidth = CAMERA_HEIGHT * ((float)width / (float)height);
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
        TextureComponent textureComponent = new TextureComponent();
        TextureRegion region = new TextureRegion(parent.getAssets().get(Asset.ROCKET_OFF_TEXTURE.getPath(), Texture.class));
        TransformComponent transform = new TransformComponent();
        float width = RenderingSystem.pixelsToMeters(region.getRegionWidth());
        float height = RenderingSystem.pixelsToMeters(region.getRegionHeight());
        transform.position.x = 0;
        transform.position.y = height/2.0f;
        transform.rotation = 0;
        textureComponent.region = region;
        Body body = bodyFactory.createDynamicRectangle(
                transform.position.x, transform.position.y,
                width, height,
                transform.rotation);
        System.out.println(body.getMass());
        rocket.addComponent(new FuelComponent());
        ThrustComponent thrustComponent = new ThrustComponent();
        thrustComponent.offset.y = -height/2.0f;
        rocket.addComponent(thrustComponent);
        EngineStateComponent engine = new EngineStateComponent();
        engine.running = true;
        rocket.addComponent(engine);
        rocket.addComponent(new BodyComponent(body));
        rocket.addComponent(transform);
        rocket.addComponent(textureComponent);
        rocket.addComponent(new RocketComponent());
        return rocket;
    }

    private Entity createGroundEntity() {
        // TODO: use ground texture instead of pixmap
        int texWidth = 50;
        int texHeight = 30;
        float width = RenderingSystem.pixelsToMeters(texWidth);
        float height = RenderingSystem.pixelsToMeters(texHeight);
        float x = 0.0f;
        float y = -height/2.0f;
        Pixmap pixmap = new Pixmap(texWidth, texHeight, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        TextureComponent texComponent = new TextureComponent();
        texComponent.region = new TextureRegion(new Texture(pixmap));
        Body body = bodyFactory.createStaticRectangle(x, y, width, height, 0);
        TransformComponent transform = new TransformComponent();
        transform.position.x = x;
        transform.position.y = y;

        Entity ground = new Entity();
        ground.addComponent(new BodyComponent(body));
        ground.addComponent(texComponent);
        ground.addComponent(transform);
        return ground;
    }

    private Entity createRocketPlume(Entity rocket) {
        TextureRegion texture = new TextureRegion(parent.getAssets().get(Asset.PLUME_TEXTURE.getPath(), Texture.class));
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.region = texture;
        Texture rocketTexture = parent.getAssets().get(Asset.ROCKET_TEXTURE.getPath());
        TransformComponent transform = new TransformComponent();
        float height = RenderingSystem.pixelsToMeters(texture.getRegionHeight());
        float rocketHeight = RenderingSystem.pixelsToMeters(rocketTexture.getHeight());
        float offsetX = 0;
        float offsetY = -height/2.0f - rocketHeight/2.0f;
        BindComponent binding = new BindComponent(rocket, new Vector2(offsetX, offsetY));

        Entity plume = new Entity();
        plume.addComponent(new PlumeComponent());
        plume.addComponent(textureComponent);
        plume.addComponent(transform);
        plume.addComponent(binding);
        return plume;
    }
}
