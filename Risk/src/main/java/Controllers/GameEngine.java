package Controllers;

import java.util.ArrayList;
import java.util.Scanner;

import Models.Continent;
import Models.Country;
import Models.LogEntryBuffer;
import Tools.ColorCoding;
import Tools.Connectivity;
import state.Phase;
import state.Preload;
import state.*;


/**
 *  Context of the State pattern. 
 *  It contains a State object (Here, State class is the class Phase). 
 */
public class GameEngine 
{
	
	LogEntryBuffer d_logEntryBuffer = new LogEntryBuffer();

	/**
	 * State object of the GameEngine 
	 */
	private Phase l_gamePhase;
	private Connectivity l_connectivity;
	private boolean l_checkIfTest = false;
	
    /**
     * Gets the value indicating if the GameEngine is in test mode.
     * @return true if the GameEngine is in test mode, false otherwise.
     */
	public boolean getCheckIfTest() 
	{
		return l_checkIfTest;
	}

    /**
     * Sets the test mode for the GameEngine.
     * @param l_checkIfTest true to enable test mode, false otherwise.
     */
	public void setCheckIfTest(boolean l_checkIfTest) 
	{
		this.l_checkIfTest = l_checkIfTest;
	}

	int mystart;
	String mycommand;

	/**
	 * Method that allows the GameEngine object to change its state.  
	 * @param p_phase new state to be set for the GameEngine object.
	 */
	public void setPhase(Phase p_phase) 
	{
		l_gamePhase = p_phase;
		d_logEntryBuffer.log("new phase: " + p_phase.getClass().getSimpleName());
		System.out.println("new phase: " + p_phase.getClass().getSimpleName());
	}
	
    /**
     * Gets the name of the current phase.
     * @return The name of the current phase.
     */
	public String getPhaseName()
	{
		return l_gamePhase.getClass().getSimpleName();
	}
	
    /**
     * Gets the current phase object.
     * @return The current phase object.
     */
	public Phase getPhase()
	{
		return l_gamePhase;
	}
	
	/**
	 * This method will ask the user: 
	 * 1. What part of the game they want to start with (edit map or play game). 
	 *      Depending on the choice, the state will be set to a different object, 
	 *      which will set different behavior. 
	 * 2. What command they want to execute from the game. 
	 *      Depending on the state of the GameEngine, each command will potentially 
	 *      have a different behavior. 
	 */
	
