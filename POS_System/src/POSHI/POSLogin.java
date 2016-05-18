package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import POSPD.Cashier;
import POSPD.Register;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class POSLogin extends JPanel {
	private JPasswordField passwordField;
	private JLabel lblMessageLabel;
	private DefaultComboBoxModel cashierComboList;
	private JComboBox cashierComboBox;
	private DefaultComboBoxModel registerComboList;
	private JComboBox registerComboBox;
	private JTextField textField;

	/**
	 * Create the panel with passed current frame and store.
	 */
	public POSLogin(JFrame currentFrame, Store store) {
		setLayout(null);
		
		//Title of the panel
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(204, 27, 35, 16);
		add(lblLogin);
		
		//Label for message to enter the number of the cashier
		JLabel lblCashierNumber = new JLabel("Cashier Number");
		lblCashierNumber.setBounds(52, 103, 101, 16);
		add(lblCashierNumber);
		
		//Password field to enter the password
		passwordField = new JPasswordField();
		passwordField.setBounds(184, 185, 134, 28);
		add(passwordField);
		
		//Label for message to enter the password
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(53, 191, 80, 16);
		add(lblPassword);
		
		/**
		 * Button Login get selected cashier and register, compares entered password 
		 *  with stored password for cashier and if they match it creates new session
		 *  and it sets the cash amount for the register. If they don't equal, it 
		 *  displays the error message.
		 */
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cashier cashier = (Cashier) cashierComboBox.getSelectedItem();
				Register register = (Register) registerComboBox.getSelectedItem();
				
				if (cashier.isAuthorized(passwordField.getText()))
				{
					Session session = new Session(store,cashier,register);
					session.getRegister().getCashDrawer().setCashAmount(new BigDecimal(textField.getText()));
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new POSSale(currentFrame,store,session,new Sale()));
					currentFrame.getContentPane().revalidate();
				}
				else
				{
					lblMessageLabel.setText("Invalid Password for Number. Try Again");
				}
				
			}
		});
		btnLogin.setBounds(103, 241, 117, 29);
		add(btnLogin);
		
		/**
		 * Button Cancel takes user back to home page.
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(250, 241, 117, 29);
		add(btnCancel);
		
		lblMessageLabel = new JLabel("");
		lblMessageLabel.setForeground(Color.RED);
		lblMessageLabel.setBounds(137, 69, 230, 16);
		add(lblMessageLabel);
		
		//Label for message to enter the number of the register
		JLabel lblRegisterNumber = new JLabel("Register Number :");
		lblRegisterNumber.setBounds(52, 131, 128, 16);
		add(lblRegisterNumber);
		
		//Combo box with cashiers to choose from
		DefaultComboBoxModel cashierList = new DefaultComboBoxModel(store.getCashierList().toArray());
		cashierComboBox = new JComboBox(cashierList);
		cashierComboBox.setBounds(183, 99, 117, 27);
		add(cashierComboBox);
		
		//Combo box with registers to choose from
		DefaultComboBoxModel registerList = new DefaultComboBoxModel(store.getRegisterList().toArray());
		registerComboBox = new JComboBox(registerList);
		registerComboBox.setBounds(183, 131, 117, 27);
		add(registerComboBox);
		
		//Label for message to enter the starting cash
		JLabel lblStartingCash = new JLabel("Starting Cash :");
		lblStartingCash.setBounds(51, 163, 117, 16);
		add(lblStartingCash);
		
		//Text field to enter the starting cash
		textField = new JTextField();
		textField.setBounds(184, 157, 134, 28);
		add(textField);
		textField.setColumns(10);

	}
}
