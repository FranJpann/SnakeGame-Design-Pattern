package item;

import java.util.ArrayList;

import agent.Snake;
import model.InputMap;
import utils.FeaturesItem;

public interface Item {
	
	public int getX();
	public int getY();
	public void setPosition(int x, int y);
	public void setRandomPosition(InputMap map, ArrayList<Snake> s);
	
	public FeaturesItem getFeatureItem();
}
