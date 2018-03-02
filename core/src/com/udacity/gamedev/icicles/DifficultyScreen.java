package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by mkemp on 2/26/18.
 */

public class DifficultyScreen extends InputAdapter implements Screen {

    private IciclesGame game;

    private ShapeRenderer shapeRenderer;
    private FitViewport viewport;
    private SpriteBatch spriteBatch;
    private BitmapFont font;

    public DifficultyScreen(IciclesGame game) {
        this.game = game;
    }

    @Override
    public void show() {
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);

		spriteBatch = new SpriteBatch();
		viewport = new FitViewport(Constants.DIFFICULTY_WORLD_SIZE, Constants.DIFFICULTY_WORLD_SIZE);

		Gdx.input.setInputProcessor(this);

		font = new BitmapFont();
		font.getData().setScale(Constants.DIFFICULTY_LABEL_SCALE);
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
        viewport.apply();

        Color bgColor = Constants.BACKGROUND_COLOR;
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Constants.COLOR_COLD);
        shapeRenderer.circle(
                Constants.DIFFICULT_COLD_BUTTON_CENTER.x,
                Constants.DIFFICULT_COLD_BUTTON_CENTER.y,
                Constants.DIFFICULTY_SELECT_BUTTON_RADIUS,
                Constants.DIFFICULTY_SELECT_BUTTON_SEGMENTS
        );
        shapeRenderer.setColor(Constants.COLOR_COLDER);
        shapeRenderer.circle(
                Constants.DIFFICULT_COLDER_BUTTON_CENTER.x,
                Constants.DIFFICULT_COLDER_BUTTON_CENTER.y,
                Constants.DIFFICULTY_SELECT_BUTTON_RADIUS,
                Constants.DIFFICULTY_SELECT_BUTTON_SEGMENTS
        );
        shapeRenderer.setColor(Constants.COLOR_COLDEST);
        shapeRenderer.circle(
                Constants.DIFFICULT_COLDEST_BUTTON_CENTER.x,
                Constants.DIFFICULT_COLDEST_BUTTON_CENTER.y,
                Constants.DIFFICULTY_SELECT_BUTTON_RADIUS,
                Constants.DIFFICULTY_SELECT_BUTTON_SEGMENTS
        );
        shapeRenderer.end();

        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        GlyphLayout easyLayout = new GlyphLayout(font, Constants.LABEL_COLD);
        GlyphLayout mediumLayout = new GlyphLayout(font, Constants.LABEL_COLDER);
        GlyphLayout hardLayout = new GlyphLayout(font, Constants.LABEL_COLDEST);

        font.draw(spriteBatch,
                Constants.LABEL_COLD,
                Constants.DIFFICULT_COLD_BUTTON_CENTER.x,
                Constants.DIFFICULT_COLD_BUTTON_CENTER.y + easyLayout.height / 2,
                0, Align.center, false);
        font.draw(spriteBatch,
                Constants.LABEL_COLDER,
                Constants.DIFFICULT_COLDER_BUTTON_CENTER.x,
                Constants.DIFFICULT_COLDER_BUTTON_CENTER.y + mediumLayout.height / 2,
                0, Align.center, false);
        font.draw(spriteBatch,
                Constants.LABEL_COLDEST,
                Constants.DIFFICULT_COLDEST_BUTTON_CENTER.x,
                Constants.DIFFICULT_COLDEST_BUTTON_CENTER.y + hardLayout.height / 2,
                0, Align.center, false);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        shapeRenderer.dispose();
        font.dispose();
        shapeRenderer.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        if (worldTouch.dst(Constants.DIFFICULT_COLD_BUTTON_CENTER) < Constants.DIFFICULTY_SELECT_BUTTON_RADIUS) {
            game.showIciclesScreen(Constants.Difficulty.EASY);
        } else if (worldTouch.dst(Constants.DIFFICULT_COLDER_BUTTON_CENTER) < Constants.DIFFICULTY_SELECT_BUTTON_RADIUS) {
            game.showIciclesScreen(Constants.Difficulty.MEDIUM);
        } else if (worldTouch.dst(Constants.DIFFICULT_COLDEST_BUTTON_CENTER) < Constants.DIFFICULTY_SELECT_BUTTON_RADIUS) {
            game.showIciclesScreen(Constants.Difficulty.HARD);
        }

        return true;

    }
}
