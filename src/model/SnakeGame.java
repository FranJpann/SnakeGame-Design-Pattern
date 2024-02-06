package model;

import java.util.*;
import agent.*;
import agentStrategy.*;
import item.*;
import utils.FeaturesItem;
import utils.ItemType;

public class SnakeGame extends Game{

	InputMap map;
	AgentFactory fabrique;
	ArrayList<Snake> listSnakes = new ArrayList<Snake>();
	ArrayList<Item> Items = new ArrayList<Item>();
	
	Collisions strategieCollisions;
	
	public SnakeGame(int maxturn, InputMap map) {
		super(maxturn);
		this.map = map;
		initializeGame();
	}
	
	public void addSnakes() {
		fabrique = new SnakeFactory();
		listSnakes = fabrique.creerAgents(map.getStart_snakes());
		for(Snake s: listSnakes) {
			s.setStrategy(new SnakeStrategyRandom(s, Items));
		}
	}
	
	public void deleteSnake(Snake s) {
		s.remove();
		listSnakes.remove(s);
	}
	
	public void addItems() {
		ArrayList<FeaturesItem> items = map.getStart_items();
		for(FeaturesItem item: items) {
			if(item.getItemType() == ItemType.APPLE) Items.add(new Apple(item));
			else if(item.getItemType() == ItemType.INVINCIBILITY_BALL) Items.add(new InvincibilityBall(item));
			else if(item.getItemType() == ItemType.SICK_BALL) Items.add(new SickBall(item));
			else if(item.getItemType() == ItemType.BOX) Items.add(new Box(item));
		}
	}
	
	public void playSnakes() {
		ArrayList<Snake> snakesToDestroy = new ArrayList<Snake>();
		for(Snake s : listSnakes) {
			if(!s.play(map, lastKey) || strategieCollisions.checkCollisionSnakeHimself()) {
				snakesToDestroy.add(s);
			}
		}
		for(Snake s: snakesToDestroy) deleteSnake(s);
		if(listSnakes.isEmpty()) gameOver();
	}

	@Override
	public void initializeGame() {
		turn = 0;
		addItems();
		addSnakes();
		this.strategieCollisions = new Collisions(this);
		System.out.println("Initialisé.");
	}
	
	@Override
	public void takeTurn() {
		System.out.println("Tour " + this.turn + " du jeu en cours");
		
		//Check isSickTurn et isInvincibleTurn des snakes
		//Quand isSickTurn ou isInvincible est mis à 1 ça démarre le compte à rebours jusqu'à 20 tours
		for(Snake s: listSnakes) {
			if(s.getSick()) {
				if(s.currentTurnSick < 20) {
					s.currentTurnSick++;
				}
				else {
					s.setSick(false);
					s.currentTurnSick = 0;
				}
			}
			if(s.getInvincible()) {
				if(s.currentTurnInvincible < 20) {
					s.currentTurnInvincible++;
				}
				else {
					s.setInvincible(false);
					s.currentTurnInvincible = 0;
				}
			}
		}
		
		playSnakes();
		strategieCollisions.checkCollisionSnakeItem();
	}

	@Override
	public boolean gameContinue() {
		return isRunning;
	}

	@Override
	public void gameOver() {
		finalStop = true;
		pause();
		System.out.println("Game over !");
	}

}
