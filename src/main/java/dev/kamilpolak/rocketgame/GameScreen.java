package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.kamilpolak.rocketgame.ecs.Engine;
import dev.kamilpolak.rocketgame.entities.Rocket;
import dev.kamilpolak.rocketgame.systems.RenderingSystem;

public class GameScreen implements Screen {
    RocketGame parent;
    SpriteBatch batch;
    OrthographicCamera camera;
    private final Engine ecs = new Engine();

    public GameScreen(RocketGame game) {
        parent = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        Rocket rocket = new Rocket(
                new TextureRegion(parent.getAssets().get(Asset.ROCKET_TEXTURE.getPath(), Texture.class))
        );
        ecs.addEntity(rocket);

        RenderingSystem renderingSystem = new RenderingSystem(10, batch);
        ecs.addSystem(renderingSystem);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
}
