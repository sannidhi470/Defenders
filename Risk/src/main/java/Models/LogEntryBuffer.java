package Models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;


import Tools.Observable;
import Views.LogWriter;


public class LogEntryBuffer implements Observable {
	
	private LogWriter d_logWriter= new LogWriter();
	
	
	public void log(String p_log) {
		notifyObservers(p_log);
	}
	
	@Override
	public void notifyObservers(String p_log) {
		d_logWriter.update(p_log);
	}
	
	public void clearFile() {
		PrintWriter l_writeData = null;
		String l_fileName= "LogEntry";
		try {
			l_writeData= new PrintWriter(new BufferedWriter(new FileWriter("logFile/" + l_fileName + ".log", false)));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
