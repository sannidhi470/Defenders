package Exceptions;

public class MapExceptions extends Exception {
	    public MapExceptions(String message) {
	        super(message);
	    }
}
class MapValidator {
    public void validateMap(String gameMap) throws MapExceptions {
    	
        if (!isValidMap()) {
            throw new MapExceptions("Invalid map: The map configuration is not valid.");
        }
    }

    private boolean isValidMap() {
    	//call the function for validating map
       return true;
    }
}