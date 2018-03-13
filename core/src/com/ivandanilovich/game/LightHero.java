package com.ivandanilovich.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.PointLight;
import box2dLight.RayHandler;

class LightHero {
    public Body c;
    public PointLight p;
    public RayHandler h;
    World w;
    OrthographicCamera cam;
    float Const;


    public LightHero(World world, OrthographicCamera camera, float WorldConst) {
        w = world;
        cam = camera;
        Const = WorldConst;
    }

    public void createHandler() {
        h = new RayHandler(w);
        h.setCombinedMatrix(cam.combined);

 /*       final String vertexShader =
                "attribute vec4 vertex_positions;\n" //
                        + "attribute vec4 quad_colors;\n" //
                        + "attribute float s;\n"
                        + "uniform mat4 u_projTrans;\n" //
                        + "varying vec4 v_color;\n" //
                        + "void main()\n" //
                        + "{\n" //
                        + "   v_color = s * quad_colors;\n" //
                        + "   gl_Position =  u_projTrans * vertex_positions;\n" //
                        + "}\n";
        final String fragmentShader = "#ifdef GL_ES\n" //
                + "precision lowp float;\n" //
                + "#define MED mediump\n"
                + "#else\n"
                + "#define MED \n"
                + "#endif\n" //
                + "varying vec4 v_color;\n" //
                + "void main()\n"//
                + "{\n" //
                + "  gl_FragColor = (1.0-v_color);\n" //// TODO: 12.03.2018 L.I.M.B.  блин
                + "}";

        ShaderProgram shader = new ShaderProgram(vertexShader,fragmentShader);
        Gdx.app.log("SHADER",""+shader.isCompiled());
        h.setLightShader(shader);
*/
        h.setBlur(true);
    }


    public void createCircle(float x, float y, float r) {
        Body circle;
        BodyDef circleDef = new BodyDef();
        circleDef.type = BodyDef.BodyType.DynamicBody;
        circleDef.position.set(x * Const, y * Const);
        circle = w.createBody(circleDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(r * Const);
        FixtureDef circleFixture = new FixtureDef();
        circleFixture.shape = circleShape;
        circleFixture.density = 10f;
        circleFixture.friction = 0;
        circleFixture.restitution = 0f;
        circle.createFixture(circleFixture);
        c = circle;
        c.setFixedRotation(true);
    }


    public void render(Matrix4 cam) {
        h.setCombinedMatrix(cam);
        h.updateAndRender();
    }

    public void createLight(Color color, float radius) {
        p = new PointLight(h, 5000, color, radius * Const, 0, 0);
        p.setSoftnessLength(100f);
        p.attachToBody(c);
    }

    float force = 1f;
    float limit = 4;

    public  void move(Vector2 vel){
        c.setLinearVelocity(vel);
    }
}
