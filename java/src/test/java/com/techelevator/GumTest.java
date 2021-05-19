package com.techelevator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GumTest{

	Gum jawBall = new Gum ("JawBall", "9.99");

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
		jawBall.dispense();
		Assert.assertEquals("You have purchased: JawBall for $9.99\nChew Chew, Yum!", test01dispense.toString().trim());
	}

	@Test
	public void test_02_reduceQuantityTest() {
		jawBall.getQuantity();
		Assert.assertEquals("5", jawBall.getQuantity());

		jawBall.reduceQuantity();
		Assert.assertEquals("4", jawBall.getQuantity());

		jawBall.reduceQuantity();
		Assert.assertEquals("3", jawBall.getQuantity());

		jawBall.reduceQuantity();
		Assert.assertEquals("2", jawBall.getQuantity());

		jawBall.reduceQuantity();
		Assert.assertEquals("1", jawBall.getQuantity());

		jawBall.reduceQuantity();
		Assert.assertEquals("SOLD OUT", jawBall.getQuantity());

		jawBall.reduceQuantity();
		Assert.assertEquals("SOLD OUT", jawBall.getQuantity());
	}
}