package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Random;

/**
 * Created by mkemp on 2/26/18.
 */

public class Icicles {

    public static final String TAG = Icicles.class.getName();

    private Array<Icicle> icicleArray;
    private ExtendViewport viewport;

    public Icicles(ExtendViewport viewport) {
        this.viewport = viewport;
        // random generator to generate icicle objects at random times
        // random generator to randomise position
        icicleArray = new Array<Icicle>();
    }

    public void update(float delta) {
        Random random = new Random();
        if (random.nextFloat() < delta * Constants.SPAWNS_PER_SECOND) {
            icicleArray.add(new Icicle(viewport));
        }

        for (Icicle icicle : icicleArray) {
            icicle.update(delta);
        }
    }

    public void render(ShapeRenderer renderer) {
        for (Icicle icicle : icicleArray) {
            icicle.render(renderer);
        }
    }
}
