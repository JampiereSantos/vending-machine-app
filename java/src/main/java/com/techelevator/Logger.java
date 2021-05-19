package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	// Instance Variables
	String logPath;
	File logFile;
	PrintWriter logWriter;
	SimpleDateFormat simpleDateFormat;
	String pattern;
 
	// Constructor 
	public Logger() {
		this.logPath = "Log.txt";
		this.logFile = new File(logPath);
		this.pattern = "MM/dd/yyyy hh:mm:ss a";
		this.simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			this.logWriter = new PrintWriter(new FileOutputStream(logFile),true);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!");
		}
	}

	// Method
	
	public void log(String activity, String currentMoney, String remainingMoney) { 
		String dateTime = simpleDateFormat.format(new Date());
		logWriter.println(dateTime + " " + activity + " $" + currentMoney + " $" + remainingMoney);
	}
}