package agent;

import java.util.ArrayList;

import agentStrategy.*;
import model.InputMap;
import utils.*;

public class Snake implements Agent{

	public FeaturesSnake FeatureSnake;
	public AgentAction lastAction;
	
	public ArrayList<Position> positions;
	public int oldLastPositionX, oldLastPositionY;
	
	SnakeStrategy strategy;
	
	private boolean isInvincible = false;
	public int currentTurnInvincible = 0;
	private boolean isSick = false;
	public int currentTurnSick = 0;
	
	
	public Snake(FeaturesSnake Fs) {
		this.positions = Fs.getPositions();
		this.lastAction = Fs.getLastAction();
		this.FeatureSnake = Fs;
	}
	
	public void setSick(boolean b) {
		this.isSick = b;
		FeatureSnake.setSick(b);
	}
	
	public boolean getSick() {
		return isSick;
	}
	
	public void setInvincible(boolean b) {
		this.isInvincible = b;
		FeatureSnake.setInvincible(b);
	}
	
	public boolean getInvincible() {
		return isInvincible;
	}
	
	public ArrayList<Position> getPosition() {
		return positions;
	}
	
	// Ajoute une position dans le tableau positions
	public void addPosition(Position p) {
		positions.add(p);
	}
	
	// Incrémente les positions du tableau positions d'un nombre x et d'un nombre y
	public void addToPosition(int x, int y, InputMap map) {
		
		int xMap = map.getSizeX() - 1;
		int yMap = map.getSizeY() - 1;
		
		this.oldLastPositionX = positions.get(positions.size() - 1).getX();
		this.oldLastPositionY = positions.get(positions.size() - 1).getY();
		
		for(int i = positions.size() - 1; i >= 0; i--) {
			if(i == 0) {
				if(positions.get(i).getX() + x < 0) positions.get(i).setX(xMap);
				else if(positions.get(i).getX() + x > xMap) positions.get(i).setX(0);
				else if(positions.get(i).getY() + y < 0) positions.get(i).setY(yMap);
				else if(positions.get(i).getY() + y > yMap) positions.get(i).setY(0);
				else {
					positions.get(i).setX(positions.get(i).getX() + x);
					positions.get(i).setY(positions.get(i).getY() + y);
				}
			}
			else {
				positions.get(i).setX(positions.get(i-1).getX());
				positions.get(i).setY(positions.get(i-1).getY());
			}
		}
	}
	
	public boolean checkPosition(int x, int y){
		boolean b = false;
		for(Position p: positions) {
			if(p.getX() == x && p.getY() == y) b = true;
		}
		return b;
	}
	
	public void growSnake() {
		addPosition(new Position(this.oldLastPositionX, this.oldLastPositionY));
	}
	
	public void remove() {
		this.positions.clear();
	}
	
	public void setStrategy(SnakeStrategy s) {
		this.strategy = s;
	}
	
	public void mouvement(AgentAction action, InputMap map) {
		if(action == AgentAction.MOVE_UP) upMouvement(map);
		else if(action == AgentAction.MOVE_DOWN) downMouvement(map);
		else if(action == AgentAction.MOVE_LEFT) leftMouvement(map);
		else if(action == AgentAction.MOVE_RIGHT) rightMouvement(map);
	}

	@Override
	public void upMouvement(InputMap map) {
		addToPosition(0, -1, map);
		FeatureSnake.setLastAction(AgentAction.MOVE_UP);
	}
	@Override
	public void downMouvement(InputMap map) {
		addToPosition(0, 1, map);
		FeatureSnake.setLastAction(AgentAction.MOVE_DOWN);
	}
	@Override
	public void leftMouvement(InputMap map) {
		addToPosition(-1, 0, map);
		FeatureSnake.setLastAction(AgentAction.MOVE_LEFT);
	}
	@Override
	public void rightMouvement(InputMap map) {
		addToPosition(1, 0, map);
		FeatureSnake.setLastAction(AgentAction.MOVE_RIGHT);
	}
	
	public boolean play(InputMap map, AgentAction lastKey) {
		return strategy.play(map, lastKey);
	}
	
}
