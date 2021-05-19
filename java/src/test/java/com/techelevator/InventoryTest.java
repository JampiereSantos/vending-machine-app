package com.techelevator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {

	Inventory inventoryTest = new Inventory();
	
	private  ByteArrayOutputStream test01displayItems = new ByteArrayOutputStream();
	private  PrintStream originalOut = System.out;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(test01displayItems));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}
	
	@Test
	public void test_01_displayItems() {
		inventoryTest.getListFromFile();
		inventoryTest.setInventoryMap();
		inventoryTest.displayItems();
		Assert.assertEquals("\r\n" + 
				"A1) Potato Crisps $3.05 Quantity: 5\r\n" + 
				"A2) Stackers $1.45 Quantity: 5\r\n" + 
				"A3) Grain Waves $2.75 Quantity: 5\r\n" + 
				"A4) Cloud Popcorn $3.65 Quantity: 5\r\n" + 
				"B1) Moonpie $1.80 Quantity: 5\r\n" + 
				"B2) Cowtales $1.50 Quantity: 5\r\n" + 
				"B3) Wonka Bar $1.50 Quantity: 5\r\n" + 
				"B4) Crunchie $1.75 Quantity: 5\r\n" + 
				"C1) Cola $1.25 Quantity: 5\r\n" + 
				"C2) Dr. Salt $1.50 Quantity: 5\r\n" + 
				"C3) Mountain Melter $1.50 Quantity: 5\r\n" + 
				"C4) Heavy $1.50 Quantity: 5\r\n" + 
				"D1) U-Chews $0.85 Quantity: 5\r\n" + 
				"D2) Little League Chew $0.95 Quantity: 5\r\n" + 
				"D3) Chiclets $0.75 Quantity: 5\r\n" + 
				"D4) Triplemint $0.75 Quantity: 5\r\n" + 
				"", test01displayItems.toString());
	}
	
	@Test
	public void test_02_getMap() {
		inventoryTest.getListFromFile();
		inventoryTest.setInventoryMap();
		inventoryTest.getCurrentInventory();
		Assert.assertEquals(inventoryTest.setInventoryMap(), inventoryTest.getCurrentInventory());
	}
}