package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class ShoppingCartTest {

	@Test
	public void test_01_purchaseItem() {
		ShoppingCart cartTest = new ShoppingCart();
		List<String> testList = new ArrayList<>();
		testList.add("Testy McTestFace");
		testList.add("Widget Spinner");
		
		cartTest.purchaseItem("Testy McTestFace");
		cartTest.purchaseItem("Widget Spinner");
		Assert.assertEquals(testList, cartTest.getPurchaseList());
	}
	
	@Test
	public void test_02_addToTotalSales() {
		ShoppingCart cartTest = new ShoppingCart();
		cartTest.addToTotalSales(BigDecimal.ONE);
		cartTest.addToTotalSales(BigDecimal.TEN);
		Assert.assertEquals(BigDecimal.valueOf(11.00).setScale(2), cartTest.getTotalSales());
	}

	@Test
	public void test_03_salesReport() throws FileNotFoundException {
		ShoppingCart cartTest = new ShoppingCart();
		cartTest.purchaseItem("Testy McTestFace");
		cartTest.purchaseItem("Widget Spinner");
		cartTest.purchaseItem("Testy McTestFace");
		
		String pattern = "MM-dd-yyyy 'at' hh mm a";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateTime = simpleDateFormat.format(new Date());
		File salesFile = new File("VM Sales Report for " + dateTime + ".txt");
		String cartTestString = "";
		
		cartTest.salesReport();
		Scanner cartTestScanner = new Scanner(salesFile);
		while (cartTestScanner.hasNextLine()) {
			cartTestString += cartTestScanner.nextLine() + "\n";
		}
		cartTestScanner.close();
		Assert.assertEquals("Widget Spinner|1\nTesty McTestFace|2\n\nTOTAL SALES: $0.00", cartTestString.trim());
	}
}
