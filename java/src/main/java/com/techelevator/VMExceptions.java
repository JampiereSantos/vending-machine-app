package com.techelevator;

@SuppressWarnings("serial")
public class VMExceptions extends Exception{

	public VMExceptions() {
	}
	
	public void soldOutException() {
		System.out.println("\n" + "Sorry, we are SOLD OUT of that item!");
	}
	
	public void insufficientFundsException() {
		System.out.println("\n" + "Sorry, you do not have enough money! Feed me money!");
	}
	
	public void generalVendoMaticException() {
		System.out.println("Sorry, Vendo-Matic 800 is not working at the moment, please start over.");
	}
}