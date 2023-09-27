package Models;
import java.util.*;

public class Order {
	
	String d_orderAction;
	String d_selectedTerritory;
	String d_sourceCountryName;
	Integer d_armiesToDeploy;
	Order orderObj;

	public Order() {
	}
	
	public Order(String p_orderAction, String p_targetCountryName, Integer p_armiesToDeploy) {
		this.d_orderAction = p_orderAction;
		this.d_selectedTerritory = p_targetCountryName;
		this.d_armiesToDeploy = p_armiesToDeploy;
	}

	public String getD_orderAction() {
		return d_orderAction;
	}

	void setD_orderAction(String p_orderAction) {
		this.d_orderAction = p_orderAction;
	}

	public String getD_targetCountryName() {
		return d_selectedTerritory;
	}

	public void setD_targetCountryName(String p_selectedTerritory) {
		this.d_selectedTerritory = p_selectedTerritory;
	}
	
	public String getD_sourceCountryName() {
		return d_sourceCountryName;
	}

	public void setD_sourceCountryName(String p_sourceCountryName) {
		this.d_sourceCountryName = p_sourceCountryName;
	}

	public Integer getD_numberOfArmiesToPlace() {
		return d_armiesToDeploy;
	}

	public void setD_numberOfArmiesToPlace(Integer p_numberOfArmiesToPlace) {
		this.d_armiesToDeploy = p_numberOfArmiesToPlace;
	}
	
	private boolean validateDeployOrder(Player player, String territory, int p_armiesToDeploy) {
        //check if the territory belongs to the player
        return true;
    }

    private void executeDeployOrder(Player player, String territory, int p_armiesToDeploy) {
        
        player.d_armiesOwned += p_armiesToDeploy;
    }

    public void deployPhase(Player currentPlayer) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Issue Orders Phase (Deploy)");
        System.out.print("Select a territory to deploy armies:");
        d_selectedTerritory = scanner.nextLine();
        System.out.print("Enter the number of armies to deploy: ");
        int d_armiesToDeploy = scanner.nextInt();

        
        System.out.println("Executing deploy order...");
        if (validateDeployOrder(currentPlayer, d_selectedTerritory, d_armiesToDeploy)) {
            executeDeployOrder(currentPlayer, d_selectedTerritory, d_armiesToDeploy);
            System.out.println(d_armiesToDeploy + " armies have been deployed to " + d_selectedTerritory);
        } else {
            System.out.println("Invalid deploy order. Check your input.");
        }
    }
}