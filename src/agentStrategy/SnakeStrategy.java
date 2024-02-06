package agentStrategy;

import model.InputMap;
import utils.AgentAction;

public interface SnakeStrategy {
	
	abstract public boolean play(InputMap map, AgentAction lastKey);
}