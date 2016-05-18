package POSPD;


import java.math.*;
import java.util.*;


public class Sale  
{

	/**
	 * Association Type = POSPD.Payment
	 */
	private List<Payment> payments;
	/**
	 * Association Type = POSPD.SaleLineItem
	 */
	private List<SaleLineItem> saleLineItems;
	private GregorianCalendar dateTime;
	private Boolean taxFree;
	private Session session;
     
	/**
	 * Default Sale constructor that creates an instance of the Sale object with set date and time, with ArrayList 
	 * of SaleLineItems, ArrayList of Payments and it sets taxFree to false.
	 */
	public Sale()
	{
		setDateTime(new GregorianCalendar());
		saleLineItems = new ArrayList<SaleLineItem>();
		payments = new ArrayList<Payment>();
		setTaxFree(false);
	}

	/**
	 * Sale constructor that creates an instance of Sale, sets the TaxFree, 
	 * sets the Date time and adds sale to the session.
	 * 
	 * @param dateTime
	 * @param taxFree
	 */
	public Sale(Session session, String taxFree)
	{
		this();
		if (taxFree.equals("N")) setTaxFree(true); else setTaxFree(false);
		setDateTime(new GregorianCalendar());
		session.addSale(this);
		setSession(session);
	}
	
    /**
     * Looks for and returns the List of the payments for the sale.
     * 
     * @return payments
     */
	public List<Payment> getPayments()
	{
		return this.payments;
	}
    
	/**
	 * Looks for and returns List of the sale line items.
	 * 
	 * @return sale line items.
	 */
	public List<SaleLineItem> getSaleLineItems()
	{
		return this.saleLineItems;
	}
	
    /**
     * Looks for and returns the date and time of the sale.
     * 
     * @return dateTime
     */
	public GregorianCalendar getDateTime()
	{
		return this.dateTime;
	}
	
    /**
     * Sets the date and time for the sale. 
     */
	public void setDateTime(GregorianCalendar dateTime)
	{
		this.dateTime = dateTime;
	}
	
    /**
     * Returns if the sale is tax free or not.
     * 
     * @return true if sale is tax free, false if it is not.
     */
	public Boolean getTaxFree()
	{
		return this.taxFree;
	}
	
    /**
     * Sets the tax free for the sale. 
     * 
     * @param taxFree
     */
	public void setTaxFree(Boolean taxFree)
	{
		this.taxFree = taxFree;
	}
	
	
	/**
	 * Calculates the total for the sale. Returns the big decimal of total to pay.
	 * 
	 * @return total.
	 */
	public BigDecimal calcTotal()
	{
		return calcSubTotal().add(calcTax());
	}
 
	/**
	 * Calculates the subtotal for the sale.
	 * It adds price of every sale line item in the sale to the subtotal.
	 * 
	 * @return big decimal of subtotal.
	 */
	public BigDecimal calcSubTotal()
	{
		BigDecimal subTotal = new BigDecimal ("0");
		for (SaleLineItem sli : saleLineItems)
		{ subTotal = subTotal.add(sli.calcSubTotal());}
		return subTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Calculates the change to be returned to customer.
	 * It subtracts total from the amount tendered.
	 * 
	 * @return change
	 */
	public BigDecimal calcChange()
	{
		return calcAmtTendered().subtract(calcTotal());
	}

	/**
	 * Calculates the tax for the sale.
	 * Looks if the sale is tax free and if it is not, it calculates the tax for each sale line item for the sale.
	 * 
	 * @return tax amount
	 */
	public BigDecimal calcTax()
	{
		BigDecimal tax = new BigDecimal ("0");
		if (!getTaxFree() )
		{
			for (SaleLineItem sli : saleLineItems)
			{ tax = tax.add(sli.calcTax());}
		}
		return tax.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Calculates the amount 
	 * @param amountTendered
	 * @return
	 */
	public BigDecimal calcAmount(BigDecimal amountTendered)
	{
		BigDecimal calcAmount = calcTotal().subtract(calcTotalPayment());
		if (calcAmount.compareTo(amountTendered) > 0)
		{
			calcAmount = amountTendered;
		}
		return calcAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Calculates total payment for the sale. 
	 * It adds every payment of the sale to the total amount.
	 * 
	 * @return total payments
	 */
	public BigDecimal calcTotalPayment()
	{
		BigDecimal payment = new BigDecimal ("0");
		for (Payment p : payments)
		{ payment = payment.add(p.getAmount());}
		
		return payment.setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Calculates the amount of cash.
	 * If there is cash in the payment, id adds it to the amount.
	 * 
	 * @return amount of the cash
	 */
	public BigDecimal calcCash()
	{
		BigDecimal cash = new BigDecimal ("0");
		for (Payment p : payments)
		{ if (p.hasCash()) cash = cash.add(p.getAmount());}
		
		return cash;
	}
	
	/**
	 * Calculates amount tendered for the sale.
	 * Adds the amounts tendered for every payment in the sale.
	 * 
	 * @return amount tendered
	 */
	public BigDecimal calcAmtTendered()
	{
		BigDecimal amtTendered = new BigDecimal ("0");
		for (Payment p : payments)
		{ amtTendered = amtTendered.add(p.getAmtTendered());}
		
		return amtTendered.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
    
	/**
	 * Compares amount of total payments and total to pay. If it is equal or if total payment is bigger, it returns
	 * true.
	 * 
	 * @return true if payment is enough
	 */
	public Boolean isPaymentEnough()
	{
		return (calcTotalPayment().compareTo(calcTotal()) >= 0);
	}

	/**
	 * Adds the payment for the sale.
	 * 
	 * @param payment
	 */
	public void addPayment(Payment payment)
	{
		getPayments().add(payment);
	}

	/**
	 * Adds the sale line item to the sale line items and it sets it for the sale.
	 * 
	 * @param sli
	 */
	public void addSaleLineItem(SaleLineItem sli)
	{
		getSaleLineItems().add(sli);
		sli.setSale(this);
	}
	
	/**
	 * Returns the String type of the sale instance.
	 */
	public String toString()
	{
		String slis = "";
		   for (SaleLineItem sli : getSaleLineItems()) { slis += "     "+sli.toString()+"\r\n";}
		return "Sale : SubTotal = "+calcSubTotal().toPlainString() 
				+" Tax = "+calcTax().toPlainString()
				+" Total = "+calcTotal().toPlainString()
				+" Payment = "+calcTotalPayment().toPlainString()
				+" Change = "+calcChange()+"\r\n"+slis;
	}
	// Overriding the compareTo method
	   public int compareTo(Sale sale){
	      return getDateTime().compareTo(sale.getDateTime());
	   }

	   // Overriding the compare method  
	   public int compare(Sale s1, Sale s2){
	      return (int) (s1.getDateTime().getTimeInMillis()- s2.getDateTime().getTimeInMillis()) ;
	     
	   }
    
	   /**
	    * Looks for and returns the session of the sale.
	    * 
	    * @return session
	    */
	public Session getSession() {
		return session;
	}

	/**
	 * Sets the session for the sale.
	 * 
	 * @param session
	 */
	public void setSession(Session session) {
		this.session = session;
	}
	   

}