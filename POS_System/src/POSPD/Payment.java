package POSPD;


import java.math.*;

public abstract class  Payment
{

	private BigDecimal amount;
	
	/**
	 * Looks for and returns amount of the payment.
	 * 
	 * @return amount 
	 */

	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * Sets amount of the payment.
	 * 
	 * @param amount
	 */

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	 * Looks for and returns amount tendered.
	 * 
	 * @return amount tendered
	 */
	
	public BigDecimal getAmtTendered()
	{ return getAmount();}

	/**
	 * Looks if payment is in cash and returns true if it is.
	 * 
	 * @return boolean
	 */
	public boolean hasCash()
	{
		return true;
	}
}