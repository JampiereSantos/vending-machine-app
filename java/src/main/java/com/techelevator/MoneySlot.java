package com.techelevator;

import java.math.BigDecimal;

public class MoneySlot {

	// Instance Variable
	private BigDecimal balance;
 
	// Constructor
	public MoneySlot() {
		this.balance = BigDecimal.valueOf(0.00).setScale(2);
	}

	// Getter
	
	public BigDecimal getBalance() {
		return balance;
	} 

	// Methods
	
	public void displayCurrentBalance() {
		System.out.println("\n" + "Current Money Provided: $" + ((getBalance())).setScale(2));
	}
	
	public void feedMoney(BigDecimal money) {
		balance = balance.add(money);
		//balance += money;
	}

	public void spendMoney(BigDecimal money) {
		balance = balance.subtract(money);
	}

	public BigDecimal checkPurchase(BigDecimal money) {
		BigDecimal checkingMoney = balance.subtract(money);
		return checkingMoney;
	}

	public void giveChange() {
		BigDecimal hundredBD = new BigDecimal(100);
		BigDecimal currentBalance100 = getBalance().multiply(hundredBD);
		
		int currentBalanceInInt = currentBalance100.intValue();
		
		int numOfQuarters = 0;
		int numOfDimes = 0;
		int numOfNickels = 0;
 
		while (currentBalanceInInt > 0) {
			if (currentBalanceInInt >= 25) {
				numOfQuarters++;
				currentBalanceInInt -= 25;
			} else if (currentBalanceInInt < 25 && currentBalanceInInt >= 10) {
				numOfDimes++;
				currentBalanceInInt -= 10;
			} else if (currentBalanceInInt < 10) {
				numOfNickels++;
				currentBalanceInInt -= 5;
			}
		}
		System.out.println( "Your change is " + numOfQuarters + " quarter(s), " + 
				numOfDimes + " dime(s), and " + numOfNickels + " nickel(s).");
		this.balance = BigDecimal.valueOf(0.00).setScale(2);
	}
}
