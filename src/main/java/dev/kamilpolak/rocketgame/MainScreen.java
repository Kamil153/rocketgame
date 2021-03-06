package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import dev.kamilpolak.rocketgame.components.BodyComponent;
import dev.kamilpolak.rocketgame.components.ControlComponent;
import dev.kamilpolak.rocketgame.components.EngineStateComponent;
import dev.kamilpolak.rocketgame.components.ThrustNoiseComponent;
import dev.kamilpolak.rocketgame.ecs.Engine;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.systems.*;
import dev.kamilpolak.rocketgame.ui.FlightTable;
import dev.kamilpolak.rocketgame.ui.MenuTable;
import dev.kamilpolak.rocketgame.upgrades.FinsUpgrade;
import dev.kamilpolak.rocketgame.upgrades.TVCUpgrade;
import dev.kamilpolak.rocketgame.upgrades.Upgrade;

import java.util.HashSet;
import java.util.Set;


public class MainScreen implements Screen {
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
    private final Countdown countdown = new Countdown();
    private final Entity rocket;
    private final Set<Upgrade> upgrades = new HashSet<>();
    private final Set<Upgrade> installedUpgrades = new HashSet<>();

    private static final float CAMERA_HEIGHT_FLIGHT = 350.0f;
    private static final float CAMERA_HEIGHT_MENU = 150.0f;
    private static final float COUNTDOWN_TIME = 5.0f;
    private static final float ZOOM_OUT_TIME = 3.0f;

    private float cameraHeight = CAMERA_HEIGHT_MENU;
    private GameState currentState = GameState.MENU;

    public MainScreen(RocketGame game) {
        parent = game;
        assets = parent.getAssets();
        batch = new SpriteBatch();
        world = new World(new Vector2(0, -10), true);
        bodyFactory = new BodyFactory(world);
        entityFactory = new EntityFactory(world, assets);
        initializeUpgrades();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(calculateViewportWidth(w, h, cameraHeight), cameraHeight);

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
        Gdx.input.setInputProcessor(gameStage);

        flightTable = new FlightTable(rocket, uiSkin);
        flightTable.setCountdown(countdown);
        menuTable = new MenuTable(rocket, uiSkin);
        showMenu();

        ecs.addSystem(new RocketTurnSystem(17));
        ecs.addSystem(new ThrustSystem(16));
        ecs.addSystem(new PhysicsSystem(15, world));
        ecs.addSystem(new BindSystem(14));
        ecs.addSystem(new CameraSystem(12, camera, 0));
        ecs.addSystem(new RenderingSystem(10, batch, camera));
        ecs.addSystem(new PlumeSystem(7));
        ecs.addSystem(new DebugRenderSystem(5, camera, world));
    }

    private static float calculateViewportWidth(float width, float height, float viewportHeight) {
        return viewportHeight * (width / height);
    }

    private void initializeUpgrades() {
        upgrades.add(new TVCUpgrade(assets.get(Asset.TVC_UPGRADE.getPath())));
        upgrades.add(new FinsUpgrade(assets.get(Asset.FINS_UPGRADE.getPath())));
    }

    private void showMenu() {
        gameStage.clear();
        gameStage.addActor(menuTable);
        menuTable.addLaunchListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startFlight();
            }
        });
    }

    private void startFlight() {
        if(currentState == GameState.MENU) {
            gameStage.clear();
            gameStage.addActor(flightTable);
            currentState = GameState.COUNTDOWN;
            countdown.setTime(COUNTDOWN_TIME);
            Gdx.input.setInputProcessor(gameStage);
            rocket.removeComponent(ThrustNoiseComponent.class);
        }
    }

    private void updateFlight() {
        if(countdown.isPastT0() && currentState == GameState.COUNTDOWN) {
            currentState = GameState.STARTING;
            rocket.getComponent(EngineStateComponent.class).running = true;
        }
        if(currentState == GameState.STARTING) {
            float progress = Math.min(1.0f, Math.abs(countdown.getTime())/ZOOM_OUT_TIME);
            float viewportHeight = Interpolation.smooth.apply(CAMERA_HEIGHT_MENU, CAMERA_HEIGHT_FLIGHT, progress);
            camera.viewportHeight = viewportHeight;
            camera.viewportWidth = calculateViewportWidth(
                    Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight(),
                    viewportHeight);
            if(viewportHeight == CAMERA_HEIGHT_FLIGHT) {
                currentState = GameState.FLIGHT;
                rocket.addComponent(new ControlComponent());
                rocket.addComponent(new ThrustNoiseComponent());
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.529f, 0.808f, 0.922f, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        ecs.update(delta);
        countdown.update(delta);
        updateFlight();
        gameStage.act();
        gameStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = calculateViewportWidth((float)width, (float)height, cameraHeight);
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
}
