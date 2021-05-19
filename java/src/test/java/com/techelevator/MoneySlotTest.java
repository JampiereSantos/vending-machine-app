package com.techelevator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneySlotTest {

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
	public void test_01_displayCurrentBalance() {
		MoneySlot moneySlotTest = new MoneySlot();
		moneySlotTest.feedMoney(BigDecimal.TEN);
		moneySlotTest.displayCurrentBalance();
		Assert.assertEquals("Current Money Provided: $10.00", testStream.toString().trim());
	}
	
	@Test
	public void test_02_feedMoney() {
		MoneySlot moneySlotTest = new MoneySlot();
		moneySlotTest.feedMoney(BigDecimal.ONE.setScale(2));
		Assert.assertEquals(moneySlotTest.getBalance(), BigDecimal.ONE.setScale(2));
	}
	
	@Test
	public void test_03_spendMoney() {
		MoneySlot moneySlotTest = new MoneySlot();
		moneySlotTest.feedMoney(BigDecimal.TEN.setScale(2));
		moneySlotTest.spendMoney(BigDecimal.TEN.setScale(2));
		Assert.assertEquals(moneySlotTest.getBalance(), BigDecimal.ZERO.setScale(2));
	}
	
	@Test
	public void test_04_checkPurchase() {
		MoneySlot moneySlotTest = new MoneySlot();
		
		// positive value
		moneySlotTest.feedMoney(BigDecimal.TEN.setScale(2));
		moneySlotTest.checkPurchase(BigDecimal.ONE.setScale(2));
		Assert.assertEquals(BigDecimal.valueOf(9).setScale(2), moneySlotTest.checkPurchase(BigDecimal.ONE.setScale(2)));
		
		// negative value
		moneySlotTest.spendMoney(BigDecimal.ONE.setScale(2));
		moneySlotTest.checkPurchase(BigDecimal.TEN.setScale(2));
		Assert.assertEquals(BigDecimal.valueOf(-1).setScale(2), moneySlotTest.checkPurchase(BigDecimal.TEN.setScale(2)));
	}
	
	@Test
	public void test_05_giveChange0() {
		MoneySlot moneySlotTest = new MoneySlot();

		moneySlotTest.giveChange();
		Assert.assertEquals("Your change is 0 quarter(s), 0 dime(s), and 0 nickel(s).", testStream.toString().trim());
	}
	
	@Test
	public void test_06_giveChangeAllCoinsWith140() {
		MoneySlot moneySlotTest = new MoneySlot();
		moneySlotTest.feedMoney(BigDecimal.valueOf(1.40).setScale(2));
		moneySlotTest.giveChange();
		Assert.assertEquals("Your change is 5 quarter(s), 1 dime(s), and 1 nickel(s).", testStream.toString().trim());
	}
}
