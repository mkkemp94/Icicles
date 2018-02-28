package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by mkemp on 2/26/18.
 */

public class Icicles {

    public static final String TAG = Icicles.class.getName();

    DelayedRemovalArray<Icicle> icicleArray;
    private ExtendViewport viewport;

    public Icicles(ExtendViewport viewport) {
        this.viewport = viewport;
        init();
    }

    public void init() {
        icicleArray = new DelayedRemovalArray<Icicle>(false, 100);
    }

    public void update(float delta) {

        if (MathUtils.random() < delta * Constants.SPAWNS_PER_SECOND) {
            icicleArray.add(new Icicle(new Vector2(
                    MathUtils.random() * viewport.getWorldWidth(),
                    viewport.getWorldHeight()
            )));
        }

        for (Icicle icicle : icicleArray) {
            icicle.update(delta);
        }

        icicleArray.begin();

        for (int i = 0; i < icicleArray.size; i++) {
            if (icicleArray.get(i).position.y < -Constants.ICICLE_HEIGHT) {
                icicleArray.removeIndex(i);
            }
        }

        icicleArray.end();
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Constants.ICICLE_COLOR);
        for (Icicle icicle : icicleArray) {
            icicle.render(renderer);
        }
    }
}
