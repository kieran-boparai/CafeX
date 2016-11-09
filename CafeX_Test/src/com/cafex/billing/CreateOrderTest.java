package com.cafex.billing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Will be used to test the CreateOrder functionality. The current set of tests will include:
 * 	Adding a single item to the order and confirming it is there.
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
		// so far the order contains 1 cola, we will add a coffee and then call the toString
		order.addItemToOrder("coffee");
		String fullOrder = order.toString();
		if(fullOrder.equals("Full Order Details...\nCola : COLD_DRINK : £0.50\nCoffee : HOT_DRINK : £1.00\nTotal Order Cost = £1.50")){
			fail("Order was not summarised/the total was not correctly calculated");
		}
	}
	
	@Test
	public void testServiceCharge(){
		// Need to check that when a Food item has been added to the menu that a Service Charge of 10% of the total bill will be applied
		// So far no food has been added so there should be no service charge
		String fullOrder = order.toString();
		if(fullOrder.equals("Full Order Details...\nCola : COLD_DRINK : £0.50\nCoffee : HOT_DRINK : £1.00\nTotal Order Cost = £1.50")){
			fail("Order was not summarised/the total was not correctly calculated");
		}
		
		// now we will add a food item and check again
		order.addItemToOrder("Cheese Sandwich");
		if(fullOrder.equals("Full Order Details...\nCola : COLD_DRINK : £0.50\nCoffee : HOT_DRINK : £1.00\n"
				+ "Cheese Sandwich : COLD_FOOD : £2.00\nService Charge : SVCE_CHARGE : £0.35\nTotal Order Cost = £3.85")){
			fail("Order was not summarised/the total was not correctly calculated");
		}
	}
}
