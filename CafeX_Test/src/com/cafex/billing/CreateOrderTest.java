package com.cafex.billing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Will be used to test the CreateOrder functionality. The current set of tests will include:
 * 	Adding a single item to the order and confirming it is there.
 * 	Ensuring the correct Service Charge is added
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
	}
}
