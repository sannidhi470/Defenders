package Views;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import Tools.Observer;


public class LogWriter implements Observer {
	
	private String l_fileName= "LogEntry";
	
	public void update(String p_log) {
		writeLogFile(p_log);
	}
	
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
	
	public void checkDirectory(String path) {
		File directory= new File(path);
		if(!directory.exists() || !directory.isDirectory()) {
			directory.mkdirs();
		}
	}

}
