package POSPD;

import java.math.BigDecimal;
import java.util.*;

public class Cashier
{

	private String number;
	private Person person;
	/**
	 * Association Type = POSPD.Session
	 */
	private ArrayList<Session> sessions;
	
	/**
	 * String password-password for login
	 */
	private String password;
	
	
	
	/**
	 * Default Cashier constructor creates an instance of cashier with arrayList of sessions 
	 * and Person object.
	 */

	public Cashier()
	{ 
		sessions = new ArrayList<Session>();
		person = new Person();
	}
	
	/**
	 * Cashier constructor with passed in number (as string), password (as string), and person(Person),
	 * creates an instance with all the fields set.
	 *  
	 * @param number
	 * @param person
	 * @param password
	 */
	public Cashier(String number, Person person, String password)
	{
		this();
		setNumber(number);
		setPerson(person);
		setPassword(password);
	}
	
	/**
	 * getNumber() looks for cashier's number and returns it
	 * @return cashier number
	 */

	public String getNumber()
	{
		return this.number;
	}
	
	/**
	 * setNumber()-sets number of cashier
	 * @param number
	 */

	public void setNumber(String number)
	{
		this.number = number;
	}
	
	/**
	 * returns Person object associated with cashier
	 * @return person
	 */

	public Person getPerson()
	{
		return this.person;
	}
	
	/**
	 * sets Person object for cashier
	 * @param person
	 */

	public void setPerson(Person person)
	{
		this.person = person;
	}
	
	/**
	 * 
	 * @return arrayList of sessions associated with specific cashier
	 */

	public ArrayList<Session> getSessions()
	{
		return this.sessions;
	}
	
	

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * isAuthorized()-compares entered password with one in the record by the number of cashier
	 *                and if they match it returns true.
	 * @param password
	 */
	public Boolean isAuthorized(String password)
	{
		return  getPassword().compareTo(password) == 0;
	}
	
	/**
	 * isUsed()-checks if cashier has any sessions and if he does returns true
	 * @return
	 */
	
	public Boolean isUsed()
	{
		return getSessions().size() != 0;
	}
	
	
    /**
     * calcNumberSales- has one parameter (date), declares a variable numSales that is going to be return later.
     * As long as there are sessions, it loops through them and compares start date of session with the date
     * that was passed as parameter. If those dates are equal, it adds sales to number of sales.  
     * 
     * @param date
     * @return numSales
     */
	
	public int calcNumberSales(GregorianCalendar date)
	{
		int numSales= 0;
		for (Session session : getSessions())
		{
			
			if (session.getStartDateTime().get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
					session.getStartDateTime().get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR))
				{
					numSales+=session.getSales().size();
				}
		}
		return numSales;
	}
	
	/**
	 * calcDollarSales- similar to calcNumberSales, it compares started date of the session with the date that was
	 * passed as the parameter. If they are equal, it adds amount of money in dollars to the dollarSales variable.
	 * 
	 * @param date
	 * @return dollarSales
	 */
	
	public BigDecimal calcDollarSales(GregorianCalendar date)
	{
		BigDecimal dollarSales= new BigDecimal("0.00");
		for (Session session : getSessions())
		{
			
			if (session.getStartDateTime().get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
					session.getStartDateTime().get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR))
				{
				
					dollarSales = dollarSales.add(session.calcTotal());
				}
		}
		 
		return dollarSales;
	}
	/**
	 *addSession- adds a session to the arrayList of sessions
	 * 
	 * @param session
	 */
	public void addSession(Session session)
	{
		getSessions().add(session);
	}
	
	public String toString()
	{
		return getNumber()+" "+getPerson().getName();
	}
}