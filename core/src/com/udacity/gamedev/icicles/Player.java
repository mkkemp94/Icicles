package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by mkemp on 2/26/18.
 */

public class Player {

    public static final String TAG = Player.class.getName();

    private Vector2 position;
    private Vector2 velocity;
    private Viewport viewport;

    public Player(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    public void init() {
        position = new Vector2(viewport.getWorldWidth() / 2, Constants.PLAYER_HEAD_HEIGHT);
        velocity = new Vector2();
    }

    public void render(float delta, ShapeRenderer renderer) {
        checkForInput(delta);
        renderBody(renderer);
    }

    private void checkForInput(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velocity.x = Constants.PLAYER_MOVEMENT;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocity.x = -Constants.PLAYER_MOVEMENT;
        }
        else {
            velocity.x = 0;
        }

        position.x += delta * velocity.x;

        collideWithWalls();
    }

    private void collideWithWalls() {
        float headRadius = Constants.PLAYER_HEAD_RADIUS;
        if (position.x - headRadius < 0) {
            position.x = headRadius;
            velocity.x = 0;
        } else if (position.x + headRadius > viewport.getWorldWidth()) {
            position.x = viewport.getWorldWidth() - headRadius;
            velocity.x = 0;
        }
    }

    private void renderBody(ShapeRenderer renderer) {
        renderer.setColor(Constants.PLAYER_BODY_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Filled);

        float headRadius = Constants.PLAYER_HEAD_RADIUS;
        float limbWidth = Constants.PLAYER_LIMB_WIDTH;

        renderer.circle(position.x, position.y, headRadius, Constants.PLAYER_HEAD_SEGMENTS);

        Vector2 torsoTop = new Vector2(position.x, position.y - headRadius);
        Vector2 torsoBottom = new Vector2(torsoTop.x, torsoTop.y - 2 * headRadius);

        renderer.rectLine(torsoTop, torsoBottom, limbWidth);

        renderer.rectLine(
                torsoTop.x, torsoTop.y,
                torsoTop.x + headRadius, torsoTop.y - headRadius,
                limbWidth
        );

        renderer.rectLine(
                torsoTop.x, torsoTop.y,
                torsoTop.x - headRadius, torsoTop.y - headRadius,
                limbWidth
        );

        renderer.rectLine(
                torsoBottom.x, torsoBottom.y,
                torsoBottom.x + headRadius, torsoBottom.y - headRadius,
                limbWidth
        );

        renderer.rectLine(
                torsoBottom.x, torsoBottom.y,
                torsoBottom.x - headRadius, torsoBottom.y - headRadius,
                limbWidth
        );
    }
}
