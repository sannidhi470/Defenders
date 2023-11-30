package Controllers;

import java.io.FileNotFoundException;
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
 *  It contains a State object (in this example the State class is the class Phase). 
 */
public class GameEngine {
	
	LogEntryBuffer d_logEntryBuffer = new LogEntryBuffer();

	/**
	 * State object of the GameEngine 
	 */
	private static Phase gamePhase;
	private Connectivity connectivity;
	private boolean checkIfTest = false;
	private boolean checkIfTournament = false;
	private boolean checkIfLoad = false;
	private boolean checkIfSave = false;
	
	public boolean getCheckIfSave() {
		return checkIfSave;
	}

	public void setCheckIfSave(boolean checkIfSave) {
		this.checkIfSave = checkIfSave;
	}

	public boolean getCheckIfLoad() {
		return checkIfLoad;
	}

	public void setCheckIfLoad(boolean checkIfLoad) {
		this.checkIfLoad = checkIfLoad;
	}

	public boolean getCheckIfTournament() {
		return checkIfTournament;
	}
	
	public void setCheckIfTournament(boolean checkIfTournament) {
		this.checkIfTournament = checkIfTournament;
	}
	
	/**
	 * Used for the unit testing
	 * @return returns true for testing
	 */
	public boolean getCheckIfTest() {
		return checkIfTest;
	}
	/**
	 * This function sets the checkIfTest parameter.
	 * @param checkIfTest testing parameter
	 */
	public void setCheckIfTest(boolean checkIfTest) {
		this.checkIfTest = checkIfTest;
	}

	int mystart;
	String mycommand;

	/**
	 * Method that allows the GameEngine object to change its state.  
	 * @param p_phase new state to be set for the GameEngine object.
	 */
	public void setPhase(Phase p_phase) {
		gamePhase = p_phase;
		d_logEntryBuffer.log("new phase: " + p_phase.getClass().getSimpleName());
		System.out.println("new phase: " + p_phase.getClass().getSimpleName());
	}
	/**
	 * This function returns the phase of the game
	 * @return returns the name of the game phase.
	 */
	public static String getPhaseName()
	{
		return gamePhase.getClass().getSimpleName();
	}
	/**
	 * This function returns the game phase of the game
	 * @return returns the game phase
	 */
	
	public Phase getPhase()
	{
		return gamePhase;
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
	
	public void start() throws FileNotFoundException {
		d_logEntryBuffer.clearFile();
		Connectivity l_connectivity=new Connectivity();
		
		l_connectivity.setD_continentList(new ArrayList<Continent>());
		l_connectivity.setD_countryList(new ArrayList<Country>());
		boolean l_check_if_map_loaded = false;
		Scanner keyboard = new Scanner(System.in);
		Scanner phase_command = new Scanner(System.in);
		String[] l_commands;
		do {
			if(!this.getCheckIfLoad())
			{
			d_logEntryBuffer.log("Choice of Starting the Game or Ending it");
			System.out.println("1. Edit Map");
			System.out.println("2. Play Game");
			System.out.println("3. Quit");
			System.out.println("Where do you want to start?: ");
			mystart = keyboard.nextInt();
			}
			else if(this.getCheckIfLoad())
			{
				mystart=1;
			}
			switch (mystart) {
			case 1:
				setPhase(new Preload(this));
				break;
			case 2:
				setPhase(new PlayGame(this));
				break;
			case 3:
				d_logEntryBuffer.log("Bye!");
				System.out.println("Bye!");
				return;
			}
			
			do {
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
				d_logEntryBuffer.log("enter a " + gamePhase.getClass().getSimpleName() + " phase command: ");
				System.out.println("enter a " + gamePhase.getClass().getSimpleName() + " phase command: ");
				mycommand = phase_command.nextLine();
				l_commands = mycommand.split(" "); 
				System.out.println(" =================================================");
				if(l_commands[0]!= null)
				{
				switch (l_commands[0]) {
				case "tournament" :
					gamePhase.enableTournament(mycommand);
					System.out.println("Tournament complete! Thank you participating");
					System.exit(0);
				case "loadmap":					
					gamePhase.loadMap(l_connectivity,l_commands);
					l_check_if_map_loaded = true;
					break;
				case "validatemap":
					if(l_check_if_map_loaded) gamePhase.validateMap(l_connectivity);
					else {
						d_logEntryBuffer.log("ERROR: Map cannot be validated before loading it");
						System.out.println(ColorCoding.red+"ERROR: Map cannot be validated before loading it"+ColorCoding.blank);
					}
					break;
				case "showmap":
					if(l_check_if_map_loaded) gamePhase.viewMap(l_connectivity.getD_continentList(),l_connectivity.getD_countryList(),Play.getL_playersArray());
					else {
						d_logEntryBuffer.log("ERROR: Map cannot be viewed before loading it");
						System.out.println(ColorCoding.red+"ERROR: Map cannot be viewed before loading it"+ColorCoding.blank);
					}
					break;
				case "help":
					gamePhase.help();
					break;
				case "editcountry":
					gamePhase.editCountry(l_commands, l_connectivity);
					break;
				case "editcontinent":
					if(l_check_if_map_loaded) gamePhase.editContinent(l_commands, l_connectivity);
					else {
						d_logEntryBuffer.log("ERROR: Map cannot be edited before loading it");
						System.out.println(ColorCoding.red+"ERROR: Map cannot be edited before loading it"+ColorCoding.blank);
					}
					break;	
				case "editneighbor":
					gamePhase.editNeighbor(l_commands, l_connectivity);
					break;						
				case "savemap":
					gamePhase.saveMap(l_connectivity, l_commands[1]);
					break;				
				case "gameplayer":
					gamePhase.setPlayers(l_commands,l_connectivity);
					break;
				case "assigncountries":
					if(gamePhase.assignCountries(l_connectivity))
					{
					gamePhase.next(l_connectivity);
					gamePhase.reinforce(l_connectivity);
					gamePhase.attack(l_connectivity);
					if(this.getPhaseName().equals("End"))
					{
						return;
					}
					gamePhase.fortify(l_connectivity);
					}
					break;
				case "deploy":
					gamePhase.reinforce(l_connectivity);
					gamePhase.attack(l_connectivity);
					gamePhase.fortify(l_connectivity);
					break;
				case "attack":
					gamePhase.attack(l_connectivity);
					break;
				case "fortify":
					gamePhase.fortify(l_connectivity);
					break;
				case "loadgame":
					gamePhase.loadgame(l_commands,l_connectivity,this);
					break;
				case "exit":
					gamePhase.endGame(l_connectivity);
					break;
				default: 
					d_logEntryBuffer.log("This command does not exist");
					System.out.println("This command does not exist");
				}
				}
			} while (!(gamePhase instanceof End));
		} while (l_commands[0] != "exit");
		keyboard.close();
	}
	/**
	 * This method gets the connectivity object
	 * @return returns the map content
	 */
	public Connectivity getConnectivity() {
		return connectivity;
	}
	/**
	 * This function sets the map content.
	 * @param connectivity - holds values of the continent country neighbor 
	 */
	public void setConnectivity(Connectivity connectivity) {
		this.connectivity = connectivity;
	}
}
