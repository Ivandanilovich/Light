package com.ivandanilovich.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


class EndGame implements Screen {

    private final Stage stage;
    private Core core;

    EndGame(final Core Core) {
        this.core = Core;
        stage = new Stage(new ScreenViewport());
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = core.font;
        labelStyle.fontColor = Color.BLACK;
        Table table =new Table();
        table.setFillParent(true);
        table.pad(50,0,0,0);
        table.add(new Label("The END",labelStyle)); table.row();
        table.add(new Label("Light in Dark",labelStyle)); table.row();
        table.add(new Label("Created by Ivan_Danilovich",labelStyle)); table.row();
        table.add(new Label("2017. Tomsk",labelStyle));

        stage.addActor(table);

        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                if(keycode==4){
                    core.setScreen(new Welcome(core));
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
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
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
        });

    }

    public void show() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
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
