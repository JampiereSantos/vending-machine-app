package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Inventory {

	// instance variables
	private String filePath;
	private File inventoryFile;
	private List<String[]> inventoryListFromFile;
	private Map<String, VendingMachineItem> inventoryMap;

	// constructor
	public Inventory() {
		this.filePath = "vendingmachine.csv";
		this.inventoryFile = new File(filePath);
		this.inventoryListFromFile = new ArrayList<>();
		this.inventoryMap = new LinkedHashMap<>();
	}

	// Getters and Setters
	
	// Getter that uses Capstone csv file to generate String[]s into a List
	public List<String[]> getListFromFile() {
		try (Scanner fileScanner = new Scanner(inventoryFile)) {
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] lineArray = line.split("\\|");
				inventoryListFromFile.add(lineArray);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (Exception ex) {
			System.out.println("A general exception has occured!");
			ex.printStackTrace();
		} return inventoryListFromFile;
	}
  
	// Setter that populates Map where key=slot and value=each item
	public Map<String, VendingMachineItem> setInventoryMap() {
		for (String[] thisArray : inventoryListFromFile) {
			if (thisArray[3].equalsIgnoreCase("CANDY")) {
				Candy thisCandy = new Candy(thisArray[1], thisArray[2]);
				inventoryMap.put(thisArray[0], thisCandy);
			} else if (thisArray[3].equalsIgnoreCase("CHIP")) {
				Chip thisChip = new Chip(thisArray[1], thisArray[2]);
				inventoryMap.put(thisArray[0], thisChip);
			} else if (thisArray[3].equalsIgnoreCase("DRINK")) {
				Drink thisDrink = new Drink(thisArray[1], thisArray[2]);
				inventoryMap.put(thisArray[0], thisDrink);
			} else if (thisArray[3].equalsIgnoreCase("GUM")) {
				Gum thisGum = new Gum(thisArray[1], thisArray[2]);
				inventoryMap.put(thisArray[0], thisGum);
			}
		} return inventoryMap;
	}

	// Getter that allows access to current inventory (price, quantity)
	public Map<String, VendingMachineItem> getCurrentInventory(){
		return inventoryMap;
	}
	
	// Methods
	
	// Prints current inventory to console 
	public void displayItems() {
		Set<String> keys = inventoryMap.keySet();
		System.out.println();
		for (String key : keys) {
			System.out.println(key.toString() + ") " + inventoryMap.get(key).getName() + " $"
			+ inventoryMap.get(key).getPrice() + " Quantity: " + String.valueOf(inventoryMap.get(key).getQuantity()));
		} 
	}
}
