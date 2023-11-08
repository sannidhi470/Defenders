package Models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;


import Tools.Observable;
import Views.LogWriter;

/**
 * This class refers to the log entry into the file.
 */
public class LogEntryBuffer implements Observable {
	
	private LogWriter d_logWriter= new LogWriter();
	
	
	/**
	 * 
	 * This function is used to write any data to the log file. 
	 * @param p_log refers to the data being written in log file.
	 */
	public void log(String p_log) {
		notifyObservers(p_log);
	}
	
	/**
	 * This function notify all of its observers if the object has changed.
	 */
	
	@Override
	public void notifyObservers(String p_log) {
		d_logWriter.update(p_log);
	}
	
	/**
	 * This function is used to clean the log file at every call.
	 */
	
	public void clearFile() {
		PrintWriter l_writeData = null;
		String l_fileName= "LogEntry";
		try {
			l_writeData= new PrintWriter(new BufferedWriter(new FileWriter("logFiles/" + l_fileName + ".log", false)));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
