package com.ivandanilovich.game;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by user on 11.03.2018.
 */

public class LevelShow implements Screen {

    private final String T = this.toString();
    private final Stage stage;
    private final int x;
    private final long time;
    private Core core;

    LevelShow(final Core Core, int x) {
        this.x=x;
        this.core = Core;
        stage = new Stage(new ScreenViewport());

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = core.font;
        labelStyle.fontColor = Color.BLACK;

        Table table =new Table();
        table.setFillParent(true);
        table.pad(100,0,0,0);
        table.add(new Label("Level "+x,labelStyle));

        stage.addActor(table);


        time = TimeUtils.nanoTime();

    }

    public void show() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();


        if(TimeUtils.timeSinceNanos(time)>1500000000L){
            core.setScreen(new Game(core,x));
        }
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
