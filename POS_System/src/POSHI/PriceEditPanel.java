package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.Item;
import POSPD.Price;
import POSPD.PromoPrice;
import POSPD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class PriceEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private boolean isPromo= false;
	private PromoPrice promoPrice = null;
	private Price price;
	private JLabel lblEndDate;

	/**
	 * Create the panel with passed current frame, store, item, current price, 
	 * parameter indicating if item is being added and parameter indicating
	 * if price is being added.
	 */
	public PriceEditPanel(JFrame currentFrame, Store store,Item item, Price priceIn, boolean isItemAdd, boolean isAdd) {
		price = priceIn;
		System.out.println(price.getClass());
		if (price.getClass().toString().equals("class POSPD.PromoPrice"))
		{
			isPromo = true;
			promoPrice = (PromoPrice) price;
		}
		
		setLayout(null);
		
		//Title of the panel
		JLabel lblEditPrice = new JLabel("Edit Price");
		lblEditPrice.setBounds(160, 16, 72, 16);
		add(lblEditPrice);
		
		//Label that indicates the price of the item
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setBounds(43, 92, 61, 16);
		add(lblPrice);
		
		//Text field that displays price of the item.
		textField = new JTextField( );
		if (price.getPrice() != null)
			textField = new JTextField(price.getPrice().toString());
		textField.setBounds(140, 86, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		//Label that indicates the effective date for the price
		JLabel lblEffectiveDate = new JLabel("Effective Date :");
		lblEffectiveDate.setBounds(43, 134, 95, 16);
		add(lblEffectiveDate);
		
		//if effective date is not null, effective date is set to "MM/dd/yyyy" format
		String strdate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if (price.getEffectiveDate() != null) 
		strdate = sdf.format(price.getEffectiveDate().getTime());
		
		//Text field that displays the effective date.
		textField_1 = new JTextField(strdate);
		textField_1.setBounds(140, 128, 134, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		/**
		 * Button Save adds price if isAdd is true, or deletes old and saves new edited price
		 * if isAdd is false. If the price is promo price, then it sets the end date too 
		 * and saves the price as promo price and it takes user to ItemEditPanel
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setting the price and effective date
				price.setPrice(new BigDecimal(textField.getText()));
				String[] ef;
				ef = textField_1.getText().split("/");
				price.setEffectiveDate(new GregorianCalendar(Integer.parseInt(ef[2]),Integer.parseInt(ef[0])-1,Integer.parseInt(ef[1])));
					
				if (isAdd) 
				{
					item.addPrice(price);
				}
				
				if (isPromo)
				{
					String[] ed;
					ed = textField_2.getText().split("/");
					promoPrice.setEndDate(new GregorianCalendar(Integer.parseInt(ed[2]),Integer.parseInt(ed[0])-1,Integer.parseInt(ed[1])));
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame,store,item,isItemAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(69, 225, 117, 29);
		add(btnSave);
		
		/**
		 * Button Cancel takes user back to ItemEditPanel.
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame,store,item,isItemAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(211, 225, 117, 29);
		add(btnCancel);
		
		
		
		//check box if selected sets isPromo to true and creates new instance of PromoPrice()
		//if selected, label EndDate is set to visible
		JCheckBox chckbxPromoPrice = new JCheckBox("Promo Price");
		chckbxPromoPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxPromoPrice.isSelected()){
					isPromo = true;
					promoPrice = new PromoPrice();
					promoPrice.setPrice(price.getPrice());
					promoPrice.setEffectiveDate(price.getEffectiveDate());
					price = promoPrice;
					lblEndDate.setVisible(true);
					
					String enddate = "";
					SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
					if (promoPrice.getEndDate() != null) 
						enddate = sdf1.format(promoPrice.getEndDate().getTime());
					textField_2.setText(enddate);
					textField_2.setVisible(true);
					
				}
				else
				{
					isPromo=false;
					lblEndDate.setVisible(false);
					textField_2.setVisible(false);
					price = new Price();
					price.setPrice(promoPrice.getPrice());
					price.setEffectiveDate(promoPrice.getEffectiveDate());
				}
			}
		});
		
		chckbxPromoPrice.setBounds(127, 45, 128, 23);
		if (isPromo) chckbxPromoPrice.setSelected(true);
		else chckbxPromoPrice.setSelected(false);
		if (isAdd) chckbxPromoPrice.setEnabled(true);
		else chckbxPromoPrice.setEnabled(false);
		add(chckbxPromoPrice);
		
		//Label for message to enter end date for promo price
		lblEndDate = new JLabel("End Date :");
		lblEndDate.setBounds(43, 168, 84, 16);
		lblEndDate.setVisible(false);
		add(lblEndDate);
		
		//Text Field to enter end date for promo price
		textField_2 = new JTextField( );
		textField_2.setBounds(140, 162, 134, 28);
		textField_2.setVisible(false);
		add(textField_2);
		textField_2.setColumns(10);
		
		//setting label EndDate and text field to enter the end date 
		//to visible if price is promo price
		if (isPromo)
		{
			lblEndDate.setVisible(true);
			
			String enddate = "";
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			if (promoPrice.getEndDate() != null) 
				enddate = sdf1.format(promoPrice.getEndDate().getTime());
			textField_2.setText(enddate);
			textField_2.setVisible(true);
		
		}
	}
}
