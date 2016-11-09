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
}
