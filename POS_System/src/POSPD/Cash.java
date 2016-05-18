package POSPD;

import java.math.*;

public class Cash extends Payment
{

	private BigDecimal amtTendered;
	/**
	* Cash default constructor creates an instance with no default values  
	*/
	public Cash()
	{
		
	}
	/**
	 * Cash constructor creates an instance with the passed in amount, amtTenderd
	 * and adds the sales collection of payments.
	 * 
	 * @param amount
	 * @param amtTendered
	 */
	public Cash(Sale sale, String amount, String amtTendered)
	{
		this();
		setAmount(new BigDecimal(amount));
		setAmtTendered(new BigDecimal(amtTendered));
		sale.addPayment(this);
	}
	/**
	* getAmountTendered() returns the value of amtTendered 
	* 
	* @returns BigDecial of the amtTendered
	*/
	public BigDecimal getAmtTendered()
	{
		return this.amtTendered;
	}

	public void setAmtTendered(BigDecimal amtTendered)
	{
		this.amtTendered = amtTendered;
	}

	

	

}