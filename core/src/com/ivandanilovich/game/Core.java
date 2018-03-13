package com.ivandanilovich.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Core extends Game {
    public BitmapFont font;
    public SpriteBatch batch;
    public Skin skin;
    public TextureAtlas atlas;
    public Music backgroundMusic;


    public void create() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("UI/fonts/mainfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 50;
        font = generator.generateFont(param);
        font.setColor(Color.YELLOW);
        generator.dispose();
        batch = new SpriteBatch();

        skin = new Skin();
        atlas = new TextureAtlas(Gdx.files.internal("UI/images/graphic.pack"));
        skin.addRegions(atlas);

        Preferences prefs = Gdx.app.getPreferences("My Preferences");

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds/BackMusic.mp3"));
        backgroundMusic.setLooping(true);
        if(prefs.getBoolean("isMusic",true)) {
            backgroundMusic.play();
        }
        prefs.putInteger("CountLevel", 15);
        prefs.flush();

        this.setScreen(new Welcome(this));

    }

    public void render() {
        super.render();
    }

    public void dispose() {
    }
}
