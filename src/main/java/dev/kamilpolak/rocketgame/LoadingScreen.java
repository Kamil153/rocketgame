package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class LoadingScreen implements Screen {
    private final RocketGame parent;
    private final SpriteBatch batch;
    private final BitmapFont font = new BitmapFont();

    public LoadingScreen(RocketGame game) {
        parent = game;
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        if(parent.getAssets().update()) {
            parent.startGame();
        }
        else {
            batch.begin();
            font.draw(batch, "Loading...", 20, 20);
            batch.end();
        }
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
        batch.dispose();
    }

    @Override
    public void dispose() {

    }
}
