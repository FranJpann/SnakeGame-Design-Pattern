package model;

import java.util.ArrayList;
import agent.Snake;

public class CollisionSnakeHimself {
	
	public ArrayList<Snake> listSnakes;
	
	public CollisionSnakeHimself(SnakeGame snakeGame) {
		this.listSnakes = snakeGame.listSnakes;
	}
	
	public boolean checkCollision() {
		boolean b = false;
		for(Snake s: listSnakes) {
			if(!s.getInvincible()) {
				for(int i=1; i < s.getPosition().size()-1; i++) {
					if(s.getPosition().get(i).getX() == s.getPosition().get(0).getX() && s.getPosition().get(i).getY() == s.getPosition().get(0).getY()) {
						b = true;
					}
				}
			}
		}
		return b;
	}
}
