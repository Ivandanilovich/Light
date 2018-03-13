package com.ivandanilovich.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class ChainCreater {
    World world;

    public ChainCreater(World world) {
        this.world=world;
    }

    public Body create(Vector2 v1, Vector2 v2) {
        ChainShape chainShape = new ChainShape();
        chainShape.createChain(new Vector2[] {v1,v2});
        BodyDef chainBodyDef = new BodyDef();
        chainBodyDef.type = BodyDef.BodyType.StaticBody;
        Body chainBody = world.createBody(chainBodyDef);
        chainBody.createFixture(chainShape, 0);
        FixtureDef circleFixture = new FixtureDef();
        circleFixture.density = 10f;
        circleFixture.friction = 0;
        circleFixture.restitution = 0.1f;
        return chainBody;
    }
}


