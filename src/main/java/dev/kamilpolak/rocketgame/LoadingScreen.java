package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class LoadingScreen implements Screen {
    RocketGame parent;
    SpriteBatch batch;
    BitmapFont font = new BitmapFont();

    public LoadingScreen(RocketGame game) {
        parent = game;
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        batch.begin();
        font.draw(batch, "Loading...", 20, 20);
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {

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
        batch.dispose();
    }
}
