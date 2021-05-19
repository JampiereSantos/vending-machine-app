package com.techelevator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VMExceptionsTest {

	VMExceptions VME = new VMExceptions();
	
	private  ByteArrayOutputStream testStream = new ByteArrayOutputStream();
	private  PrintStream originalOut = System.out;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(testStream));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}
	
	@Test
	public void test_01_soldOut() {
		VME.soldOutException();
		Assert.assertEquals("Sorry, we are SOLD OUT of that item!", testStream.toString().trim());
	}
	
	@Test
	public void test_02_insufficientFunds() {
		VME.insufficientFundsException();
		Assert.assertEquals("Sorry, you do not have enough money! Feed me money!", testStream.toString().trim());
	}
	
	@Test
	public void test_03_generalVMException() {
		VME.generalVendoMaticException();
		Assert.assertEquals("Sorry, Vendo-Matic 800 is not working at the moment, please start over.", testStream.toString().trim());
	}
}
