package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by mkemp on 2/26/18.
 */

public class Icicles {

    public static final String TAG = Icicles.class.getName();

    private Array<Icicle> icicleArray;
    private ExtendViewport viewport;

    public Icicles(ExtendViewport viewport) {
        this.viewport = viewport;
        init();
    }

    public void init() {
        icicleArray = new Array<Icicle>(false, 100);
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
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Constants.ICICLE_COLOR);
        for (Icicle icicle : icicleArray) {
            icicle.render(renderer);
        }
    }
}
