package Tools;

/**
 * This is an Observable interface.
 */
public interface Observable {
	
	/**
	 * This interface is used to write any data to the log file. 
	 * @param p_log refers to the data being written in log file.
	 */
	public void notifyObservers(String p_log);


}
