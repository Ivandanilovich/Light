package com.ivandanilovich.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

class Welcome implements Screen {
    private final Stage stage;
    private Core core;

    Welcome(final Core Core) {
        this.core = Core;

        stage = new Stage(new ScreenViewport());


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = core.font;
        textButtonStyle.up = core.skin.getDrawable("empty");
        textButtonStyle.down =core.skin.getDrawable("empty");
        textButtonStyle.checked = core.skin.getDrawable("empty");
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = core.font;
        Table table = new Table();
        Label.LabelStyle label1Style = new Label.LabelStyle();
        label1Style.font = core.font;
        label1Style.fontColor = Color.YELLOW;

        TextButton startNewGame = new TextButton("Start New Game", textButtonStyle);
        startNewGame.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Preferences prefs = Gdx.app.getPreferences("My Preferences");
                prefs.putInteger("MaxOpenLevel", 1);
                prefs.putInteger("CurrentLevel", 1);
                prefs.flush();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                core.setScreen(new Game(core, Gdx.app.getPreferences("My Preferences").getInteger("CurrentLevel")));
            }
        });

        TextButton continueB = new TextButton("Continue", textButtonStyle);
        continueB.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                core.setScreen(new LevelShow(core, Gdx.app.getPreferences("My Preferences").getInteger("MaxOpenLevel")));
            }
        });

        TextButton menu = new TextButton("Menu", textButtonStyle);
        menu.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                core.setScreen(new MenuLevel(core));
            }
        });

        TextButton options = new TextButton("Options", textButtonStyle);
        options.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                 core.setScreen(new JOptions(core));
            }
        });

        TextButton exit = new TextButton("Exit", textButtonStyle);
        exit.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
        });

        table.setFillParent(true);
        table.add(new Label("Light in Dark", label1Style));
        table.row();
        table.add(new Label("", label1Style));
        table.row();
        table.add(startNewGame);
        table.row();
        table.add(continueB);
        table.row();
        table.add(menu);
        table.row();
        table.add(options);
        table.row();
        table.add(exit);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    public void show() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(15 / 255f, 15 / 255f, 20 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
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
    }
}