	public void start() 
	{
		d_logEntryBuffer.clearFile();
		Connectivity l_connectivity=new Connectivity();
		
		l_connectivity.setD_continentList(new ArrayList<Continent>());
		l_connectivity.setD_countryList(new ArrayList<Country>());
		boolean l_check_if_map_loaded = false;
		Scanner keyboard = new Scanner(System.in);
		Scanner phase_command = new Scanner(System.in);
		String[] l_commands;
		do {
			d_logEntryBuffer.log("Choice of Starting the Game or Ending it");
			System.out.println("1. Edit Map");
			System.out.println("2. Play Game");
			System.out.println("3. Quit");
			System.out.println("Where do you want to start?: ");
			mystart = keyboard.nextInt();
			switch (mystart) {
			case 1:
				setPhase(new Preload(this));
				break;
			case 2:
				setPhase(new PlaySetup(this));
				break;
			case 3:
				d_logEntryBuffer.log("Bye!");
				System.out.println("Bye!");
				return;
			}
			
			do 
			{
				System.out.println(" ====================================================================================================");
				System.out.println("| #   PHASE                   : command                                                             |"); 
				System.out.println(" ====================================================================================================");
				System.out.println("| 1.  Any                     : show map                                                            |");
				System.out.println("| 2.  Edit:PreLoad            : load map, edit country, edit continent, edit neighbor, validatemap  |");
				System.out.println("| 3.  Edit:PostLoad           : save map                                                            |");
				System.out.println("| 4.  Play:PlaySetup          : gameplayer, assigncountries                                         |");
				System.out.println("| 5.  Play:MainPlay:Reinforce : deploy                                                              |");
				System.out.println("| 6.  Play:MainPlay:Attack    : advance, bomb, airlift, blockade, negotiate                         |");
				System.out.println("| 7.  Play:MainPlay:Fortify   : fortify                                                             |");
				System.out.println("| 8.  End                     : end game                                                            |");
				System.out.println("| 9.  Any                     : next phase                                                          |");
				System.out.println(" ====================================================================================================");
				d_logEntryBuffer.log("enter a " + l_gamePhase.getClass().getSimpleName() + " phase command: ");
				System.out.println("enter a " + l_gamePhase.getClass().getSimpleName() + " phase command: ");
				mycommand = phase_command.nextLine();
				l_commands = mycommand.split(" "); 
				System.out.println(" =================================================");
				
				if(l_commands[0]!= null)
				{
				switch (l_commands[0]) 
				{
				case "loadmap":					
					l_gamePhase.loadMap(l_connectivity,l_commands);
					l_check_if_map_loaded = true;
					break;
				case "validatemap":
//					d_logEntryBuffer.log("Validating the loaded Map");
					if(l_check_if_map_loaded) l_gamePhase.validateMap(l_connectivity);
					else {
						d_logEntryBuffer.log("ERROR: Map cannot be validated before loading it");
						System.out.println(ColorCoding.red+"ERROR: Map cannot be validated before loading it"+ColorCoding.blank);
					}
					break;
				case "showmap":
					if(l_check_if_map_loaded) l_gamePhase.viewMap(l_connectivity.getD_continentList(),l_connectivity.getD_countryList(),Play.getL_playersArray());
					else {
						d_logEntryBuffer.log("ERROR: Map cannot be viewed before loading it");
						System.out.println(ColorCoding.red+"ERROR: Map cannot be viewed before loading it"+ColorCoding.blank);
					}
					break;
				case "help":
					l_gamePhase.help();
					break;
				case "editcountry":
					l_gamePhase.editCountry(l_commands, l_connectivity);
					break;
				case "editcontinent":
					if(l_check_if_map_loaded) l_gamePhase.editContinent(l_commands, l_connectivity);
					else {
						d_logEntryBuffer.log("ERROR: Map cannot be edited before loading it");
						System.out.println(ColorCoding.red+"ERROR: Map cannot be edited before loading it"+ColorCoding.blank);
					}
					break;	
				case "editneighbor":
					l_gamePhase.editNeighbor(l_commands, l_connectivity);
					break;						
				case "savemap":
					l_gamePhase.saveMap(l_connectivity, l_commands[1]);
					break;				
				case "gameplayer":
					l_gamePhase.setPlayers(l_commands);
					break;
				case "assigncountries":
					if(l_gamePhase.assignCountries(l_connectivity))
					{
					l_gamePhase.next();
					}
					break;
				case "deploy":
					l_gamePhase.reinforce(l_connectivity);
					break;
				case "attack":
					l_gamePhase.attack(l_connectivity);
					break;
				case "fortify":
					l_gamePhase.fortify(l_connectivity);
					break;
				case "exit":
					l_gamePhase.endGame();
					break;
				default: 
					d_logEntryBuffer.log("This command does not exist");
					System.out.println("This command does not exist");
				}
				}
			} while (!(l_gamePhase instanceof End));
		} while (l_commands[0] != "exit");
		keyboard.close();
	}

    /**
     * Gets the connectivity object.
     * @return Connectivity object.
     */
	public Connectivity getConnectivity() 
	{
		return l_connectivity;
	}

    /**
     * sets the connectivity object.
     * @param connectivity Connectivity object.
     */
	public void setConnectivity(Connectivity connectivity) 
	{
		this.l_connectivity = connectivity;
	}
}
