package POSPD;

import java.math.BigDecimal;
import java.util.*;

public class Credit extends AuthorizedPayment
{

	private String cardType;
	private String acctNumber;
	private Calendar expireDate;
	
	/**
	 * Default Credit constructor. Creates an empty instance. 
	 */
	
	public Credit()
	{

	}

	/**
	 * Credit constructor- creates an instance of Credit class with passed parameters. Sets card type, expire date,
	 * amount and account number.
	 *  
	 * @param cardType
	 * @param acctNumber
	 * @param expireDate
	 */
	public Credit(Sale sale, String amount, String cardType, String acctNumber, String expireDate)
	{
		setAmount(new BigDecimal(amount));
		setCardType(cardType);
		setAcctNumber(acctNumber);
		String[] ed;
		ed = expireDate.split("/");
		setExpireDate(new GregorianCalendar(Integer.parseInt(ed[2]),Integer.parseInt(ed[0]),Integer.parseInt(ed[1])));
		sale.addPayment(this);
	}
	
	/**
	 * getCardType-gets the type of the card and returns it
	 * 
	 * @return cardType
	 */

	public String getCardType()
	{
		return this.cardType;
	}

	public void setCardType(String cardType)
	{
		this.cardType = cardType;
	}
	
	/**
	 * getAcctNumber-gets and return account number
	 * 
	 * @return acctNumber
	 */

	public String getAcctNumber()
	{
		return this.acctNumber;
	}

	public void setAcctNumber(String acctNumber)
	{
		this.acctNumber = acctNumber;
	}
	
	/**
	 * getExpireDate-gets the date that credit card is expiring on and returns it. 
	 * 
	 * @return expireDate
	 */

	public Calendar getExpireDate()
	{
		return this.expireDate;
	}

	public void setExpireDate(Calendar expireDate)
	{
		this.expireDate = expireDate;
	}
	

	@Override
	public Boolean isAuthorized() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean hasCash()
	{
		return false;
	}
}