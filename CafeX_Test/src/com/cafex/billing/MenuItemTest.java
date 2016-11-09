package com.cafex.billing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Will be used to test MenuItem
 * Main tests will be to be able to create a single item and then correctly get each bit of information from it.
 * We'll also want to be able to easiy output the details for the item in a String so will test this also
 * @author kieran.boparai
 *
 */
public class MenuItemTest {

	// single item we will use to test again
	private MenuItem cola1;
	@Before
	public void setUp() throws Exception {
		cola1 = new MenuItem("cola", MenuItem.MenuItems.COLD_DRINK, 0.5);
	}

	@Test
	public void testGetName(){
		String result = cola1.getItemName();
		if(!result.equals("cola")){
			fail("Incorrect item name has been returned");
		}
	}
	
	@Test
	public void testGetType(){
		MenuItem.MenuItems result = cola1.getItemType();
		if(result != MenuItem.MenuItems.COLD_DRINK){
			fail("Incorrect item type has been returned");
		}
	}
	
	@Test
	public void testGetPrice(){
		double result = cola1.getItemPrice();
		if(result != 0.5){
			fail("Incorrect item price has been returned");
		}
	}
	
	@Test
	public void testToString(){
		String result = cola1.toString();
		if(!result.equals("cola : COLD_DRINK : £0.50")){
			fail("String output for Menu Item is not correctly formatted");
		}
	}
}
