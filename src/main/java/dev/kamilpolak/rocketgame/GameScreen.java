package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import dev.kamilpolak.rocketgame.components.BodyComponent;
import dev.kamilpolak.rocketgame.components.FuelComponent;
import dev.kamilpolak.rocketgame.components.TextureComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Engine;
import dev.kamilpolak.rocketgame.ecs.Entity;
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

        RenderingSystem renderingSystem = new RenderingSystem(10, batch);
        ecs.addSystem(renderingSystem);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        System.out.println(delta);
        Gdx.gl.glClearColor( 1, 0, 0, 1 );
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
        TransformComponent transform = new TransformComponent();
        rocket.addComponent(transform);
        TextureComponent textureComponent = new TextureComponent();
        TextureRegion region = new TextureRegion(parent.getAssets().get(Asset.ROCKET_OFF_TEXTURE.getPath(), Texture.class));
        textureComponent.region = region;
        rocket.addComponent(textureComponent);
        Body body = bodyFactory.createRectangularBody(
                transform.position.x, transform.position.y,
                region.getRegionWidth(), region.getRegionHeight());
        rocket.addComponent(new BodyComponent(body));
        return rocket;
    }
}
