package agentStrategy;

import java.util.ArrayList;
import java.util.Random;
import agent.Snake;
import item.Item;
import model.InputMap;
import utils.AgentAction;

public class SnakeStrategyRandom implements SnakeStrategy{
	
	Snake snake;
	ArrayList<Item> items;
	
	public SnakeStrategyRandom(Snake s, ArrayList<Item> items) {
		this.snake = s;
		this.items = items;
	}
	
	public boolean play(InputMap map, AgentAction lastKey) {
		
		ArrayList<AgentAction> possibleMoves = new ArrayList<AgentAction>();
		possibleMoves = possibleMoves(map);
		
		AgentAction bestMove = bestMove();
		AgentAction action = null;
		
		if(possibleMoves.isEmpty()) {
			if(snake.getInvincible()) {
				snake.mouvement(bestMove, map);
				return true;
			}
			else return false;
		}
		else {
			if(bestMove != null && possibleMoves.contains(bestMove)) {
				action = bestMove;
			}
			else {
				Random random = new Random();
				action = possibleMoves.get(random.nextInt(possibleMoves.size()));
			}
		
			System.out.println("Moves possibles :");
			for(AgentAction a: possibleMoves) System.out.println("	"+ a);
			System.out.println("Best move : " + bestMove + "\nMove Choisi : " + action + "\n");
		
			snake.mouvement(action, map);
			return true;
		}
	}
	
	public ArrayList<AgentAction> possibleMoves(InputMap map) {
		int xSnake = snake.getPosition().get(0).getX();
		int ySnake = snake.getPosition().get(0).getY();
		boolean walls[][] = map.get_walls();
		
		ArrayList<AgentAction> moves = new ArrayList<AgentAction>();

		if(ySnake > 0 && !snake.checkPosition(xSnake, ySnake - 1) && !walls[xSnake][ySnake - 1]) {
			moves.add(AgentAction.MOVE_UP); // UP
		}
		else if(ySnake == 0) {
			moves.add(AgentAction.MOVE_UP); // UP
		}
		
		if(ySnake < map.getSizeY() - 1 && !snake.checkPosition(xSnake, ySnake + 1) && !walls[xSnake][ySnake + 1]) {
			moves.add(AgentAction.MOVE_DOWN); // DOWN
		}
		else if(ySnake == map.getSizeY() - 1) {
			moves.add(AgentAction.MOVE_DOWN); // DOWN
		}
		
		if(xSnake > 0 && !walls[xSnake - 1][ySnake] && !snake.checkPosition(xSnake - 1, ySnake)) {
			moves.add(AgentAction.MOVE_LEFT); // LEFT
		}
		else if(xSnake == 0) {
			moves.add(AgentAction.MOVE_LEFT); // LEFT
		}
		
		if(xSnake < map.getSizeX() - 1 && !walls[xSnake+1][ySnake] && !snake.checkPosition(xSnake + 1, ySnake)) {
			moves.add(AgentAction.MOVE_RIGHT); // RIGHT
		}
		else if(xSnake == map.getSizeX() - 1) {
			moves.add(AgentAction.MOVE_RIGHT); // RIGHT
		}
		
		return moves;
	}
	
	public AgentAction bestMove() {
		int xSnake = snake.getPosition().get(0).getX();
		int ySnake = snake.getPosition().get(0).getY();
		
		Item item = nearestItem();
		
		int xItem = item.getX();
		int yItem = item.getY();
		
		int xDiff = Math.abs(xSnake - xItem);
		int yDiff = Math.abs(ySnake - yItem);
		
		if(xSnake < xItem && ySnake <= yItem) {		//BAS DROIT
			if(xDiff >= yDiff) return AgentAction.MOVE_RIGHT;
			else return AgentAction.MOVE_DOWN;
		}
		else if(xSnake >= xItem && ySnake <= yItem) {		//BAS GAUCHE
			if(xDiff > yDiff) return AgentAction.MOVE_LEFT;
			else return AgentAction.MOVE_DOWN;
		}
		else if(xSnake >= xItem && ySnake >= yItem) {		// HAUT GAUCHE
			if(xDiff > yDiff) return AgentAction.MOVE_LEFT;
			else return AgentAction.MOVE_UP;
		}
		else if(xSnake <= xItem && ySnake >= yItem) {		//HAUT DROIT
			if(xDiff > yDiff) return AgentAction.MOVE_RIGHT;
			else return AgentAction.MOVE_UP;
		}
		else return null;
	}
	
	public Item nearestItem() {
		Item item = items.get(0);
		int xSnake = snake.getPosition().get(0).getX();
		int ySnake = snake.getPosition().get(0).getY();
		
		double distance = Math.abs((xSnake - item.getX()) * (xSnake - item.getX()) - (ySnake - item.getY()) * (ySnake - item.getY()));
		distance = Math.sqrt(distance);
		
		double distanceTemp;
		
		System.out.println("\nDistance : " + distance);
		
		for(Item i: items) {
			distanceTemp = Math.sqrt(Math.abs((xSnake - i.getX()) * (xSnake - i.getX()) - (ySnake - i.getY()) * (ySnake - i.getY())));
			System.out.println("DistanceTemp : " + distanceTemp);
			if(distanceTemp < distance) {
				distance = distanceTemp;
				item = i;
			}
		}
		
		System.out.println("Distance choisie : " + distance);
		
		return item;
	}
}