package Controllers;

import java.util.ArrayList;
import java.util.Scanner;

import Models.Continent;
import Models.Country;
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

	/**
	 * State object of the GameEngine 
	 */
	private Phase gamePhase;
	private Connectivity connectivity;
	
	int mystart;
	String mycommand;

	/**
	 * Method that allows the GameEngine object to change its state.  
	 * @param p_phase new state to be set for the GameEngine object.
	 */
	public void setPhase(Phase p_phase) {
		gamePhase = p_phase;
		System.out.println("new phase: " + p_phase.getClass().getSimpleName());
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
	
	public void start() {
		Connectivity l_connectivity=new Connectivity();
		
		l_connectivity.setD_continentList(new ArrayList<Continent>());
		l_connectivity.setD_countryList(new ArrayList<Country>());
		Scanner keyboard = new Scanner(System.in);
		Scanner phase_command = new Scanner(System.in);
		String[] l_commands;
		do {
			System.out.println("1. Edit Map");
			System.out.println("2. Play Game");
			System.out.println("3. Quit");
			System.out.println("Where do you want to start?: ");
			mystart = keyboard.nextInt();
			switch (mystart) {
			case 1:
				// Set the state to Preload
				setPhase(new Preload(this));
				break;
			case 2:
				// Set the state to PlaySetup
				setPhase(new PlaySetup(this));
				break;
			case 3:
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
				System.out.println("| 8.  End                     : end game                                                             |");
				System.out.println("| 9.  Any                     : next phase                                                          |");
				System.out.println(" ====================================================================================================");
				System.out.println("enter a " + gamePhase.getClass().getSimpleName() + " phase command: ");
				mycommand = phase_command.nextLine();
				l_commands = mycommand.split(" "); 
				System.out.println(" =================================================");
				//
				// Calls the method corresponding to the action that the user has selected. 
				// Depending on what it the current state object, these method calls will  
				// have a different implementation. 
				//
				
				if(l_commands[0]!= null)
				{
				switch (l_commands[0]) {
				case "loadmap":					
					gamePhase.loadMap(l_connectivity,l_commands);
					break;
				case "validatemap":
//					d_logEntryBuffer.log("Validating the loaded Map");
					gamePhase.validateMap(l_connectivity);
					break;
				case "showmap":
					gamePhase.viewMap(l_connectivity.getD_continentList(),l_connectivity.getD_countryList(),Play.getL_playersArray());
					break;
				case "help":
					gamePhase.help();
					break;
				case "editcountry":
					gamePhase.editCountry(l_commands, l_connectivity);
					break;
				case "editcontinent":
					gamePhase.editContinent(l_commands, l_connectivity);
					break;	
				case "editneighbor":
					gamePhase.editNeighbor(l_commands, l_connectivity);
					break;						
				case "savemap":
					//setPhase(new PostLoad(this));
					gamePhase.saveMap(l_connectivity);
					break;				
				case "gameplayer":
					gamePhase.setPlayers(l_commands);
					break;
				case "assigncountries":
					if(gamePhase.assignCountries(l_connectivity))
					{
					gamePhase.next();
					}
					break;
				case "deploy":
					gamePhase.reinforce(l_connectivity);
					break;
				case "attack":
					gamePhase.attack(l_connectivity);
					break;
				case "fortify":
					gamePhase.fortify();
					break;
				case "exit":
					gamePhase.endGame();
					break;
				default: 
					System.out.println("This command does not exist");
				}
				}
			} while (!(gamePhase instanceof End));
		} while (l_commands[0] != "exit");
		keyboard.close();
	}

	public Connectivity getConnectivity() {
		return connectivity;
	}

	public void setConnectivity(Connectivity connectivity) {
		this.connectivity = connectivity;
	}
}
