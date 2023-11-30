package state;

import Controllers.GameEngine;

/**
 * The abstract sub class Edit contains states for Preload and Postload states.
 */
public abstract class Edit extends Phase 
{

    /**
     * Constructor for the Edit class.
     *
     * @param p_ge The GameEngine object associated with this Edit phase.
     */
    Edit(GameEngine p_ge) 
    {
        super(p_ge);
    }

    /**
     * Loads a map. This method is expected to be overridden by subclasses.
     */
    public void loadMap() 
    {
        printInvalidCommandMessage();
    }

    /**
     * Displays the map.
     */
    public void showMap() 
    {
        System.out.println("Map is being displayed");
    }

    /**
     * Edits a country. This method is expected to be overridden by subclasses.
     */
    public void editCountry() 
    {
        printInvalidCommandMessage();
    }

    /**
     * Edits a continent. This method is expected to be overridden by subclasses.
     */
    public void editContinent() 
    {
        printInvalidCommandMessage();
    }

    /**
     * Edits a neighbor. This method is expected to be overridden by subclasses.
     */
    public void editNeighbor() 
    {
        printInvalidCommandMessage();
    }

    /**
     * Validates the map. This method is expected to be overridden by subclasses.
     */
    public void validateMap() 
    {
        printInvalidCommandMessage();
    }

    /**
     * Saves the map. This method is expected to be overridden by subclasses.
     */
    public void saveMap() 
    {
        printInvalidCommandMessage();
    }


}