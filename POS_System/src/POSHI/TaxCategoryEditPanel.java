package POSHI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class TaxCategoryEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel with passed current frame, store, tax category and parameter that
	 * indicates if tax category is new one or one currently in store.
	 */
	public TaxCategoryEditPanel(JFrame currentFrame, Store store, TaxCategory taxCategory, Boolean isAdd) {
		setLayout(null);
		
		//Label that indicates the tax category 
		JLabel lblNewLabel = new JLabel("Category :");
		lblNewLabel.setBounds(56, 76, 86, 16);
		add(lblNewLabel);
		
		//Label that indicates the rate of the tax
		JLabel lblRate = new JLabel("Rate :");
		lblRate.setBounds(56, 115, 61, 16);
		add(lblRate);
		
		//Label that indicates the date when tax category is effective
		JLabel lblNewLabel_1 = new JLabel("Date :");
		lblNewLabel_1.setBounds(56, 154, 61, 16);
		add(lblNewLabel_1);
		
		//Text Field that displays the tax category
		textField = new JTextField(taxCategory.getCategory());
		textField.setBounds(154, 70, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		//Text Field that displays tax rate
		textField_1 = new JTextField(taxCategory.getTaxRate().toString());
		textField_1.setBounds(154, 109, 134, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		String strdate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if (taxCategory.getEffectiveDate() != null) 
		strdate = sdf.format(taxCategory.getEffectiveDate().getTime());
		
		//Text field that displays the effective date for the tax category
		textField_2 = new JTextField(strdate);
		textField_2.setBounds(154, 148, 134, 28);
		add(textField_2);
		textField_2.setColumns(10);
		
		/**
		 * Button Save determines if tax category that is being edited is new one or
		 * one already in the store. If it is new one it just adds it to the store. If
		 * it is one already in store, it deletes it and adds the new edited one to the store.
		 * It takes user to Tax Category List panel 
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd && !taxCategory.getCategory().equals(textField.getText()))
				{
					store.removeTaxCategory(taxCategory);
					taxCategory.setCategory(textField.getText());
					store.addTaxCategory(taxCategory);;
				}
				if (isAdd) 
				{
					taxCategory.setCategory(textField.getText());
					store.addTaxCategory(taxCategory);
				}
				
				taxCategory.setTaxRate(new BigDecimal(textField_1.getText()));
				
				taxCategory.setEffectiveDate(textField_2.getText());
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(84, 233, 117, 29);
		add(btnSave);
		
		/**
		 * Button Cancel takes user to Tax Category Panel.
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(226, 233, 117, 29);
		add(btnCancel);
		
		//Title of the panel.
		JLabel lblEditTaxCategory = new JLabel("Edit Tax Category");
		lblEditTaxCategory.setBounds(164, 24, 161, 16);
		add(lblEditTaxCategory);

	}
}
