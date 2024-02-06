package controller;

import model.*;

public abstract class AbstractController {
	protected Game game;
	
	public abstract void restart() throws Exception;
	
	public void step() {
		game.step();
	}
	
	public void play() {
		game.launch();
	}
	
	public void pause() {
		game.pause();
	}
	
	public void setSpeed(double speed) {
		game.setTime((long)(1000 / speed));
	}
}
