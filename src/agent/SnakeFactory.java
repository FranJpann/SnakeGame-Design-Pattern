package agent;
import java.util.ArrayList;
import utils.*;

public class SnakeFactory implements AgentFactory{

	@Override
	public ArrayList<Snake> creerAgents(ArrayList<FeaturesSnake> start_snakes) {
		ArrayList<Snake> snakes = new ArrayList<Snake>();
		for(FeaturesSnake snake : start_snakes) {
			snakes.add(new Snake(snake));
		}
		return snakes;
	}
}
