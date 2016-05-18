package POSHI;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import POSPD.*;


public class POSStart {
	public static Store myStore;
	
	/**
	 * Main prints out name of the store, creates new instance of Store(), 
	 * and runs store in POSJFrame.
	 *  
	 * @param args
	 */
	public static void main(String[] args) 
	{

//			myStore = new Store();
//			System.out.println("Ready to open Store");
//			myStore.openStore();
//			printStore();
//			System.out.println("Store Open:"+myStore.getName() );


		myStore = new Store();
		myStore.openStore();
		POSJFrame.run(myStore);

	}

	/**
	 * printStore() prints out the data from the file. It prints all of the cashiers,
	 * registers, items and sessions.
	 */
	public static void printStore()
	{
		System.out.println("==============");
		System.out.println("Cashiers");
		System.out.println("==============");
		for (Entry<String, Cashier> entry : myStore.getCashiers().entrySet()) 
		{
	        System.out.println(entry.getValue().getPerson().getName());
	        
		}
		System.out.println("==============");
		System.out.println("Registers");
		System.out.println("==============");
		for (Entry<String, Register> entry : myStore.getRegisters().entrySet()) 
		{
	        System.out.println(entry.getValue().getNumber());
	        
		}
		System.out.println("==============");
		System.out.println("Items");
		System.out.println("==============");
		for (Entry<String, Item> entry : myStore.getItems().entrySet()) 
		{
	        System.out.println(entry.getValue().toString());
		}
		System.out.println("==============");
		System.out.println("Sessions");
		System.out.println("==============");
		for (Session session : myStore.getSessions())
	
		{
	        System.out.println(session.toString());
		}
	}
}


