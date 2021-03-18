package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import dev.kamilpolak.rocketgame.components.*;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.models.Player;
import dev.kamilpolak.rocketgame.ui.*;


public class MainScreen implements Screen, ILaunchListener, ITerminationListener, ISummaryListener {
    private final RocketGame parent;
    private final AssetManager assets;
    private final OrthographicCamera camera;
    private final GameWorld ecs;
    private final Stage gameStage;
    private final FlightTable flightTable;
    private final MenuTable menuTable;
    private final Entity rocketEntity;
    private final UpgradeController upgradeController;
    private final FlightController flightController;
    private final RocketController rocketController;
    private final Player player = new Player();
    private final SummaryScreen summaryScreen;

    private static final float CAMERA_HEIGHT_MENU = 150.0f;

    public MainScreen(RocketGame game) {
        parent = game;
        assets = parent.getAssets();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(Util.calculateViewportWidth(w, h, CAMERA_HEIGHT_MENU), CAMERA_HEIGHT_MENU);
        ecs = new GameWorld(assets, camera);
        summaryScreen = new SummaryScreen(assets);
        summaryScreen.addListener(this);
        rocketEntity = ecs.getRocketEntity();


        Skin uiSkin = assets.get(Asset.UI_SKIN.getPath());
        gameStage = new Stage();
        //gameStage.setDebugAll(true);

        rocketController = new RocketController(rocketEntity);
        flightTable = new FlightTable(rocketController.getRocket(), uiSkin);
        menuTable = new MenuTable(uiSkin, player);
        menuTable.addLaunchListener(this);
        upgradeController = new UpgradeController(rocketEntity, menuTable, assets, player);
        flightController = new FlightController(rocketEntity, flightTable, camera);
        flightController.addListener(this);
    }

    private void showMenu() {
        gameStage.clear();
        gameStage.addActor(menuTable);
    }

    @Override
    public void show() {
        showMenu();
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.529f, 0.808f, 0.922f, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        ecs.update(delta);
        flightController.update(delta);
        rocketController.update();
        gameStage.act();
        gameStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        float cameraHeight = camera.viewportHeight;
        camera.viewportWidth = Util.calculateViewportWidth((float)width, (float)height, cameraHeight);
        gameStage.getViewport().update(width, height);
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
        gameStage.dispose();
    }

    @Override
    public void clickedLaunch() {
        gameStage.clear();
        gameStage.addActor(flightTable);
        flightController.initiate();
    }

    @Override
    public void flightTerminated() {
        rocketEntity.removeComponent(FlightTerminationComponent.class);
        Body body = rocketEntity.getComponent(BodyComponent.class).body;
        player.addMoney((int)body.getPosition().y/2);
        body.setTransform(EntityData.ROCKET_POSITION.x, EntityData.ROCKET_POSITION.y, EntityData.ROCKET_ANGLE);
        body.setLinearVelocity(Vector2.Zero);
        body.setAngularVelocity(0.0f);
        EngineStateComponent state = rocketEntity.getComponent(EngineStateComponent.class);
        state.running = false;
        float cameraHeight = camera.viewportHeight;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera.viewportHeight = CAMERA_HEIGHT_MENU;
        camera.viewportWidth = Util.calculateViewportWidth(w, h, CAMERA_HEIGHT_MENU);
        parent.setScreen(summaryScreen);
    }

    @Override
    public void summaryContinued() {
        parent.setScreen(this);
    }
}
