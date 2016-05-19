package POSDM;

import POSPD.*;

import java.io.*;

public class StoreDM {
	
	// This should be in first commit
	// This is in second commit
	// third commit
	// fourth commit


/**
 * loadStore(store) looks for data in the file with given file name. It creates new
 * session currentSession with sale currentSale. 
 * 
 * @param store
 */
public static void loadStore(Store store)
{
	
	String fileName ="StoreData.csv";
	String line = null;
	String[] token;
	String dataType; 
	Session currentSession = null;
	Sale currentSale = null;

	
    try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);
        //while there is data in file do this
        while((line = bufferedReader.readLine()) != null) 
        {
        		//split data by comma
	        	token = line.split(",");
	        	dataType = token[0];
	        	/**
	        	 * Determined by data type of the token, variables are set. For 
	        	 * example if it is "Store", it has to be name of the store. However, if it 
	        	 * is "Cashier", it is all info about the current cashier and so on. 
	        	 */
	        	if (dataType.equals("Store"))
	        	{
	        		store.setName(token[1]);
	        	}
	        	else if (dataType.equals("Cashier"))
	        	{
	        	
	        		Person storePerson = new Person(token[2], token[3], token[4], token[5], token[6], token[7], token[8]);
	        		Cashier storeCashier = new Cashier(token[1],storePerson,token[9]);
	        		storePerson.setCashier(storeCashier);
	        		store.addCashier(storeCashier);
	        				
	        	}
	        	else if (dataType.equals("TaxCategory"))
	        		
	        	{
	        		TaxCategory storeTaxCategory = new TaxCategory(token[1],token[3],token[2]);
	        		store.addTaxCategory(storeTaxCategory);
	        	}
	        	else if (dataType.equals("Item"))
	        	{
	        		Item storeItem = new Item(store,token[1],token[3], token[4]);
	        		Price itemPrice = new Price(storeItem, token[5], token[6]);
	        		UPC storeUPC = new UPC(token[2],storeItem);
	        		storeItem.addUPC(storeUPC);
	        		storeItem.addPrice(itemPrice);
	        		store.addUPC(storeUPC);
	        		if (token.length > 7) 
	        			{ 
	        				PromoPrice promoPrice = new PromoPrice(storeItem, token[7],token[8],token[9]);
	        				storeItem.addPrice(promoPrice);
	        			}
	        	}
	        	else if (dataType.equals("Register"))
	        		
	        	{
	        		new Register(store,token[1]);
	        		
	        	}
	        	else if (dataType.equals("Session"))
	        		
	        	{
	        		currentSession = new Session(store,token[1],token[2]);
	        	}
	        	else if (dataType.equals("Sale"))
	        		
	        	{
	        		currentSale = new Sale(currentSession,token[1]);
	        	}
	        	else if (dataType.equals("SaleLineItem"))
	        		
	        	{
	        		new SaleLineItem(store, currentSale,token[1],token[2]);

	        	}
	        	else if (dataType.equals("Payment"))
	        		
	        	{
	        		if (token[1].equals("Cash") )
    				{
	        			new Cash(currentSale,token[2],token[3]);
    				}
	        		if (token[1].equals("Check") )
    				{
	        			new Check(currentSale, token[2], token[4], token[5], token[3]);
    				}
	        		if (token[1].equals("Credit") )
    				{

	        			new Credit(currentSale, token[2],token[4], token[5], token[6]);
    				}
	        	}
        }    

        // Always close files.
        bufferedReader.close();            
    }
    catch(FileNotFoundException ex) 
    {
        System.out.println(
            "Unable to open file '" + 
            fileName + "'" + " at cur dir: " + System.getProperty("user.dir"));                
    }
    catch(IOException ex) {
        System.out.println(
            "Error reading file '" 
            + fileName + "'");   	
	
	}
}


}


