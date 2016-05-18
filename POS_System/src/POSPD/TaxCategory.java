package POSPD;

import java.math.*;
import java.util.*;

public class TaxCategory
{

	private String category;
	private Calendar effectiveDate;
	private BigDecimal taxRate;
 
    /**
     * Default TaxCategory constructor that creates an instance of TaxCategory with TaxRate.
     */
	public TaxCategory()
	{
		setTaxRate(new BigDecimal("0.00"));
	}

	
	/**
	 * TaxCategory constructor that creates an instance of TaxCategory with category, effective date and taxRate
	 * 
	 * @param category
	 * @param effectiveDate
	 * @param taxRate
	 */
	public TaxCategory(String category, String effectiveDate, String taxRate)
	{
		setCategory(category);
		setTaxRate(new BigDecimal (taxRate));
		String[] ed;
		ed = effectiveDate.split("/");
		setEffectiveDate(new GregorianCalendar(Integer.parseInt(ed[2])+2000,Integer.parseInt(ed[0])-1,Integer.parseInt(ed[1])));

	}

	
	/**
	 * Looks for and returns tax category.
	 * 
	 * @return category
	 */
	public String getCategory()
	{
		return this.category;
	}

	/**
	 * Sets tax category
	 * 
	 * @param category
	 */
	public void setCategory(String category)
	{
		this.category = category;
	}

	/**
	 * Looks for and returns effective date for some category.
	 * 
	 * @return effective date
	 */
	public Calendar getEffectiveDate()
	{
		return this.effectiveDate;
	}

	/**
	 * Sets effective date for category.
	 * 
	 * @param effectiveDate
	 */
	public void setEffectiveDate(Calendar effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}
	
	/**
	 * Sets the effective date of category in the string type.
	 * 
	 * @param effectiveDate
	 */
	public void setEffectiveDate(String effectiveDate)
	{
		String[] ed;
		ed = effectiveDate.split("/");
		setEffectiveDate(new GregorianCalendar(Integer.parseInt(ed[2]),Integer.parseInt(ed[0])-1,Integer.parseInt(ed[1])));			
	}
	
	/**
	 * Check if taxCategory is effective.
	 * 
	 * @param date
	 */
	public void isEffective(Calendar date)
	{
		// TODO - implement TaxCategory.isEffective
		throw new UnsupportedOperationException();
	}

	/**
	 * Looks for and returns taxRate for the tax category.
	 * 
	 * @return taxRate
	 */
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	
    /**
     * Sets the tax rate for the tax category.
     * 
     * @param taxRate
     */
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	
	/**
	 * Returns String type of TaxCategory.
	 */
	public String toString()
	{
		return getCategory();
	}

}