package com.ivandanilovich.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ButtonHandler extends ClickListener {
    private Core core;
    private int Lx;
    public void setX(int x){
        this.Lx=x;
    }
    ButtonHandler(Core core){
        this.core=core;
    }
    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        core.setScreen(new LevelShow(core, Lx));
    }
}
