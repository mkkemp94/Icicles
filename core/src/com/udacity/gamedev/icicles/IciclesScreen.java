package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by mkemp on 2/26/18.
 */

public class IciclesScreen extends InputAdapter implements Screen {

    public static final String TAG = IciclesScreen.class.getName();

    Constants.Difficulty difficulty;

    private  IciclesGame game;

    private ShapeRenderer renderer;
    private SpriteBatch spriteBatch;
    private BitmapFont font;

    private ExtendViewport gameViewport;
    private ScreenViewport hudViewport;

    private Player player;
    private Icicles icicles;

    private int topScore;

    public IciclesScreen(IciclesGame game, Constants.Difficulty difficulty) {
        this. game = game;
        this.difficulty = difficulty;
    }

    @Override
    public void show() {
        gameViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        hudViewport = new ScreenViewport();

        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        player = new Player(gameViewport);
        icicles = new Icicles(gameViewport, difficulty);

        topScore = 0;

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        player.update(delta);
        icicles.update(delta);

        if (player.hitByIcicle(icicles)) {
            icicles.init();
        }

        gameViewport.apply(true);

        Color bgColor = Constants.BACKGROUND_COLOR;
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(gameViewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        player.render(renderer);
        icicles.render(renderer);
        renderer.end();

        topScore = Math.max(topScore, icicles.iciclesDodged);

        hudViewport.apply();

        spriteBatch.setProjectionMatrix(hudViewport.getCamera().combined);

        spriteBatch.begin();
        font.draw(spriteBatch,
                "Difficulty: " + difficulty.label + "\n" +
                "Deaths: " + player.numDeaths,
                Constants.HUD_MARGIN,
                hudViewport.getWorldHeight() - Constants.HUD_MARGIN
        );
        font.draw(spriteBatch,
                "Score: " + icicles.iciclesDodged + "\n" +
                "Top Score: " + topScore,
                hudViewport.getWorldWidth() - Constants.HUD_MARGIN,
                hudViewport.getWorldHeight() - Constants.HUD_MARGIN,
                0, Align.right, false
        );
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
        hudViewport.update(width, height, true);
        font.getData().setScale(Math.min(width, height) / Constants.HUD_SCREEN_REFERENCE_SIZE);
        player.init();
        icicles.init();
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
        spriteBatch.dispose();
        font.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        game.showDifficultyScreen();
        return true;
    }
}
