package agentStrategy;
import agent.Snake;
import model.InputMap;
import utils.AgentAction;

public class SnakeStrategyHuman implements SnakeStrategy{
	
	Snake snake;
	
	public SnakeStrategyHuman(Snake s) {
		this.snake = s;	
	}

	@Override
	public boolean play(InputMap map, AgentAction lastKey) {
		snake.mouvement(lastKey, map);
		return !checkWall(map);
	}
	
	public boolean checkWall(InputMap map) {
		boolean b = false;
		boolean walls[][] = map.get_walls();
		
		if(walls[snake.getPosition().get(0).getX()][snake.getPosition().get(0).getY()]) b = true;
		return b;
	}
}
