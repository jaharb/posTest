package POSPD;

import java.math.*;

public class Check extends AuthorizedPayment
{

	private String routingNumber;
	private String accountNumber;
	private String checkNumber;
	private BigDecimal amtTendered;
	
	/**
	 * Default Check constructor creates an empty instance of Check class, no parameters.
	 */
	public Check()
	{
		
	}
	
	/**
	 * Check constructor creates an instance of Check with amount to be payed, account number, check number
	 * and amount tendered. It adds this payment method to sale.
	 *  
	 *   
	 * @param amount- amount to be payed
	 * @param accountNumber
	 * @param checkNumber
	 * @param amtTendered-amount of money that customer paid with
	 */
	
	public Check(Sale sale, String amount, String accountNumber, String checkNumber, String amtTendered)
	{
		setAmount(new BigDecimal(amount));
		setAccountNumber (accountNumber);
		setCheckNumber(checkNumber);
		setAmtTendered(new BigDecimal(amtTendered));
		sale.addPayment(this);
	}
	
	/**
	 * Looks for the routing number of account and returns it
	 * 
	 * @return routing number
	 */
	public String getRoutingNumber()
	{
		return this.routingNumber;
	}
	/**
	 * Sets routing Number
	 * 
	 * @param routingNumber
	 */

	public void setRoutingNumber(String routingNumber)
	{
		this.routingNumber = routingNumber;
	}
	
    /**
     * Looks for an account number and returns it.
     * 
     * @return account number
     */
	public String getAccountNumber()
	{
		return this.accountNumber;
	}
	
	/**
	 * Sets account number
	 * 
	 * @param accountNumber
	 */

	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber = accountNumber;
	}
	
	/**
	 * Looks for and returns check number.
	 * 
	 * @return check number
	 */

	public String getCheckNumber()
	{
		return this.checkNumber;
	}
	
	/**
	 * Sets check number
	 * 
	 * @param checkNumber
	 */

	public void setCheckNumber(String checkNumber)
	{
		this.checkNumber = checkNumber;
	}
	
	/**
	 * Looks for and returns amount tendered.
	 * 
	 * @return amount tendered
	 */

	public BigDecimal getAmtTendered()
	{
		return this.amtTendered;
	}
	
	/**
	 * Sets amount tendered
	 * 
	 * @param amtTendered
	 */

	public void setAmtTendered(BigDecimal amtTendered)
	{
		this.amtTendered = amtTendered;
	}
	
	/**
	 * isAuthorized- is check authorized.
	 */


	@Override
	public Boolean isAuthorized() {
		// TODO Auto-generated method stub
		return null;
	}

}