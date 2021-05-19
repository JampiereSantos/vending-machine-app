package com.techelevator;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "SUPER SECRET SALES REPORT!";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private static final String ONE_DOLLAR = "$1";
	private static final String TWO_DOLLAR = "$2";
	private static final String FIVE_DOLLAR = "$5";
	private static final String TEN_DOLLAR = "$10";
	private static final String FEED_MONEY_DONE = "Finished";
	private static final String[] FEED_MONEY_MENU_OPTIONS = {ONE_DOLLAR, TWO_DOLLAR, FIVE_DOLLAR, TEN_DOLLAR, FEED_MONEY_DONE};

	private Menu menu; 
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;}

	private Inventory inventory = new Inventory();
	private MoneySlot moneySlot = new MoneySlot();
	private Logger logger = new Logger();
	private VMExceptions VME = new VMExceptions();
	private ShoppingCart shoppingCart = new ShoppingCart();

	public static void main(String[] args){
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();}

	public void run(){
		inventory.getListFromFile();  		// Reads file and generates Inventory List
		inventory.setInventoryMap(); 		// Stocks machine at startup
		System.out.println("Welcome to the Vendo-Matic 800!");
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				inventory.displayItems(); 	
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				boolean purchase = true;
				while (purchase) {
					moneySlot.displayCurrentBalance();
					String choicePurchase = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (choicePurchase.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						boolean feed = true;
						while (feed) {
							moneySlot.displayCurrentBalance();
							String choiceFeedMoney = (String) menu.getChoiceFromOptions(FEED_MONEY_MENU_OPTIONS);
	/*  Feed Money	*/		if (choiceFeedMoney.equals(ONE_DOLLAR)) {
								logger.log("FEED MONEY:", moneySlot.getBalance().toString(), moneySlot.getBalance().add(BigDecimal.valueOf(1.00)).toString());
								moneySlot.feedMoney(BigDecimal.valueOf(1.00));
							} else if (choiceFeedMoney.equals(TWO_DOLLAR)) { 
								logger.log("FEED MONEY:", moneySlot.getBalance().toString(), moneySlot.getBalance().add(BigDecimal.valueOf(2.00)).toString());
								moneySlot.feedMoney(BigDecimal.valueOf(2.00));
							} else if (choiceFeedMoney.equals(FIVE_DOLLAR)) {
								logger.log("FEED MONEY:", moneySlot.getBalance().toString(), moneySlot.getBalance().add(BigDecimal.valueOf(5.00)).toString());
								moneySlot.feedMoney(BigDecimal.valueOf(5.00));
							} else if (choiceFeedMoney.equals(TEN_DOLLAR)) {
								logger.log("FEED MONEY:", moneySlot.getBalance().toString(), moneySlot.getBalance().add(BigDecimal.valueOf(10.00)).toString());
								moneySlot.feedMoney(BigDecimal.valueOf(10.00));
							} else if (choiceFeedMoney.equals(FEED_MONEY_DONE)) {
								feed = false; }
						}
					} else if (choicePurchase.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						inventory.displayItems();	// Displays items for purchase
						moneySlot.displayCurrentBalance();
						Scanner purchaseScanner = new Scanner(System.in);
						System.out.print("Please choose a slot >>> ");
						String slotString = purchaseScanner.nextLine();
						boolean validSlot = false;
						for (Map.Entry<String, VendingMachineItem> entry : inventory.getCurrentInventory().entrySet()) {
/*  Checks input  */		if ((slotString.equalsIgnoreCase(entry.getKey()))) {
								validSlot = true; 
							}
						} if (validSlot == true) {
							for (Map.Entry<String, VendingMachineItem> entry : inventory.getCurrentInventory().entrySet()) {
								if (slotString.equalsIgnoreCase(entry.getKey())) {
/*  Checks if SOLD OUT  */			if (entry.getValue().getQuantity().equals("SOLD OUT")) {
										VME.soldOutException();
									} else {
										BigDecimal currentPriceFromString = new BigDecimal(entry.getValue().getPrice());
/*  Checks for sufficient funds  */		if (moneySlot.checkPurchase(currentPriceFromString).compareTo(BigDecimal.ZERO) < 0 ) {
											VME.insufficientFundsException();
										} else {
/*  Sells item and updates all  */			logger.log(entry.getValue().getName() + " " + slotString.toUpperCase(), moneySlot.getBalance().toString(), moneySlot.getBalance().subtract(currentPriceFromString).toString());
											moneySlot.spendMoney(currentPriceFromString);
											shoppingCart.purchaseItem(entry.getValue().getName());
											shoppingCart.addToTotalSales(currentPriceFromString);
											entry.getValue().dispense();
											entry.getValue().reduceQuantity();
										}
									}
								}	
							}
						}
						if (validSlot == false) {
							System.out.println("\n" + "*** " + slotString + " is not a valid option ***");
						}
	/*  Gives  */	} else if (choicePurchase.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
	/*  Change */		System.out.println("\n" + "Calculating change for $" + ((moneySlot.getBalance())).setScale(2) + " ..."+"\n");
						logger.log("GIVE CHANGE:", moneySlot.getBalance().toString(), "0.00");
						moneySlot.giveChange();
						moneySlot.displayCurrentBalance();
						purchase = false;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(1);
			} else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
				shoppingCart.salesReport();
			}
		}
	}
}