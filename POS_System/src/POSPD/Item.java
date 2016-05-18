package POSPD;

import java.math.*;
import java.util.*;

public class Item
{

	private String number;
	private String description;
	/**
	 * Association Type = POSPD.SaleLineItem
	 */
	private ArrayList<SaleLineItem> saleLineItems;
	/**
	 * Association Type = POSPD.UPC
	 */
	private TreeMap<String, UPC> upcs;
	/**
	 * Association Type = POSPD.Price
	 */
	private TreeSet<Price> prices;
	
	private TaxCategory taxCategory;
	
	/**
	 * Default Item constructor with no parameters. Creates an instance of Item with ArrayList of sale line items,
	 * TreeSet of prices and TreeMap of upcs.
	 */
	public Item()
	{
		saleLineItems = new ArrayList<SaleLineItem>();
		prices = new TreeSet<Price>();
		upcs = new TreeMap<String, UPC>();
	}

	/**
	 * Item constructor creates an instance of Item and it sets its number to passed number, description to passed
	 * description, taxCategory to taxCategory and it adds this Item to the store.
	 * 
	 * @param number
	 * @param description
	 * @param store
	 * @param taxCategory
	 */
	public Item(Store store, String number, String description, String taxCatagory)
	{
		this();
		setNumber(number);
		setDescription(description);
		setTaxCategory(store.findTaxCategoryForCategory(taxCatagory));
		store.addItem(this);
	}
	
	/**
	 * Looks for and return number of the Item.
	 * 
	 * @return item number
	 */

	public String getNumber()
	{
		return this.number;
	}
	
	/**
	 * Sets number of the item.
	 * 
	 * @param number
	 */

	public void setNumber(String number)
	{
		this.number = number;
	}
	
	/**
	 * Looks for and returns description (name) of the item.
	 * 
	 * @return item description (name)
	 */

	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * Sets description (name) of the item.
	 * 
	 * @param description
	 */

	public void setDescription(String description)
	{
		this.description = description;
	}
	/**
	 * Looks for and returns Array List of sale line items.
	 * 
	 * @return sale line items.
	 */

	public ArrayList<SaleLineItem> getSaleLineItems()
	{
		return this.saleLineItems;
	}
	/**
	 * Looks for and returns Tree Map with upcs for an item.
	 * 
	 * @return Tree Mapp of upcs.
	 */

	public TreeMap<String, UPC> getUpcs()
	{
		return this.upcs;
	}
    /**
     * Looks for and return Tree Set of prices for an item.
     * 
     * @return Tree Set of prices.
     */
	public TreeSet<Price> getPrices()
	{
		return this.prices;
	}

	/**
	 * Looks for and returns Tax Category for an item.
	 * 
	 * @return Tax Category.
	 */
	public TaxCategory getTaxCategory()
	{
		return this.taxCategory;
	}
	
	/**
	 * Sets Tax Category for an item.
	 * 
	 * @param taxCategory.
	 */

	public void setTaxCategory(TaxCategory taxCategory)
	{
		this.taxCategory = taxCategory;
	}
	
	/**
	 * Compares sale line to null. If it doesn't equal null, it adds it to saleLineItems.
	 * 
	 * @param sli
	 */
	public void addSaleLineItem (SaleLineItem sli)
	{
		if (sli != null)
		{
			getSaleLineItems().add(sli);
		}
	}
	
	/**
	 * Compares price to null. If it doesn't equal null, it adds it to TreeSet of prices.
	 * 
	 * @param price
	 */
	public void addPrice (Price price)
	{
		if (price != null)
		{
			getPrices().add(price);
		}
	}
	/**
	 * It compares price to null. If it doesn't equal null, it removes price from TreeSet of prices.
	 * 
	 * @param price
	 */
	public void removePrice (Price price)
	{
		if (price != null)
		{
			getPrices().remove(price);
		}
	}
	/**
	 * Compares upc to null. If it doesn't equal to null, it adds upc to TreeMap of upcs.
	 * 
	 * @param upc
	 */
	public void addUPC (UPC upc)
	{
		if (upc != null)
		{
			getUpcs().put(upc.getUpc(),upc);
		}
	}
	
	/**
	 * Compares upc to null. If it doesn't equal, it removes upc from TreeMap of upcs.
	 * 
	 * @param upc
	 */
	public void removeUPC (UPC upc)
	{
		if (upc != null)
		{
			getUpcs().remove(upc.getUpc());
		}
	}

	/**
	 * Sets current price to equal to null. It traverses through prices and compares each price
	 * with effective date. If price is effective, it sets current price to that price. If, after traversal
	 * current price still equals to null, it returns zero. Otherwise, it returns price.
	 * 
	 * @param date
	 * @return price for the item.
	 */
	public BigDecimal getPriceForDate(GregorianCalendar date)
	{
		
		Price currentPrice = null;
		for (Price p : prices)
		{ 
//			System.out.println("Price:"+p.toString() +" "+p.isInEffect(date));
			if (p.isInEffect(date))
			{ 
				currentPrice = p;
			}
		}
		if (currentPrice == null) return new BigDecimal("0"); 
				else return currentPrice.getPrice();
	}

	/**
	 * Looks and returns Tax rate for the specific date.
	 * 
	 * @param date
	 * @return Tax rate of the item for the specific date.
	 */
	public BigDecimal getTaxRateForDate(GregorianCalendar date)
	{
		return getTaxCategory().getTaxRate();
	}
	
	/**
	 * Calculates total price for number of same items for given date.
	 * 
	 * @param quantity
	 * @param date
	 * @return total price.
	 */
	public BigDecimal calcTotal(int quantity, GregorianCalendar date)
	{
		BigDecimal total;

		total = getPriceForDate(date).multiply(new BigDecimal(quantity));
		return(total);
	}
	
	/**
	 * Compares dates of when item was sold and the passed date. 
	 * If they equal it adds 1 to count for every item. 
	 * 
	 * @param date
	 * @return number of items sold for specific date.
	 */
	public int calcItemSoldCount(GregorianCalendar date)
	{
		int count =0;
	
		for (SaleLineItem sli : getSaleLineItems())
		{
			
			if (sli.getSale().getDateTime().get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
			sli.getSale().getDateTime().get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR))
			{
				count+= sli.getQuantity();
			}
		}
		return count;
	}
	
	/**
	 * Checks if item is used.
	 * 
	 * @return boolean value. True if it is not used, false if it is. 
	 */
	public boolean isUsed()
	{
		return getSaleLineItems().size() == 0;
	}

    /**
     * Converts Item to String format.
     * 
     * @return Item in the String format.
     */
	public String toString()
	{
		return getNumber()+" "
				+getDescription()+" "
				+getPriceForDate(new GregorianCalendar()).toPlainString()+" "
				+getTaxCategory().getTaxRate().toPlainString();
	}
}