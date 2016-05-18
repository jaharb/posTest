package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import POSPD.*;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class CashierEditPanel extends JPanel {
	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Create the panel with passed current frame, store, cashier, and parameter that indicates
	 * if the add or edit button is pressed previously. 
	 */
	public CashierEditPanel(JFrame currentFrame, Store store, Cashier cashier, boolean isAdd) {
		Person person;
		person = cashier.getPerson();
		
		setLayout(null);
		
		//Label that indicates the title 
		JLabel lblEdit = new JLabel("Edit Cashier");
		lblEdit.setBounds(171, 36, 96, 16);
		add(lblEdit);
		/**
		 * Button Save performs following action: If we are not adding new cashier
		 * we remove old one and add new one (edited one) to the store. If we are adding 
		 * new one, we don't remove anything, we just add new one and set all of the 
		 * values from the text fields. 
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd && !cashier.getNumber().equals(textField.getText()))
				{
					store.removeCashier(cashier);
					cashier.setNumber(textField.getText());
					store.addCashier(cashier);;
				}
				if (isAdd) 
				{
					cashier.setNumber(textField.getText());
					store.addCashier(cashier);
				}
				
				cashier.setPassword(passwordField.getText());
				person.setName(textField_1.getText());
				person.setAddress(textField_2.getText());
				person.setCity(textField_3.getText());
				person.setState(textField_4.getText());
				person.setZip(textField_5.getText());
				person.setSSN(textField_7.getText());
				person.setPhone(textField_6.getText());
				
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(73, 242, 117, 29);
		add(btnSave);
		
		/**
		 * Button Cancel returns Cashier List Panel On.
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(233, 242, 117, 29);
		add(btnCancel);
		
		/**
		 * Labels for all of the variables that contain info about cashier.
		 */
		JLabel lblNumber = new JLabel("Number :");
		lblNumber.setBounds(48, 75, 61, 16);
		add(lblNumber);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(48, 101, 61, 16);
		add(lblName);
		
		JLabel lblAddress = new JLabel("Address : ");
		lblAddress.setBounds(48, 127, 76, 16);
		add(lblAddress);
		
		JLabel lblCity = new JLabel("City :");
		lblCity.setBounds(48, 153, 61, 16);
		add(lblCity);
		
		JLabel lblState = new JLabel("State : ");
		lblState.setBounds(247, 151, 61, 16);
		add(lblState);
		
		JLabel lblZip = new JLabel("Zip :");
		lblZip.setBounds(324, 151, 61, 16);
		add(lblZip);
		
		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setBounds(48, 179, 61, 16);
		add(lblPhone);
		
		JLabel lblSsn = new JLabel("SSN : ");
		lblSsn.setBounds(206, 75, 61, 16);
		add(lblSsn);
		
		/**
		 * Text fields to enter in values for all variables that contain info
		 * about cashiers.
		 */
		passwordField = new JPasswordField(cashier.getPassword());
		passwordField.setBounds(108, 204, 90, 28);
		add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(48, 205, 61, 16);
		add(lblPassword);
		
		textField = new JTextField(cashier.getNumber());
		textField.setBounds(108, 69, 61, 28);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(person.getName());
		textField_1.setBounds(108, 96, 134, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(person.getAddress());
		textField_2.setBounds(108, 123, 134, 28);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField(person.getCity());
		textField_3.setBounds(108, 150, 134, 28);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField(person.getState());
		textField_4.setBounds(289, 145, 31, 28);
		add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField(person.getZip());
		textField_5.setBounds(360, 145, 61, 28);
		add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField(person.getPhone());
		textField_6.setBounds(108, 177, 134, 28);
		add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField(person.getSSN());
		textField_7.setBounds(247, 69, 134, 28);
		add(textField_7);
		textField_7.setColumns(10);

	}
}
