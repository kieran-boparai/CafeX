package com.cafex.billing;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Used to build an order. This will hold a Menu object and will allow you to pull items from it and place into an Order
 * ** Story 2 ** Functionality added to list the full order on request as well as price of each item and total cost (overide toString method)
 * ** Story 4 ** Functionality added to add a 10% Service Charge if food is ordered.
 * ** Story 5 ** Functionality added to add a 20% Service Charge if hot food is ordered.
 * @author kieran.boparai
 *
 */
public class CreateOrder {
	
	private Menu menu;
	private ArrayList<MenuItem> order;
	private boolean containsFood; // will be used to decide if service charge needs to be added.
	private double serviceChargeRate = 0.0; // Will be changed depending on if cold or hot food is added
	
	/**
	 * Initialise the menu object and the ArrayList that will hold the items for the order
	 */
	public CreateOrder(){
		menu= new Menu();
		order = new ArrayList<MenuItem>();
		containsFood = false;
	}
	
	/**
	 * Used to find MenuItem objects in Menu and then add them to order.
	 * If a matching MEnuItem is found then a message is displayed to confirm it has been added to the order
	 * If a matching MenuItem cannot be found a message will be displayed informing the user of this
	 * @param itemName - String - Item name for the MenuItem desired (as stored in the Menu)
	 * @return - String - Confirmation message to alert the user of the outcome
	 */
	public String addItemToOrder(String itemName){
		String output;
		MenuItem toAdd = menu.getItem(itemName);
		if(toAdd == null){
			output = itemName + " does not exist on the Menu. Please try again";
		} else {
			order.add(toAdd);
			output = toAdd.getItemName() + " has been added to the order";
			MenuItem.MenuItems toAddType = toAdd.getItemType();
			if(toAddType == MenuItem.MenuItems.HOT_FOOD){ // If this is Hot food then we apply 20% charge rate
				containsFood = true;
				serviceChargeRate = 0.2;
			} else if(toAddType == MenuItem.MenuItems.COLD_FOOD && serviceChargeRate != 0.2){ // This is cold food and we are not already applying the hot food charge
				containsFood = true;
				serviceChargeRate = 0.1;
			}
			
			if(containsFood){ // check if there is food in the order (regardless of if current item is food)
				addServiceCharge();
			}
		}
		return output;
	}
	
	/**
	 * Will be called if a food item is addded to the order.
	 * This will calculate the total bill, remove an old out of date service charge if present and then add a new one at 10% the total bill
	 */
	private void addServiceCharge(){
		double totalBill = 0.0;
		
		// Using Iterator instead of a standard For Each loop as we may be removing items during the loop
		Iterator<MenuItem> items= order.iterator();
		while(items.hasNext()){
			MenuItem item = items.next();
			if(item.getItemType()==MenuItem.MenuItems.SVCE_CHARGE){
				items.remove();
			} else {
				totalBill += item.getItemPrice();
			}
		}
		
		double serviceCharge = totalBill * serviceChargeRate;
		MenuItem svceCharge = new MenuItem("Service Charge", MenuItem.MenuItems.SVCE_CHARGE, serviceCharge);
		order.add(svceCharge);
	}
	
	/**
	 * The to String method has been overidden to provide a list of all items in the order as well as the price for each item
	 * and the total cost of the order.
	 */
	public String toString(){
		String output = "Full Order...\n";
		double totalCost = 0.0;
		for(MenuItem item : order){
			totalCost += item.getItemPrice();
			output += item.toString() + "\n";
		}
		
		output += "Total Order Cost = £" + String.format("%.2f", totalCost);
		return output;
	}
	
	
	// Main method will be used to run the CreateOrder class which will invoke other classes as required.
	public static void main(String[] args){
		CreateOrder order = new CreateOrder();
		System.out.println(order.addItemToOrder("cola"));
		System.out.println(order.addItemToOrder("cheese Sandwich"));
		System.out.println(order.addItemToOrder("coffee"));
		System.out.println(order.addItemToOrder("Steak Sandwich"));
		System.out.println(order.toString());
	}
}
