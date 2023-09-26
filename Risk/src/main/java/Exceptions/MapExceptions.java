package Exceptions;

public class MapExceptions extends Exception {
	    public MapExceptions(String p_message) {
	        super(p_message);
	    }
}
class MapValidator {
    public void validateMap(String p_gameMap) throws MapExceptions {
    	
        if (!isValidMap(p_gameMap)) {
            throw new MapExceptions("Invalid map: The map configuration is not valid.");
        }
    }

    private boolean isValidMap(String p_gameMap) {
    	//call the function for validating map
       return true;
    }
}