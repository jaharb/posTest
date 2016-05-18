package POSPD;

public class Register
{

	private String number;
	private CashDrawer cashDrawer;
    
	/**
	 * Default Register constructor that creates an instance of the Register and it assigns a cash drawe to it.
	 */
	public Register()
	{
		setCashDrawer(new CashDrawer());
	}

	/**
	 * Register constructor that creates an instance of the Register with number of the register,cash drawer for the
	 * register, and it adds this register to the store.
	 * 
	 * @param number
	 */
	public Register(Store store, String number)
	{
		this();
		setNumber(number);
		store.addRegister(this);
	}
	
	
	/**
	 * Looks for and returns the number of the register.
	 * 
	 * @return register number.
	 */
	public String getNumber()
	{
		return this.number;
	}

	/**
	 * Sets the number of the register.
	 * 
	 * @param number
	 */
	public void setNumber(String number)
	{
		this.number = number;
	}
    
	/**
	 * Looks for and returns the cash drawer associated with the register.
	 * 
	 * @return cash drawer.
	 */
	public CashDrawer getCashDrawer()
	{
		return this.cashDrawer;
	}
	
    /**
     * Sets the cash drawer for the register.
     * 
     * @param cashDrawer
     */
	public void setCashDrawer(CashDrawer cashDrawer)
	{
		this.cashDrawer = cashDrawer;
	}

	/**
	 * Returns string type of the Register instance. 
	 */
	public String toString()
	{
		return getNumber();
	}

}