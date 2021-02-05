package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
    RocketGame parent;
    SpriteBatch batch;
    Texture rocketTexture;
    Sprite rocketSprite;
    OrthographicCamera camera;

    public GameScreen(RocketGame game) {
        parent = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
    }

    @Override
    public void show() {
        rocketTexture = parent.getAssets().get(Asset.ROCKET_TEXTURE.getPath());
        rocketSprite = new Sprite(rocketTexture);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        rocketSprite.draw(batch);
        batch.end();
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
