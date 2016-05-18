package POSPD;

public class UPC
{

	private String upc;
	private Item item;
 
	/**
	 * Default UPC constructor that creates an empty instance of UPC.
	 */
	public UPC()
	{
		 
	}

	/**
	 * UPC constructor that creates an UPC instance with upc and item that upc is assigned to.
	 * 
	 * @param upc
	 */
	public UPC(String upc, Item item)
	{
		setUpc(upc);
		setItem(item);
	}
	
	/**
	 * Looks for and returns the upc of an item.
	 * 
	 * @return upc
	 */
	public String getUpc()
	{
		return this.upc;
	}

	/**
	 * Sets the upc for an item.
	 * 
	 * @param upc
	 */
	public void setUpc(String upc)
	{
		this.upc = upc;
	}

	/**
	 * Looks for and returns the item.
	 * 
	 * @return item
	 */
	public Item getItem()
	{
		return this.item;
	}

	/**
	 * Sets the item for an upc.
	 * 
	 * @param item
	 */
	public void setItem(Item item)
	{
		this.item = item;
	}

	/**
	 * Returns String type of UPC.
	 */
	public String toString()
	{
		return this.getUpc();
	}
}