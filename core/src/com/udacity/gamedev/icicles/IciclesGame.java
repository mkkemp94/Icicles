package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Game;

public class IciclesGame extends Game {
	
	@Override
	public void create () {
		Constants.Difficulty difficulty = Constants.Difficulty.HARD;
		setScreen(new IciclesScreen(difficulty));
	}
}
