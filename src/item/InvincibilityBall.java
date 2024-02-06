package item;

import java.util.ArrayList;
import java.util.Random;

import agent.Snake;
import model.InputMap;
import utils.FeaturesItem;

public class InvincibilityBall implements Item{
	public FeaturesItem FeatureItem;
	
	public InvincibilityBall(FeaturesItem item) {
		this.FeatureItem = item;
	}

	@Override
	public int getX() {
		return FeatureItem.getX();
	}
	
	@Override
	public int getY() {
		return FeatureItem.getY();
	}

	@Override
	public void setPosition(int x,int y) {
		this.FeatureItem.setX(x);
		this.FeatureItem.setY(y);
	}
	
	public void setRandomPosition(InputMap map, ArrayList<Snake> snakes) {
		Random random = new Random();
		
		int x = random.nextInt(map.getSizeX() - 2) + 1;
		int y = random.nextInt(map.getSizeY() - 2) + 1;
		
		for(Snake s: snakes) {
			while(s.checkPosition(x, y)) {
				x = random.nextInt(map.getSizeX() - 2) + 1;
				y = random.nextInt(map.getSizeY() - 2) + 1;
			}
		}
		this.setPosition(x, y);
	}

	@Override
	public FeaturesItem getFeatureItem() {
		return FeatureItem;
	}
}
