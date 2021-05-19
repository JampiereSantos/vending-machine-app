package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

	// instance variables
	private List<String> purchasedItems;
	private Map<String, Integer> salesReport;
	private String pattern;
	private SimpleDateFormat simpleDateFormat;
	private String dateTime;
	private String salesPath;
	private File salesFile;
	private PrintWriter salesWriter;
	private BigDecimal totalSales;

	// constructor
	public ShoppingCart() {
		this.purchasedItems = new ArrayList<>();
		this.pattern = "MM-dd-yyyy 'at' hh mm a";
		this.simpleDateFormat = new SimpleDateFormat(pattern);
		this.dateTime = simpleDateFormat.format(new Date());
		this.salesPath = dateTime;
		this.salesFile = new File(salesPath);
		this.totalSales = BigDecimal.valueOf(0.00).setScale(2);
	}

	// getters for tests only, not used in CLI
	public List<String> getPurchaseList() {
		return purchasedItems;
	}
	
	public BigDecimal getTotalSales() {
		return totalSales;
	}
	
	// methods
	public void purchaseItem(String itemName) {
		purchasedItems.add(itemName);
	}
	
	public void addToTotalSales(BigDecimal price) {
		totalSales = totalSales.add(price);
	}
 
	public void salesReport() {
		this.salesReport = new HashMap<>();
		for (String item : purchasedItems) {
			Integer num = salesReport.get(item);
			if (num == null) {
				salesReport.put(item, 1);
			} else {
				salesReport.put(item, num + 1);
			}
		} try {
			this.salesWriter = new PrintWriter(new FileOutputStream("VM Sales Report for " + salesFile + ".txt"),true);
			for (Map.Entry<String, Integer> sales : salesReport.entrySet()) {
				salesWriter.println(sales.getKey() + "|" + sales.getValue());
			} salesWriter.println("\nTOTAL SALES: $" + totalSales.toString());
			salesWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!");
		} 
	}
}