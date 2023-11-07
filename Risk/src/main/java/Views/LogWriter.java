package Views;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import Tools.Observer;


public class LogWriter implements Observer {
	
	private String l_fileName= "LogEntry";
	
	
	/**
	 * This function updates the log messages.
	 */
	public void update(String p_log) {
		writeLogFile(p_log);
	}
	
	/**
	 * This function writes the log messages and writes in the log file.
	 * @param p_logData refers to the data provided from the logger.
	 */
	public void writeLogFile(String p_logData) {
		PrintWriter l_writeData= null;
		try {
			checkDirectory("logFiles");
			l_writeData= new PrintWriter(new BufferedWriter(new FileWriter("logFiles/"+l_fileName + ".log", true)));
			l_writeData.println(p_logData);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			l_writeData.close();
		}
	}
	
	/**
	 * Checks for the path.
	 * @param path refers to the path provided.
	 */
	public void checkDirectory(String path) {
		File directory= new File(path);
		if(!directory.exists() || !directory.isDirectory()) {
			directory.mkdirs();
		}
	}

}
