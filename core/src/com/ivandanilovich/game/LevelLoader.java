package com.ivandanilovich.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;


public class LevelLoader {
    private final String T = this.toString();
    public Vector2 heroPosition, StartPosition, FinishPosition;
    World world;
    public Model model;

    public LevelLoader(World world) {
        this.world = world;
    }

    public void loadLevel(int id) {
        FileHandle file = Gdx.files.internal("Levels/Jlevel" + id + ".json");
        Json json = new Json();
        model = json.fromJson(Model.class, file);
        ChainCreater creater = new ChainCreater(world);

        Object[] vectors =  model.points.toArray();

        for (int i=0;i<vectors.length-1;i++){
            creater.create((Vector2)vectors[i],(Vector2) vectors[i+1]);
        }
    }
}

