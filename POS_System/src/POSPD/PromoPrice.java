package POSPD;


import java.math.BigDecimal;
import java.util.*;

public class PromoPrice extends Price
{

	private Calendar endDate;
    
	/**
	 * Default PromoPrice constructor that creates empty instance of the PromoPrice object.
	 */
	public PromoPrice()
	{
		
	}
	
	/**
	 * Price constructor that creates an instance of the PromoPrice with Item that is associated with this promo price,
	 * price, effective date (date that this price starts being effective on) and end date(date when this price stops 
	 * being effective on).
	 * 
	 * @param price
	 * @param effectiveDate
	 * @param endDate
	 */
	public PromoPrice(Item item, String price, String effectiveDate, String endDate)
	{
		setItem(item);
		setPrice(new BigDecimal(price));
		String[] ed;
		ed = effectiveDate.split("/");;
		setEffectiveDate(new GregorianCalendar(Integer.parseInt(ed[2])+2000,Integer.parseInt(ed[0])-1,Integer.parseInt(ed[1])));
		ed = endDate.split("/");
		setEndDate(new GregorianCalendar(Integer.parseInt(ed[2])+2000,Integer.parseInt(ed[0])-1,Integer.parseInt(ed[1])));
	}
	
	/**
	 * Looks for and returns the end date of the promo price.
	 * 
	 * @return end date
	 */
	public Calendar getEndDate()
	{
		return this.endDate;
	}
    /**
     * Sets the end date of the promo price.
     * 
     * @param endDate
     */
	public void setEndDate(Calendar endDate)
	{
		this.endDate = endDate;
	}
    
	public boolean isInEffect(GregorianCalendar date)
	{
		if ((getEffectiveDate().compareTo(date) <= 0) && (getEndDate().compareTo(date) >= 0)) return true; else return false;
	}
	
	/**
	 * Returns the string type of the promo price instance.
	 */
	public String toString()
	   {
		   return getPrice().toPlainString()+" " +getEffectiveDate().getTime().toString()+"-"+getEndDate().getTime().toString();
	   }

}