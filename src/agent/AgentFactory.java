package agent;
import java.util.ArrayList;

import utils.*;

public interface AgentFactory {
	public ArrayList<Snake> creerAgents(ArrayList<FeaturesSnake> start_snakes);
}
