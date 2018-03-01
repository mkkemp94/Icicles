package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mkemp on 2/26/18.
 */

public class Constants {

    public static final float WORLD_SIZE = 10.0f;

    public static final Color BACKGROUND_COLOR = Color.BLUE;
    public static final Color ICICLE_COLOR = Color.WHITE;
    public static final Color PLAYER_BODY_COLOR = Color.BROWN;

    public static final float ICICLE_HEIGHT = 1.0f;
    public static final float ICICLE_WIDTH = 0.5f;

    public static final int PLAYER_HEAD_SEGMENTS = 20;
    public static final float PLAYER_HEAD_RADIUS = 0.5f;
    public static final float PLAYER_HEAD_HEIGHT = 4.0f * PLAYER_HEAD_RADIUS; // height off ground
    public static final float PLAYER_LIMB_WIDTH = 0.1f;

    public static final float PLAYER_MOVEMENT = 10.0f;

    public static final float ACCELEROMETER_SENSITIVITY = 0.5f;
    public static final float GRAVITATIONAL_ACCELERATION = 9.8f;

    public static final float SPAWNS_PER_SECOND = 5f;
    public static final Vector2 ICICLE_ACCELERATION = new Vector2(0, -5.0f);

    public static final float HUD_SCREEN_REFERENCE_SIZE = 480;
    public static final float HUD_MARGIN = 20.0f;

    public static final String LABEL_COLD = "Cold";
    public static final String LABEL_COLDER = "Colder";
    public static final String LABEL_COLDEST = "Coldest";

    public static final float SPAWN_RATE_COLD = 5f;
    public static final float SPAWN_RATE_COLDER = 10f;
    public static final float SPAWN_RATE_COLDEST = 15f;

    public enum Difficulty {
        EASY(SPAWN_RATE_COLD, LABEL_COLD),
        MEDIUM(SPAWN_RATE_COLDER, LABEL_COLDER),
        HARD(SPAWN_RATE_COLDEST, LABEL_COLDEST);

        float spawnRate;
        String label;

        Difficulty(float spawnRate, String label) {
            this.spawnRate = spawnRate;
            this.label = label;
        }

    }
}
