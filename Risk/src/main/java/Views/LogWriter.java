package Views;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import Tools.Observer;

/**
 * This class enables to write in the log file.
 *
 */
public class LogWriter implements Observer {
	
	private String l_fileName= "LogEntry";
	
	
	/**
	 * This function updates the log messages.
	 * @param p_log refers to the log message that needs to be updated.
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
