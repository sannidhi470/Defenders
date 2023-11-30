package Controllers;

import java.io.FileNotFoundException;

/**
 * The main driver class for starting the game engine.
 */
public class GameDriver 
{

    /**
     * The main method that initializes and starts the game engine.
     *
     * @param args Command-line arguments.
     * @throws FileNotFoundException 
     */
	public static void main(String args[]) throws FileNotFoundException 
	{
		GameEngine gameEngine = new GameEngine();
		gameEngine.start();
	}
}
