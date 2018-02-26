package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mkemp on 2/26/18.
 */

public class Icicle {

    private Vector2 position;

    public Icicle(Vector2 startPos) {
        // TODO : Randomize position
//        position = new Vector2(120, 150);
        position = startPos;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Constants.ICICLE_COLOR);

        float icicleWidth = Constants.ICICLE_WIDTH;
        float icicleHeight = Constants.ICICLE_HEIGHT;

        renderer.triangle(
                position.x, position.y,
                position.x + icicleWidth, position.y,
                position.x + icicleWidth / 2, position.y - icicleHeight);

    }

}
