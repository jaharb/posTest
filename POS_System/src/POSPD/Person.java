package POSPD;

public class Person
{

	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String ssn;
	private Cashier cashier;
	
	/**
	 * Person default constructor that creates empty instance of Person object.
	 */
	public Person()
	{
	}

	/**
	 * Person constructor that creates an instance of Person with name, ssn, 
	 *        address, city, state, zip, and phone.
	 *        
	 * @param name
	 * @param address
	 * @param phone
	 * @param ssn
	 */
	public Person(String name, String ssn, String address, String city, String state,String zip,String phone)
	{
		setName(name);
		setAddress(address);
		setCity(city);
		setState(state);
		setZip(zip);
		setPhone(phone);
		setSSN(ssn);
	}

    
	/**
	 * Looks for the name of a person and returns it. 
	 * 
	 * @return name
	 */
	public String getName()
	{
		return this.name;
	}
    
	/**
	 * Sets the name of a person.
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
    
	/**
	 * Looks for the address of the person and returns it.
	 *    
	 * @return address
	 */
	public String getAddress()
	{
		return this.address;
	}
	
	/**
	 * Sets the address of a person.
	 * 
	 * @param address
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	/**
	 * Looks for and returns the city where person lives.
	 * 
	 * @return city
	 */
	public String getCity()
	{
		return this.city;
	}
	
	/**
	 * Sets the state where person lives.
	 * 
	 * @param state
	 */
	public void setState(String state)
	{
		this.state = state;
	}
	
	/**
	 * Looks for and returns the state where person lives.
	 * 
	 * @return state
	 */
	public String getState()
	{
		return this.state;
	}
	
	/**
	 * Sets the ZIP code for the area where person lives.
	 * 
	 * @param zip
	 */
	public void setZip(String zip)
	{
		this.zip = zip;
	}
	
	/**
	 * Looks for and returns the ZIP code of the area where person lives.
	 * 
	 * @return ZIP code
	 */
	public String getZip()
	{
		return this.zip;
	}
	
	/**
	 * Sets the city where person lives.
	 * 
	 * @param city
	 */
	public void setCity(String city)
	{
		this.city = city;
	}
	
	/**
	 * Looks for and returns the phone number of the person.
	 * 
	 * @return phone number
	 */
	public String getPhone()
	{
		return this.phone;
	}
    
	/**
	 * Sets the phone number of the person
	 * 
	 * @param phone
	 */
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
    
	/**
	 * Looks for and return the SSN of the person
	 * 
	 * @return SSN
	 */
	public String getSSN()
	{
		return this.ssn;
	}
    
	/**
	 * Sets the SSN of the person
	 * 
	 * @param ssn
	 */
	public void setSSN(String ssn)
	{
		this.ssn = ssn;
	}
    
	/**
	 * Looks for cashier that this person is associated with and it returns it.
	 * 
	 * @return cashier
	 */
	public Cashier getCashier()
	{
		return this.cashier;
	}
    
	/**
	 * Sets the cashier for the person that is associated with this cashier.
	 * 
	 * @param cashier
	 */
	public void setCashier(Cashier cashier)
	{
		this.cashier = cashier;
	}
	
}