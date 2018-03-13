package com.ivandanilovich.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Timer;
import java.util.TimerTask;

import box2dLight.PointLight;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JOptions implements Screen {
    private final Stage stage;
    private Core core;

    JOptions(final Core Core) {
        this.core = Core;

        stage = new Stage(new ScreenViewport());


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = core.font;

        textButtonStyle.up = core.skin.getDrawable("empty");
        textButtonStyle.down = core.skin.getDrawable("empty");
        textButtonStyle.checked = core.skin.getDrawable("empty");

        Label.LabelStyle labelStyle = new Label.LabelStyle();

        labelStyle.font = core.font;

        Table table = new Table();


        TextButton back = new TextButton("Back", textButtonStyle);
        back.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                core.setScreen(new Welcome(core));
            }
        });

        final ImageButton.ImageButtonStyle vStyleOff = new ImageButton.ImageButtonStyle();
        final ImageButton.ImageButtonStyle vStyleOn = new ImageButton.ImageButtonStyle();

        vStyleOn.imageDown = core.skin.getDrawable("volume");
        vStyleOn.imageUp = core.skin.getDrawable("volumeOff");

        vStyleOff.imageDown = core.skin.getDrawable("volumeOff");
        vStyleOff.imageUp = core.skin.getDrawable("volume");


        final ImageButton volume = new ImageButton(core.skin.getDrawable("volume"));
        if (Gdx.app.getPreferences("My Preferences").getBoolean("isMusic", true)) {
            volume.setStyle(vStyleOff);
        } else {
            volume.setStyle(vStyleOn);
        }


        volume.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Preferences prefs = Gdx.app.getPreferences("My Preferences");
                Gdx.app.log("Tag", "herre");
                if (prefs.getBoolean("isMusic", true)) {
                    volume.setStyle(vStyleOn);
                    core.backgroundMusic.pause();
                    prefs.putBoolean("isMusic", false);
                } else {
                    volume.setStyle(vStyleOff);
                    core.backgroundMusic.play();
                    prefs.putBoolean("isMusic", true);
                }
                prefs.flush();
                super.touchUp(event, x, y, pointer, button);
            }
        });
        table.setFillParent(true);
        table.add(new Label("Options", labelStyle));
        table.row();

        table.add(new Label("", labelStyle));
        table.row();

        table.add(volume);
        table.row();
        table.add(new Label("", labelStyle));
        table.row();

        table.add(back);


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
