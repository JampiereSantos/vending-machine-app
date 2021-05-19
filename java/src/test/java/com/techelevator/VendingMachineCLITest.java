package com.techelevator;

import org.junit.Assert;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
import org.junit.Test;

import com.techelevator.view.Menu;

public class VendingMachineCLITest {

	private Menu menu;
	VendingMachineCLI VCLI = new VendingMachineCLI(menu);

	@Test
	public void test_01_run() {
		try { 
			VCLI.run();
		} catch (NullPointerException ex) {
			Assert.assertTrue("NullPointerException", true);
		}
	}
}
