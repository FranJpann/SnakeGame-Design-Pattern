package agent;

import java.util.ArrayList;

import model.InputMap;
import utils.Position;

public interface Agent {
	
	public ArrayList<Position> getPosition();
	
	public void upMouvement(InputMap map);
	public void downMouvement(InputMap map);
	public void leftMouvement(InputMap map);
	public void rightMouvement(InputMap map);
}
