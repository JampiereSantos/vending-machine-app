package com.techelevator;

public class Gum extends VendingMachineItem{

	// instance variables
	private final String DISPENSE_SOUND;
	private String quantity;

	// constructor
	public Gum(String name, String price) {
		super(name, price);
		this.DISPENSE_SOUND = "Chew Chew, Yum!";
		this.quantity = "5";
	}

	// Methods 

	@Override
	public void dispense() {
		System.out.println("\n" + "You have purchased: " + getName() + " for $" + getPrice() + "\n" + DISPENSE_SOUND);
	}

	@Override
	public String getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void reduceQuantity() {
		if (quantity.equalsIgnoreCase("SOLD OUT")) {
			//nothing happens
		}
		else if (!(quantity.equalsIgnoreCase("SOLD OUT"))) {
			int quantityAsInt = Integer.parseInt(getQuantity());
			if(quantityAsInt > 1) {
				quantityAsInt--;
				String newQuantity = String.valueOf(quantityAsInt);
				setQuantity(newQuantity);
			} else if(quantityAsInt == 1) {
				setQuantity("SOLD OUT");
			}
		} 
	}
}
