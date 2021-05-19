package com.techelevator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DrinkTest{

	Drink dietMax = new Drink ("DietMax", "9.99");

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
		dietMax.dispense();
		Assert.assertEquals("You have purchased: DietMax for $9.99\nGlug Glug, Yum!", test01dispense.toString().trim());
	}

	@Test
	public void test_02_reduceQuantityTest() {
		dietMax.getQuantity();
		Assert.assertEquals("5", dietMax.getQuantity());

		dietMax.reduceQuantity();
		Assert.assertEquals("4", dietMax.getQuantity());

		dietMax.reduceQuantity();
		Assert.assertEquals("3", dietMax.getQuantity());

		dietMax.reduceQuantity();
		Assert.assertEquals("2", dietMax.getQuantity());

		dietMax.reduceQuantity();
		Assert.assertEquals("1", dietMax.getQuantity());

		dietMax.reduceQuantity();
		Assert.assertEquals("SOLD OUT", dietMax.getQuantity());

		dietMax.reduceQuantity();
		Assert.assertEquals("SOLD OUT", dietMax.getQuantity());
	}
}