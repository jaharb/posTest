package POSPD;

import java.math.*;
import java.util.*;

public class Price implements Comparator<Price>, Comparable<Price>
{

	private BigDecimal price;
	private Calendar effectiveDate;
	private Item item;
	
	/**
	 * Default price constructor that creates empty instance of the Price.
	 */
	public Price()
	{

	}

	/**
	 * Price constructor that creates an instance of Price with item that is associated with the price,
	 * price, and the effective date of the price. 
	 * 
	 * @param item
	 * @param price
	 * @param effectiveDate
	 */
	public Price(Item item, String price, String effectiveDate)
	{
		setItem(item);
		setPrice(new BigDecimal(price));
		String[] ed;
		ed = effectiveDate.split("/");
		setEffectiveDate(new GregorianCalendar(Integer.parseInt(ed[2])+2000,Integer.parseInt(ed[0])-1,Integer.parseInt(ed[1])));

	}
	
    /**
     * Looks for and returns the price of the item.
     * 
     * @return price
     */
	public BigDecimal getPrice()
	{
		return this.price;
	}
	 
    /**
     * Sets the price of the item.
     * 
     * @param price
     */
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	
	/**
	 * Looks for and return the effective date of the price.
	 * 
	 * @return effective date
	 */
	public Calendar getEffectiveDate() {
		return effectiveDate;
	}
    
	/**
	 * Sets the effective date of the price
	 * 
	 * @param effectiveDate
	 */
	public void setEffectiveDate(Calendar effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
    /**
     * Looks for and returns the item that is associated with the price.
     *  
     * @return item
     */
	public Item getItem() {
		return item;
	}
	
    /**
     * Sets the item that is associated with the price.
     * 
     * @param item
     */
	public void setItem(Item item) {
		this.item = item;
	}
	
	/**
	 * Looks if the price is effective to the passed date. It compares the effective date of the price with 
	 * passed date. If the effective date is in the range passed date-today, than it returns true, otherwise false.
	 * 
	 * @param date
	 * @return true if the price is effective
	 */
	public boolean isInEffect(GregorianCalendar date)
	// compare to will be < 0 if A < B, > 0 if A > B, and == 0 if they are equal
	{
		if (getEffectiveDate().compareTo(date) <= 0) return true; else return false;
	}
	
	/**
	 * 
	 */
	// Overriding the compareTo method
	   public int compareTo(Price price){
	      return getEffectiveDate().compareTo(price.getEffectiveDate());
	   }
       /**
        *   
        */
	   // Overriding the compare method  
	   public int compare(Price p1, Price p2){
	      return (int) (p1.getEffectiveDate().getTimeInMillis()- p2.getEffectiveDate().getTimeInMillis()) ;
	     
	   }
	   
	   /**
	    * Returns the string of the price.
	    */
	   public String toString()
	   {
		   return getPrice().toPlainString()+" " +getEffectiveDate().getTime().toString();
	   }
	
}