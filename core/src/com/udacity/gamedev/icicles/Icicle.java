package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by mkemp on 2/26/18.
 */

public class Icicle {

    public static final String TAG = Icicle.class.getName();

    private Vector2 position;
    private ExtendViewport viewport;

    public Icicle(ExtendViewport viewport) { //Vector2 position) {
        // TODO : Randomize position above screen
//        this.position = position;
        this.viewport = viewport;
        init();
    }

    public void init() {
        position = new Vector2(viewport.getWorldWidth() / 2, viewport.getWorldHeight() + Constants.ICICLE_HEIGHT);
    }

    // TODO : Add update method to update position based on gravity
    public void update(float delta) {
        position.y -= delta * Constants.GRAVITATIONAL_ACCELERATION * Constants.ACCELEROMETER_SENSITIVITY;
    }


    public void render(ShapeRenderer renderer) {

        renderer.setColor(Constants.ICICLE_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Filled);

        float icicleWidth = Constants.ICICLE_WIDTH;
        float icicleHeight = Constants.ICICLE_HEIGHT;

        renderer.triangle(
                position.x, position.y, // point
                position.x - icicleWidth / 2, position.y + icicleHeight, // upper left
                position.x + icicleWidth / 2, position.y + icicleHeight); // upper right

    }

}
