package com.techelevator;

public abstract class VendingMachineItem {

	// private members
	private String name;
	private String price;
	
	// constructor
	public VendingMachineItem(String name, String price) {
		this.name = name;
		this.price = price;
	}

	// Getters
	public String getName() {
		return name;
	}
 
	public String getPrice() {
		return price;
	}	

	// Abstract Methods
	public abstract void dispense();
	
	public abstract String getQuantity();
	
	public abstract void setQuantity(String quantity);
	
	public abstract void reduceQuantity();
} 