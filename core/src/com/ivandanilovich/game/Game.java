package com.ivandanilovich.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Timer;
import java.util.TimerTask;

import box2dLight.PointLight;


public class Game implements Screen {

    private final float Width = Gdx.graphics.getWidth();
    private final float Height = Gdx.graphics.getHeight();
    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer box2DDebugRenderer;
    LevelLoader loader;
    LightHero hero;
    private Core core;
    private boolean isControl = false;
    private int cX, cY, dX, dY;
    private boolean isControlBlock = false;
    private boolean isGameOver = false;
    private int currentLevel;

    private Music endLevel;

    private boolean isMusic = true;


    public Game(final Core Core, int count) {
        this.currentLevel = count;
        core = Core;
        camera = new OrthographicCamera();
        float cameraHeight = 10;
        float WdivH = Width / Height;
        float cameraWidth = cameraHeight * WdivH;
        camera.setToOrtho(false, cameraWidth, cameraHeight);
        world = new World(new Vector2(0, 0), false);

        box2DDebugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);

        GetBox2dBody getBox2dBody = new GetBox2dBody();
        getBox2dBody.createBox(world, 0, 0, 1, 1, false);

        loader = new LevelLoader(world);
        loader.loadLevel(count);

        hero = new LightHero(world, camera, 1);
        hero.createHandler();
        hero.createCircle(loader.model.start.x, loader.model.start.y, 0.45f);
        hero.createLight(/*new Color(215,196,71,1)*/Color.WHITE, 7f);// TODO: 12.03.2018 sdfd

        new PointLight(hero.h, 200, Color.BLUE, 3, loader.model.finish.x, loader.model.finish.y);


        InputProcessor inputProcessor = new InputProcessor() {

            //region input
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == 4) {
                    core.setScreen(new MenuLevel(core));
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                cX = screenX;
                cY = screenY;
                isControl = true;
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {


                hero.move(new Vector2(0, 0));
                isControl = false;
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                dX = screenX;
                dY = screenY;

                float fX = dX - cX, fY = dY - cY;
                float speed = 10000;


                if (!isControlBlock)// TODO: 05.03.2018 ограничение скорости // перерисовать карту
                    hero.move(new Vector2((fX * 0.1f) > speed ? speed : (fX * 0.1f), (fY * -0.1f) > speed ? speed : (fY * -0.1f)));

                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
            //endregion
        };
        Gdx.input.setInputProcessor(inputProcessor);

        endLevel = Gdx.audio.newMusic(Gdx.files.internal("Sounds/EndLevel.mp3"));


        isMusic = Gdx.app.getPreferences("My Preferences").getBoolean("isMusic", true);

    }


    public void show() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 6, 2);
        camera.position.set(hero.c.getPosition().x, hero.c.getPosition().y, 0);
        camera.update();
        hero.render(camera.combined);
        if (getD(hero.c.getPosition(), loader.model.finish) < 1) {
            hero.p.setDistance(hero.p.getDistance() * 1.7f);
            hero.c.setLinearVelocity(0,0);
            if (isMusic) {
                endLevel.play();
            }

            final Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                int i = 1500;//TIME

                public void run() {
                    i--;
                    if (i < 0) {
                        isGameOver = true;

                        timer.cancel();
                    }
                }
            }, 0, 1);
            isControlBlock = true;
        }
        if (isGameOver) {
            Preferences prefs = Gdx.app.getPreferences("My Preferences");
            if (currentLevel == prefs.getInteger("CountLevel")) {
                core.setScreen(new EndGame(core));
            } else {
                prefs.putInteger("CurrentLevel", currentLevel + 1);
                if (currentLevel == prefs.getInteger("MaxOpenLevel")) {
                    prefs.putInteger("MaxOpenLevel", currentLevel + 1);
                }
                prefs.flush();
                core.setScreen(new LevelShow(core, currentLevel + 1));
            }
        }
    }

    private double getD(Vector2 v, Vector2 w) {
        return Math.sqrt((v.x - w.x) * (v.x - w.x) + (v.y - w.y) * (v.y - w.y));
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
    }

    public void dispose() {
        core.dispose();
    }
}
