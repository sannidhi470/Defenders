package state;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Controllers.GameEngine;
import Models.Player;
import Tools.Connectivity;
import Tools.SaveGame;

/**
 * The End class represents the end phase of the game.
 * This phase handles actions that can be performed when the game has ended.
 */
public class End extends Phase 
{

    /**
     * Constructs a new End phase with the given GameEngine.
     *
     * @param p_ge The GameEngine object associated with this phase.
     */
    End(GameEngine p_ge) 
    {
        super(p_ge);
    }

    /**
     * This method is not applicable in the End phase.
     * It prints an invalid command message.
     */
    public void loadMap() 
    {
        printInvalidCommandMessage(); 
    }

    /**
     * This method is not applicable in the End phase.
     * It prints an invalid command message.
     */
    public void showMap() 
    {
        printInvalidCommandMessage(); 
    }

    /**
     * This method is not applicable in the End phase.
     * It prints an invalid command message.
     */
    public void editCountry() 
    {
        printInvalidCommandMessage(); 
    }

    /**
     * This method is not applicable in the End phase.
     * It prints an invalid command message.
     */
    public void saveMap() 
    {
        printInvalidCommandMessage(); 
    }

    /**
     * This method is not applicable in the End phase.
     * It prints an invalid command message.
     *
     * @param p_commands An array of user commands.
     */
    public void setPlayers(String[] p_commands,Connectivity p_connectivity) 
    {
        printInvalidCommandMessage(); 
    }

    /**
     * This method is not applicable in the End phase.
     * It prints an invalid command message.
     *
     * @param p_connectivity refers to connectivity object
     * @return Always returns false.
     */
    public boolean assignCountries(Connectivity p_connectivity) 
    {
        printInvalidCommandMessage();
        return false; 
    }

    /**
     * This method is not applicable in the End phase.
     * It prints an invalid command message.
     *
     * @param p_connectivity refers to connectivity object
     */
    public void reinforce(Connectivity p_connectivity) 
    {
        printInvalidCommandMessage(); 
    }

    /**
     * This method is not applicable in the End phase.
     * It prints an invalid command message.
     *
     * @param p_connectivity refers to connectivity object
     */
    public void attack(Connectivity p_connectivity) 
    {
        printInvalidCommandMessage(); 
    }

    /**
     * This method is not applicable in the End phase.
     * It prints an invalid command message.
     *
     * @param p_connectivity refers to connectivity object
     */
    public void fortify(Connectivity p_connectivity) 
    {
        printInvalidCommandMessage(); 
    }

    /**
     * This method ends the game by printing a thank you message and exiting the program.
     */
    public void endGame(Connectivity p_connectivity)
    {
    	Scanner sc= new Scanner(System.in);
    	System.out.println("Do you want to save the game?");
    	String choice = "";
    	if(ge.getCheckIfTournament() || ge.getCheckIfTest())
    		choice = "no";
    	else
    		choice = sc.nextLine();
    	if(choice.equalsIgnoreCase("yes"))
    	{
    		System.out.println("Enter the command: ");
    		String[] l_command= sc.nextLine().split(" ");
    		String l_filename=l_command[1];
    		SaveGame sg = new SaveGame();
    		try 
    		{
				sg.saveGame(this,p_connectivity,l_filename);
			} catch (IOException e) 
    		{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Thank you for Playing the Game");
            System.exit(0);
    	}
    	else if (choice.equalsIgnoreCase("no"))
    	{
        System.out.println("Thank you for Playing the Game");
    	}
    	return;
    }

    /**
     * This method is not applicable in the End phase.
     * It prints an invalid command message.
     */
    public void next(Connectivity p_connectivity) 
    {
        printInvalidCommandMessage(); 
    }

    /**
     * This method is not implemented in the End phase.
     *
     * @param p_connectivity refers to connectivity object
     * @param p_commands An array of String commands.
     */
    @Override
    public void loadMap(Connectivity p_connectivity, String[] p_commands) 
    {
        // TODO Auto-generated method stub
    }

    /**
     * This method is not implemented in the End phase.
     *
     * @param p_connectivity refers to connectivity object
     */
    @Override
    public void validateMap(Connectivity p_connectivity) 
    {
        // TODO Auto-generated method stub
    }

    /**
     * This method is not implemented in the End phase.
     *
     * @param p_commands An array of user commands.
     * @param p_connectivity refers to connectivity object
     */
    @Override
    public void editCountry(String[] p_commands, Connectivity p_connectivity) 
    {
        // TODO Auto-generated method stub
    }

    /**
     * This method is not implemented in the End phase.
     *
     * @param p_commands An array of user commands.
     * @param p_connectivity refers to connectivity object
     */
    @Override
    public void editContinent(String[] p_commands, Connectivity p_connectivity) 
    {
        // TODO Auto-generated method stub
    }

    /**
     * This method is not implemented in the End phase.
     *
     * @param p_commands An array of user commands.
     * @param p_connectivity refers to connectivity object
     */
    @Override
    public void editNeighbor(String[] p_commands, Connectivity p_connectivity) 
    {
        // TODO Auto-generated method stub
    }

    /**
     * This method is not implemented in the End phase.
     *
     * @param p_connectivity refers to connectivity object
     * @param p_mapName The name of the map to be saved.
     */
    @Override
    public void saveMap(Connectivity p_connectivity, String p_mapName)
    {
        // TODO Auto-generated method stub
    }
	@Override
	public void loadgame(String[] p_commands, Connectivity p_connectivity, GameEngine ge) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public
	void enableTournament(String mycommand) {
		// TODO Auto-generated method stub
		
	}
}