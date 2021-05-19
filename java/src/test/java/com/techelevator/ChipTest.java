package com.techelevator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChipTest{

	Chip starchFlakes = new Chip ("StarchFlakes", "9.99");

	private  ByteArrayOutputStream test01dispense = new ByteArrayOutputStream();
	private  PrintStream originalOut = System.out;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(test01dispense));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}

	@Test
	public void test_01_dispenseTest() {
		starchFlakes.dispense();
		Assert.assertEquals("You have purchased: StarchFlakes for $9.99\nCrunch Crunch, Yum!", test01dispense.toString().trim());
	}

	@Test
	public void test_02_reduceQuantityTest() {
		starchFlakes.getQuantity();
		Assert.assertEquals("5", starchFlakes.getQuantity());

		starchFlakes.reduceQuantity();
		Assert.assertEquals("4", starchFlakes.getQuantity());

		starchFlakes.reduceQuantity();
		Assert.assertEquals("3", starchFlakes.getQuantity());

		starchFlakes.reduceQuantity();
		Assert.assertEquals("2", starchFlakes.getQuantity());

		starchFlakes.reduceQuantity();
		Assert.assertEquals("1", starchFlakes.getQuantity());

		starchFlakes.reduceQuantity();
		Assert.assertEquals("SOLD OUT", starchFlakes.getQuantity());

		starchFlakes.reduceQuantity();
		Assert.assertEquals("SOLD OUT", starchFlakes.getQuantity());
	}
}