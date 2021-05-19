package com.techelevator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CandyTest{
 
	Candy betesCrunch = new Candy ("BetesCrunch", "9.99");

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
		betesCrunch.dispense();
		Assert.assertEquals("You have purchased: BetesCrunch for $9.99\nMunch Munch, Yum!", test01dispense.toString().trim());
	}

	@Test
	public void test_02_reduceQuantityTest() {
		betesCrunch.getQuantity();
		Assert.assertEquals("5", betesCrunch.getQuantity());

		betesCrunch.reduceQuantity();
		Assert.assertEquals("4", betesCrunch.getQuantity());

		betesCrunch.reduceQuantity();
		Assert.assertEquals("3", betesCrunch.getQuantity());

		betesCrunch.reduceQuantity();
		Assert.assertEquals("2", betesCrunch.getQuantity());

		betesCrunch.reduceQuantity();
		Assert.assertEquals("1", betesCrunch.getQuantity());

		betesCrunch.reduceQuantity();
		Assert.assertEquals("SOLD OUT", betesCrunch.getQuantity());

		betesCrunch.reduceQuantity();
		Assert.assertEquals("SOLD OUT", betesCrunch.getQuantity());
	}
}