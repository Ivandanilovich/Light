package com.ivandanilovich.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

class GetBox2dBody {
    Body createBox(World world, float x, float y, float w, float h, boolean isDinamic){
        Body body;
        BodyDef bodyDef = new BodyDef();
        if(isDinamic) bodyDef.type = BodyDef.BodyType.DynamicBody;
        else bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x,y);
        bodyDef.fixedRotation= false;
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w,h);
        body.createFixture(shape,1.0f);
        return body;
    }
}
