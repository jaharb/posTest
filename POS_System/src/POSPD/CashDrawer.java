package POSPD;

import java.math.*;

public class CashDrawer
{

	private BigDecimal cashAmount;
	private int position;
	
	/**
	 * 
	 * @return amount of cash tendered from user
	 */

	public BigDecimal getCashAmount()
	{
		return this.cashAmount;
	}

	/**
	 * 
	 * @param cashAmount
	 */
	public void setCashAmount(BigDecimal cashAmount)
	{
		this.cashAmount = cashAmount;
	}
	
	/**
	 * @return position
	 */

	public int getPosition()
	{
		return this.position;
	}

	/**
	 * 
	 * @param position
	 */
	public void setPosition(int position)
	{
		this.position = position;
	}

}