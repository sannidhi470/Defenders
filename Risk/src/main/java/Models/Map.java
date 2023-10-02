package Models;

import java.util.ArrayList;

import dnl.utils.text.table.TextTable;

public class Map {

	private String d_mapFileName;
	private boolean d_mapExist;
	
	public Map()
	{
		this.d_mapExist=false;
	}
	public String getD_mapFileName() {
		return d_mapFileName;
	}
	public void setD_mapFileName(String d_mapFileName) {
		this.d_mapFileName = d_mapFileName;
	}
	public boolean isD_mapExist() {
		return d_mapExist;
	}
	public void setD_mapExist(boolean d_mapExist) {
		this.d_mapExist = d_mapExist;
	}
	
	
}