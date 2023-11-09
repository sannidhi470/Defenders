package Models;

/**
 * The class Map defines Maps and it's properties such as File Name and checker if the file exists or not.
 *
 */

public class Map {

	private String d_mapFileName;
	private boolean d_mapExist;
	
	/**
	 * This is a default constructor.
	 *
	 */
	
	public Map()
	{
		this.d_mapExist=false;
	}
	
	/**
	 * Gets the file Name.
	 * @return file name
	 *
	 */
	
	public String getD_mapFileName() {
		return d_mapFileName;
	}
	
	/**
	 * Sets the file Name.
	 * @param d_mapFileName refers to the file name.
	 *
	 */
	
	public void setD_mapFileName(String p_mapFileName) {
		this.d_mapFileName = p_mapFileName;
	}
	
	/**
	 * Checks if the file exists for not.
	 * @return true or false accordingly
	 *
	 */
	
	public boolean isD_mapExist() {
		return d_mapExist;
	}
	
	/**
	 * Sets the checker for file existance.
	 * @param p_mapExist refers to the boolean checker.
	 *
	 */

	public void setD_mapExist(boolean p_mapExist) {
		this.d_mapExist = p_mapExist;
	}
	
	
}