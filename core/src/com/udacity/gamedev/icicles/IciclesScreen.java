package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by mkemp on 2/26/18.
 */

public class IciclesScreen implements Screen {

    private ShapeRenderer renderer;
    private ExtendViewport viewport;
    private Icicle icicle;

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        icicle = new Icicle(new Vector2(Constants.WORLD_SIZE / 2, Constants.WORLD_SIZE / 2));
    }

    @Override
    public void render(float delta) {
        viewport.apply();

        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
//        renderer.triangle(50, 20, 70, 30, 50, 40);
        icicle.render(renderer);
        renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(Constants.WORLD_SIZE, Constants.WORLD_SIZE, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
