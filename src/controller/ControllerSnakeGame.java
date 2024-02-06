package controller;

import model.*;
import view.*;


public class ControllerSnakeGame extends AbstractController{
	
	private ViewCommand commandWindow;
	private PanelSnakeGame panelSnakeGame;
	private ViewSnakeGame mainWindow;
	
	public String nameFileMap;
	public InputMap map;
	
	
	public ControllerSnakeGame(String nameFile) throws Exception {
		this.nameFileMap = nameFile;
		start();
	}
	
	public void start() throws Exception {
		map = new InputMap(nameFileMap);
		
		//View
		panelSnakeGame = new PanelSnakeGame(map.getSizeX(), map.getSizeY(), map.get_walls(), map.getStart_snakes(), map.getStart_items());
		mainWindow = new ViewSnakeGame(panelSnakeGame);
				
		//Model
		game = new SnakeGame(2000, map);
		
		mainWindow.enregistrerObservateur(game);
		
		commandWindow = new ViewCommand(this);
		
		game.enregistrerObservateur(mainWindow);
		game.enregistrerObservateur(commandWindow);
	}
	
	public void restart() throws Exception {
		
		commandWindow.dispose();
		mainWindow.dispose();
		game.init();
		
		start();
	}
}