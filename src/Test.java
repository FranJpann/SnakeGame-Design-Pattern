import controller.*;

public class Test {

	public static void main(String[] args) throws Exception {
		newGame();
	}
	
	public static void newGame() throws Exception {
		new ControllerSnakeGame("layouts/alone.lay");
	}
}
