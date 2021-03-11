package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import dev.kamilpolak.rocketgame.ui.ISummaryListener;
import dev.kamilpolak.rocketgame.ui.SummaryTable;

import java.util.ArrayList;
import java.util.Collection;

public class SummaryScreen implements Screen {
    private final Stage summaryStage = new Stage();
    private final AssetManager assets;
    private final Collection<ISummaryListener> listeners = new ArrayList<>();

    public SummaryScreen(AssetManager assets) {
        this.assets = assets;
        SummaryTable summaryTable = new SummaryTable(assets.get(Asset.UI_SKIN.getPath()));
        summaryStage.addActor(summaryTable);
        summaryTable.getContinueButton().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                notifyContinued();
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(summaryStage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        summaryStage.act(v);
        summaryStage.draw();
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

    }

    private void notifyContinued() {
        for(ISummaryListener listener: listeners) {
            listener.summaryContinued();
        }
    }

    public void addListener(ISummaryListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ISummaryListener listener) {
        listeners.remove(listener);
    }
}
