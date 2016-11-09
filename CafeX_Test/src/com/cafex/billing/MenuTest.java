package com.cafex.billing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Will be used to test Menu. The main tests will be to ensure that a MenuItem objects can be added using a predefined setup method
 * and then the correct details can be pulled out.
 * @author kieran.boparai
 *
 */
public class MenuTest {

	private Menu menu;
	
	@Before
	public void setUp() throws Exception {
		menu = new Menu();
	}
	
	@Test
	public void testSetupMenu(){
		// Menu setup is private and was called by the Constructor on initialisation of the Menu object
		// There are currently only 4 items on the menu but can be changed later
		if(menu.getMenuSize() != 4){
			fail("Menu was not setup correctly, or the MEnu size is not being reported correctly");
		}	
	}
	
	@Test
	public void testGetItem(){
		// Will try to get the item Coffee by name
		MenuItem coffee1 = menu.getItem("coffee");
		if(!coffee1.getItemName().equals("Coffee")){
			fail("Item has not been retrieved from the Menu correctly");
		}
		
		//getItem search should be case insensitive and should remove spaces also (to account for human error)
		MenuItem cheeseSandwich = menu.getItem("ChEeSe SANDWICH");
		if(!cheeseSandwich.getItemName().equals("Cheese Sandwich")){
			fail("Item has not been retrieved from the Menu correctly");
		}
		
		MenuItem steakSandwich = menu.getItem("Steak       SANDWICH     ");
		if(!steakSandwich.getItemName().equals("Steak Sandwich")){
			fail("Item has not been retrieved from the Menu correctly");
		}
	}
}
