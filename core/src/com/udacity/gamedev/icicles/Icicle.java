package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mkemp on 2/26/18.
 */

public class Icicle {

    public static final String TAG = Icicle.class.getName();

    private Vector2 position;

    public Icicle(Vector2 position) {
        this.position = position;
    }

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
