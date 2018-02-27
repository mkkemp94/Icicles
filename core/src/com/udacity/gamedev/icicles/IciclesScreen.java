package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by mkemp on 2/26/18.
 */

public class IciclesScreen implements Screen {

    public static final String TAG = IciclesScreen.class.getName();

    private ShapeRenderer renderer;
    private ExtendViewport viewport;
    private Icicle icicle;
    private Player player;
    private Icicles icicles;

    @Override
    public void show() {
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        icicle = new Icicle(viewport
//                new Vector2(
//                Constants.WORLD_SIZE / 2,
//                viewport.getWorldHeight() + Constants.ICICLE_HEIGHT * 3 //Constants.WORLD_SIZE / 2
//        )
        );
        player = new Player(viewport);
        icicles = new Icicles(viewport);
    }

    @Override
    public void render(float delta) {
        player.update(delta);
        icicle.update(delta);
        icicles.update(delta);

        viewport.apply(true);

        Color bgColor = Constants.BACKGROUND_COLOR;
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        icicle.render(renderer);
        player.render(renderer);
        icicles.render(renderer);
        renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        player.init();
        icicle.init();
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
