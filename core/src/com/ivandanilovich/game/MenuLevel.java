package com.ivandanilovich.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MenuLevel implements Screen {
    private final Stage stage;
    private Core core;

    MenuLevel(final Core Core) {
        this.core = Core;

        stage = new Stage(new ScreenViewport());


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = core.font;
        textButtonStyle.up = core.skin.getDrawable("btn");
        textButtonStyle.down = core.skin.getDrawable("empty");
        textButtonStyle.checked = core.skin.getDrawable("btn");

        Table table = new Table();
        table.setFillParent(true);
        Preferences prefs = Gdx.app.getPreferences("My Preferences");
        int maxOpen = prefs.getInteger("MaxOpenLevel");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (LevelsVar.counter<=maxOpen) {
                    TextButton b = new TextButton(LevelsVar.counter+"", textButtonStyle);
                    ButtonHandler buttonHandler = new ButtonHandler(core);
                    int XX=LevelsVar.counter;
                    buttonHandler.setX(XX);
                    b.addListener(buttonHandler);
                    table.add(b).pad(20,20,20,20);
                    LevelsVar.counter++;
                } else {
                    Image image = new Image(core.skin.getDrawable("lock"));
                    table.add(image).pad(20,20,20,20);
                }
            }
            table.row();
        }
        LevelsVar.counter=1;
        stage.addActor(table);
        stage.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode==4){
                    core.setScreen(new Welcome(core));
                }
                return false;
            }
        });
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
