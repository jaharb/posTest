package POSPD;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class SaleLineItem  
{

	private Sale sale;
	private Item item;
	private int quantity;
    
	/**
	 * Looks for and returns the sale.
	 * 
	 * @return sale
	 */
	public Sale getSale()
	{
		return this.sale;
	}
	
	/**
	 * Sets the sale.
	 * 
	 * @param sale
	 */
	public void setSale(Sale sale)
	{
		this.sale = sale;
	}
	
	/**
	 * Looks for and returns the Item that is associated with this sale.
	 * 
	 * @return item
	 */
	public Item getItem()
	{
		return this.item;
	}
	
    /**
     * Sets the Item that is associated with this Sale
     * 
     * @param item
     */
	public void setItem(Item item)
	{
		this.item = item;
	}
    
	/**
	 * Looks for and returns the quantity of the item in the sale.
	 * 
	 * @return quantity of the item
	 */
	public int getQuantity()
	{
		return this.quantity;
	}
    
	/**
	 * Sets the item quantity.
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	/**
	 * Default SaleLineItem constructor that creates an empty instance of SaleLineItem object. 
	 */
	public SaleLineItem()
	{
		
	}

	/**
	 * SaleLineItem constructor that creates an instance with the item in the sale, 
	 * quantity of the item, adds the item to the SaleLineItem, adds SaleLineItem to the sale and it sets the sale.
	 *  
	 * @param itemNumber
	 * @param quantity
	 */
	public SaleLineItem(Store store, Sale sale, String itemNumber, String quantity)
	{
		setQuantity(Integer.parseInt(quantity));
		setItem(store.findItemForNumber(itemNumber));
		sale.addSaleLineItem(this);
		getItem().addSaleLineItem(this);
		setSale(sale);
	}
	
	/**
	 * SaleLineItem constructor that creates an instance of the SaleLineItem with quantity (integer), item(Item).
	 * It adds the Item to the SaleLineItems and it adds SaleLineItem to the sale.
	 * 
	 * @param sale
	 * @param item
	 * @param quantity
	 */
	public SaleLineItem(Sale sale, Item item, int quantity)
	{
		setQuantity(quantity);
		setItem(item);
		sale.addSaleLineItem(this);
		getItem().addSaleLineItem(this);
		setSale(sale);
	}
	
    /**
     * Calculates the subtotal for the SaleLineItem. 
     * Looks for the quantity of the item and date of the sale and it calculates the subtotal.
     * 
     * @return subtotal
     */
	public BigDecimal calcSubTotal()
	{
		BigDecimal subtotal = getItem().calcTotal(getQuantity(), getSale().getDateTime());
		return(subtotal);
	}
	
    /**
     * 
     * @return
     */
	public BigDecimal calcTax()
	{
		BigDecimal amount = calcSubTotal();
		BigDecimal taxRate = getItem().getTaxRateForDate(getSale().getDateTime());
		BigDecimal tax = amount.multiply(taxRate).setScale(2, BigDecimal.ROUND_HALF_UP);
		return tax;
	}

	public String toString()
	{
		GregorianCalendar date = (GregorianCalendar) getSale().getDateTime();
		return getItem().getNumber()+" "+getItem().getDescription()+" "+getQuantity()+"@$"+getItem().getPriceForDate(date)+" $"+calcSubTotal().toString();
	}
}