package com.cafex.billing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Will be used to test the CreateOrder functionality. The current set of tests will include:
 * 	Adding a single item to the order and confirming it is there.
 * 	Ensuring the correct Service Charge is added
 *  Ensure Service Charge is correctly limited given the scenario
 * @author kieran.boparai
 *
 */
public class CreateOrderTest {

	private CreateOrder order;
	
	@Before
	public void setUp() throws Exception {
		order = new CreateOrder();
	}

	@Test
	public void testAddItem(){
		// as per the Menu class the name provided will be case insensitive and can contain spaces
		// If an item is successfully added to the order a message should be displayed to report this
		// IF the item does not exist then a message should be displayed to report this
		String cola1 = order.addItemToOrder("ColA   ");
		if(!cola1.equals("Cola has been added to the order")){
			fail("Item was not added to order correctly or reporting has failed");
		}
		
		String coffee1 = order.addItemToOrder("SOMETHINGELSE");
		if(!coffee1.equals("SOMETHINGELSE does not exist on the Menu. Please try again")){
			fail("Filtering for invalid items or reporting has failed");
		}
	}
	
	@Test
	public void testToString(){
		// an overriden toString will be used to output the contents of the order and total cost
		// we will add a coffee and then call the toString
		order.addItemToOrder("coffee");
		String fullOrder = order.toString();
		if(!fullOrder.contains("Total Order Cost = £1.00")){
			fail("Order was not summarised/the total was not correctly calculated");
		}
	}
	
	@Test
	public void testServiceCharge(){
		// Need to check that when a Food item has been added to the menu that a Service Charge of 10% of the total bill will be applied
		// So far no food has been added so there should be no service charge
		String fullOrder = order.toString();
		if(fullOrder.contains("Service Charge")){
			fail("1 - Service Charge incorrectly added");
		}
		
		// now we will add a food item and check again
		order.addItemToOrder("Cheese Sandwich");
		fullOrder = order.toString();
		if(!fullOrder.contains("Service Charge : SVCE_CHARGE : £0.20")){
			fail("2 - Service Charge not correctly added");
		}
		
		// add another drink and check that service charge is correctly recalculated
		order.addItemToOrder("coffee"); // total bill is now 2+1 = £3.00 - Service Charge is £0.30
		fullOrder = order.toString();
		if(!fullOrder.contains("Service Charge : SVCE_CHARGE : £0.30")){
			fail("3 - Service Charge was not recalculated correctly");
		}
		
		// So far order only contains Cold Food and service charge = 10%
		// now add Hot Food and service charge should be 20%
		order.addItemToOrder("steak sandwich");
		fullOrder = order.toString();
		if(!fullOrder.contains("Service Charge : SVCE_CHARGE : £1.50")){ //4.50+2+1= 7.5 * 0.2 = 1.50
			fail("4 - Incorrect Service Charge applied to order");
		}
		
		// Will no test rounding by adding the Test Item created in Menu which has a price of 1.597
		order.addItemToOrder("test item");
		fullOrder = order.toString();
		// new Service Charge should be (4.5+2+1+1.597= 9.097 * 0.2 = 1.8194 - rounded to 1.82
		if(!fullOrder.contains("Service Charge : SVCE_CHARGE : £1.82")){
			fail("5 - Service Charge rounding has failed");
		}
	}
	
	@Test
	public void testServiceChargeLimit(){
		// Create an order with high value of Cold Food Items
		for(int i = 0; i < 200; i++){
			order.addItemToOrder("CheeseSandwich");
		}// total value = 2*200 = 400 * 0.1 = £40 serviceCharge (Will not be limited to £20)
		String fullOrder = order.toString();
		if(!fullOrder.contains("Service Charge : SVCE_CHARGE : £40.00")){
			fail("1 - Service Charge limiting should not be applied here");
		}
		
		// add a single Hot food item
		order.addItemToOrder("SteakSandwich"); // total value = 2*200 + 4.50 = 404.50 * 0.2 = £80.90 serviceCharge (Limited to £20)
		fullOrder = order.toString();
		if(!fullOrder.contains("Service Charge : SVCE_CHARGE : £20.00")){
			fail("2 - Service Charge limiting should have been applied here");
		}
		
	}
}
