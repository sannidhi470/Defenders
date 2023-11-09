package Controllers;
/**
 * The main driver class for starting the game engine.
 */
public class GameDriver 
{

    /**
     * The main method that initializes and starts the game engine.
     *
     * @param args Command-line arguments.
     */
	public static void main(String args[]) 
	{
		GameEngine gameEngine = new GameEngine();
		gameEngine.start();
	}
}
