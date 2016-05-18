package POSPD;

import java.math.BigDecimal;
import java.util.*;

public class Session implements Comparator<Session>, Comparable<Session>
{

	private Cashier cashier;
	private Register register;
	/**
	 * Association Type = POSPD.Sale
	 */
	private ArrayList<Sale> sales;
	private Calendar startDateTime;
	private Calendar endDateTime;
    
	/**
	 * Default Session constructor that creates an instance of Session with ArrayList of sales and start time.
	 */
	public Session()
	{
		sales = new ArrayList<Sale>();
		startDateTime = new GregorianCalendar();
	}
	
	/**
	 * Session constructor that creates an instance of Session with ArrayList of sales, start date time,
	 * cashier in charge of session, and register that session is held on. It adds the session to the store.
	 * 
	 * @param store
	 * @param cashier
	 * @param register
	 */
	public Session(Store store, String cashier, String register)
	{
		this();
		Cashier c = store.findCashierForNumber(cashier);
		setCashier(c);
		setRegister(store.findRegisterForNumber(register));
		
		store.addSession(this);
		c.addSession(this);
		
	}
	
    
	/**
	 * Session constructor that creates an instance of Session with cashier (Cashier) and register (Register).
	 * It has all same components as previous constructor, but cashier and register of different type. It also 
	 * adds this session to the store.
	 * 
	 * @param store
	 * @param cashier
	 * @param register
	 */
	public Session(Store store, Cashier cashier, Register register)
	{
		this();
		setCashier(cashier);
		setRegister(register);
		store.addSession(this);
		cashier.addSession(this);
		
	}
	
	
	/**
	 * Looks for and returns the cashier who is in charge of some session.
	 * 
	 * @return cashier
	 */
	public Cashier getCashier()
	{
		return this.cashier;
	}
	
    /**
     * Sets the cashier who is in charge for some session
     * 
     * @param cashier
     */
	public void setCashier(Cashier cashier)
	{
		this.cashier = cashier;
	}
    
	/**
	 * Looks for and returns the register that session was held on.
	 * 
	 * @return register
	 */
	public Register getRegister()
	{
		return this.register;
	}
    
	/**
	 * Sets the register that session is held on.
	 * 
	 * @param register
	 */
	public void setRegister(Register register)
	{
		this.register = register;
	}
    
	/**
	 * Looks for and returns the ArrayList of the sales that are done in some session.
	 * 
	 * @return sales
	 */
	public ArrayList<Sale> getSales()
	{
		return this.sales;
	}
    
	/**
	 * Looks for and returns the start date of the session.
	 * 
	 * @return start date
	 */
	public Calendar getStartDateTime()
	{
		return this.startDateTime;
	}
    
	/**
	 * Sets the start date for the session.
	 * 
	 * @param startDateTime
	 */
	public void setStartDateTime(Calendar startDateTime)
	{
		this.startDateTime = startDateTime;
	}

	/**
	 * Looks for and returns the end date time of the session.
	 * 
	 * @return end date time
	 */
	public Calendar getEndDateTime()
	{
		return this.endDateTime;
	}
    
	/**
	 * Sets the end date time.
	 * 
	 * @param endDateTime
	 */
	public void setEndDateTime(Calendar endDateTime)
	{
		this.endDateTime = endDateTime;
	}

	/**
	 * Adds sale to the sales. If sale doesn't equal to null it adds it.
	 */
	public void addSale(Sale sale)
	{
		if (sale != null)
		{
			getSales().add(sale);
		}
	}
	
	/**
	 * Calculates the total for the session. 
	 * Adds all totals of all sales to total of the session and returns it.
	 * 
	 * @return session total
	 */
	public BigDecimal calcTotal()
	{
		BigDecimal total = new BigDecimal ("0");
		for (Sale  sale : sales)
		{ total = total.add(sale.calcTotal());}
		
		return total.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Looks for and returns the number of sales for some session.
	 * 
	 * @return number of sales for session
	 */
	public int getNumberSales()
	{
		return getSales().size();
	}
	
	/**
	 * Calculates the total of cash for some session. 
	 * Adds all cash totals for all sales and adds it to cash session total.
	 * 
	 * @return cash session total
	 */
	public BigDecimal getTotalCash()
	{
		BigDecimal totalCash = new BigDecimal("0");
		for (Sale sale: getSales())
		{
			totalCash = totalCash.add(sale.calcCash());
		}
		return totalCash.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Calculates the difference in amount of the cash before and after session.
	 * Subtracts beginning cash amount from current cash amount.
	 * 
	 * @param cashCount
	 * @return cash difference
	 */
	public BigDecimal calcCashCountDiff(BigDecimal cashCount)
	{
		return (getRegister().getCashDrawer().getCashAmount().add(getTotalCash()).subtract(cashCount)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	// Overriding the compareTo method
	   public int compareTo(Session session){
	      return (getStartDateTime().compareTo(session.getStartDateTime()));
	   }

	   // Overriding the compare method  
	   public int compare(Session p1, Session p2){
	      return (int) (p1.getStartDateTime().getTimeInMillis()- p2.getStartDateTime().getTimeInMillis()) ;
	     
	   }
	   
	   /**
	    * Returns String type of Session.
	    */
	   public String toString()
	   {
		   String sales = "";
		   for (Sale sale : getSales()) { sales += "  "+sale.toString()+"\r\n";}
		   return "Session : Cashier :"+getCashier().getPerson().getName() +" Register :"
		          +getRegister().getNumber()+" Date : "
		          +new Date(getStartDateTime().getTimeInMillis()).toString()+" Total : "
		          +calcTotal().toPlainString() +"\r\n"+ sales;
	   }

}