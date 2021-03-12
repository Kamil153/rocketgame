package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.kamilpolak.rocketgame.components.*;
import dev.kamilpolak.rocketgame.ecs.Engine;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.systems.*;
import dev.kamilpolak.rocketgame.ui.*;


public class MainScreen implements Screen, ILaunchListener, ITerminationListener, ISummaryListener {
    private final RocketGame parent;
    private final AssetManager assets;
    private final SpriteBatch batch;
    private final World world;
    private final OrthographicCamera camera;
    private final Engine ecs = new Engine();
    private final BodyFactory bodyFactory;
    private final EntityFactory entityFactory;
    private final Stage gameStage;
    private final FlightTable flightTable;
    private final MenuTable menuTable;
    private final Entity rocket;
    private final UpgradeController upgradeController;
    private final FlightController flightController;
    private final Player player = new Player();
    private final SummaryScreen summaryScreen;

    private static final float CAMERA_HEIGHT_MENU = 150.0f;

    public MainScreen(RocketGame game) {
        parent = game;
        assets = parent.getAssets();
        batch = new SpriteBatch();
        world = new World(new Vector2(0, -10), true);
        bodyFactory = new BodyFactory(world);
        entityFactory = new EntityFactory(world, assets);
        summaryScreen = new SummaryScreen(assets);
        summaryScreen.addListener(this);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(Util.calculateViewportWidth(w, h, CAMERA_HEIGHT_MENU), CAMERA_HEIGHT_MENU);

        rocket = entityFactory.createRocket();
        System.out.println(rocket.getComponent(BodyComponent.class).body.getMass());
        ecs.addEntity(rocket);
        ecs.addEntity(entityFactory.createEarth());
        ecs.addEntity(entityFactory.createRocketPlume(rocket));
        ecs.addEntity(entityFactory.createLaunchpad());
        ecs.addEntity(entityFactory.createMountains());
        final int GROUND_COUNT = 16;
        final int TREES_COUNT = 5;
        float groundWidth = assets.get(Asset.EARTH_TEXTURE.getPath(), Texture.class).getWidth();
        ecs.addEntity(entityFactory.createEarth());
        float groundX = 0;
        for(int i = 1; i < GROUND_COUNT; i++) {
            groundX = -groundX;
            if(i % 2 == 1) {
                groundX += groundWidth;
            }
            ecs.addEntity(entityFactory.createEarth(groundX));
        }
        float treesWidth = assets.get(Asset.TREES_TEXTURE.getPath(), Texture.class).getWidth();
        ecs.addEntity(entityFactory.createTrees());
        float treesX = 0;
        for(int i = 1; i < TREES_COUNT; i++) {
            treesX = -treesX;
            if(i % 2 == 1) {
                treesX += treesWidth;
            }
            ecs.addEntity(entityFactory.createTrees(treesX));
        }

        Skin uiSkin = assets.get(Asset.UI_SKIN.getPath());
        gameStage = new Stage();
        gameStage.setDebugAll(true);

        flightTable = new FlightTable(rocket, player, uiSkin);
        menuTable = new MenuTable(uiSkin);
        menuTable.addLaunchListener(this);
        upgradeController = new UpgradeController(rocket, menuTable, assets, player);
        flightController = new FlightController(rocket, flightTable, camera);
        flightController.addListener(this);

        ecs.addSystem(new FuelSystem(18));
        ecs.addSystem(new RocketTurnSystem(17));
        ecs.addSystem(new ThrustSystem(16));
        ecs.addSystem(new PhysicsSystem(15, world));
        ecs.addSystem(new FlightTerminationSystem(14));
        ecs.addSystem(new BindSystem(13));
        ecs.addSystem(new CameraSystem(12, camera, 0));
        ecs.addSystem(new RenderingSystem(10, batch, camera));
        ecs.addSystem(new PlumeSystem(7));
        ecs.addSystem(new DebugRenderSystem(5, camera, world));
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
        rocket.removeComponent(FlightTerminationComponent.class);
        Body body = rocket.getComponent(BodyComponent.class).body;
        body.setTransform(EntityData.ROCKET_POSITION.x, EntityData.ROCKET_POSITION.y, EntityData.ROCKET_ANGLE);
        body.setLinearVelocity(Vector2.Zero);
        body.setAngularVelocity(0.0f);
        EngineStateComponent state = rocket.getComponent(EngineStateComponent.class);
        state.running = false;
        parent.setScreen(summaryScreen);
    }

    @Override
    public void summaryContinued() {
        parent.setScreen(this);
    }
}
