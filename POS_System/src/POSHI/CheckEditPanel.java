package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.Cash;
import POSPD.Check;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class CheckEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel with passed current frame, 
	 *        store, session, sale and check.
	 */
	public CheckEditPanel(JFrame currentFrame, Store store,Session session, Sale sale, Check check ) {
		setLayout(null);
		//label for message to enter the check
		JLabel lblEnterCheck = new JLabel("Enter Check");
		lblEnterCheck.setBounds(76, 22, 97, 16);
		add(lblEnterCheck);
		
		//label for message to enter the amount of the check
		JLabel lblAmount = new JLabel("Amount :");
		lblAmount.setBounds(36, 50, 61, 16);
		add(lblAmount);
		
		//text field to enter the amount
		textField = new JTextField();
		textField.setBounds(151, 44, 97, 28);
		add(textField);
		textField.setColumns(10);
		
		//label for message to enter routing number
		JLabel lblRoutingNum = new JLabel("Routing Num :");
		lblRoutingNum.setBounds(36, 92, 97, 16);
		add(lblRoutingNum);
		
		//text field to enter the routing number
		textField_1 = new JTextField();
		textField_1.setBounds(151, 84, 97, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		//label for message to enter the account number
		JLabel lblAccountNum = new JLabel("Account Num :");
		lblAccountNum.setBounds(36, 130, 97, 16);
		add(lblAccountNum);
		
		//text field to enter the account number
		textField_2 = new JTextField();
		textField_2.setBounds(151, 124, 97, 28);
		add(textField_2);
		textField_2.setColumns(10);
		
		//label for message to enter the check number
		JLabel lblCheckNum = new JLabel("Check Num:");
		lblCheckNum.setBounds(36, 167, 97, 16);
		add(lblCheckNum);
		
		//text field to enter the check number
		textField_3 = new JTextField();
		textField_3.setBounds(151, 161, 97, 28);
		add(textField_3);
		textField_3.setColumns(10);
		
		/**
		 * Button Save performs following action: sets the variables that contain
		 * info about check, adds this payment to sale and goes to POSPayment panel.
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check.setAmtTendered(new BigDecimal(textField.getText()));
				check.setAmount(sale.calcAmount(new BigDecimal(textField.getText())));
				check.setRoutingNumber(textField_1.getText());
				check.setAccountNumber(textField_2.getText());
				check.setCheckNumber(textField_3.getText());
				
				sale.addPayment(check);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame,store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
			
		});
		btnSave.setBounds(36, 209, 117, 29);
		add(btnSave);
		
		/**
		 * Button Cancel takes user to POSPayment panel.
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new POSPayment(currentFrame,store, session, sale));
					currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(161, 209, 117, 29);
		add(btnCancel);

	}
}

