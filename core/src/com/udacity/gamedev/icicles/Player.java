package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mkemp on 2/26/18.
 */

public class Player {

    private Vector2 position;

    public Player(Vector2 position) {
        this.position = position;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Constants.PLAYER_BODY_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Line);

        float headRadius = Constants.PLAYER_HEAD_RADIUS;
        float bodyHeight = Constants.PLAYER_BODY_HEIGHT;
        float limbLength = Constants.PLAYER_LIMB_LENGTH;

        renderer.circle(position.x, position.y, headRadius, 100);
        renderer.line(
                position.x,
                position.y - headRadius,
                position.x,
                position.y - headRadius - bodyHeight
        );
        renderer.line(
                position.x,
                position.y - headRadius - bodyHeight * 0.2f,
                position.x - limbLength,
                position.y - headRadius - bodyHeight * 0.2f - limbLength
        );
        renderer.line(
                position.x,
                position.y - headRadius - bodyHeight * 0.2f,
                position.x + limbLength,
                position.y - headRadius - bodyHeight * 0.2f - limbLength
        );
        renderer.line(
                position.x,
                position.y - headRadius - bodyHeight,
                position.x - limbLength,
                position.y - headRadius - bodyHeight - limbLength
        );
        renderer.line(
                position.x,
                position.y - headRadius - bodyHeight,
                position.x + limbLength,
                position.y - headRadius - bodyHeight - limbLength
        );
    }
}
