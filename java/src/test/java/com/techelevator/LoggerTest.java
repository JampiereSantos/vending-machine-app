package com.techelevator;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class LoggerTest {

	String pattern = "MM/dd/yyyy hh:mm:ss a";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String dateTime = simpleDateFormat.format(new Date());

	@Test
	public void test_01_logTest() throws FileNotFoundException {
		Logger logTest = new Logger();
		logTest.log("TEST", "123.45", "678.90");
		Scanner logTestScanner = new Scanner(logTest.logFile);
		String logTestString = logTestScanner.nextLine();
		logTestScanner.close();
		Assert.assertEquals(logTestString, dateTime + " TEST $123.45 $678.90");
	} 
}
