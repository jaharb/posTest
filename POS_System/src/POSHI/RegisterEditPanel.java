package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.*;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class RegisterEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel with passed current frame, store, register and parameter that
	 * indicates if it is register that is already in store that is being edited, or new one
	 * that is yet to be in store. 
	 */
	public RegisterEditPanel(JFrame currentFrame, Store store, Register register, boolean isAdd) {
		setLayout(null);
		
		//Title of the panel
		JLabel lblEditRegister = new JLabel("Edit Register");
		lblEditRegister.setBounds(168, 34, 90, 16);
		add(lblEditRegister);
		
		//Label that indicates the number of the register
		JLabel lblRegisterNumber = new JLabel("Register Number :");
		lblRegisterNumber.setBounds(52, 73, 124, 16);
		add(lblRegisterNumber);
		
		//Text Field that displays the number of the register
		textField = new JTextField(register.getNumber());
		textField.setBounds(188, 67, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		/**
		 * Button Save determines if the register that is being edited is new one
		 * or one that is already in the store and if it is new one it sets the
		 * register number and adds the register. If it is one that was already in the
		 * store, it deletes old one, and then sets number for edited version and 
		 * adds it to the store. After this, it takes user to Register List Panel.
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd && !register.getNumber().equals(textField.getText()))
				{
					store.removeRegister(register);
					register.setNumber(textField.getText());
					store.addRegister(register);;
				}
				if (isAdd) 
				{
					register.setNumber(textField.getText());
					store.addRegister(register);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(91, 201, 117, 29);
		add(btnSave);
		
		/**
		 * Button Cancel takes user to Register List Panel.
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(220, 201, 117, 29);
		add(btnCancel);

	}

}
