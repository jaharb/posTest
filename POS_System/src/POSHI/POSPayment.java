package POSHI;

import javax.swing.JPanel;

import POSPD.*;


import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class POSPayment extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JPanel panel;

	/**
	 * Create the panel with passed current frame, store, session and sale.
	 */
	public POSPayment(JFrame currentFrame, Store store, Session session, Sale sale) {
		setLayout(null);
		
		/**
		 * Button Cash creates new Cash instance and passes it to CashEditPanel.
		 */
		JButton btnCash = new JButton("Cash");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel = new CashEditPanel(currentFrame, store, session, sale, new Cash());
				panel.setBounds(171, 20, 273, 244);
				add(panel);
				revalidate();
				repaint();
			}
		});
		btnCash.setBounds(17, 170, 117, 29);
		add(btnCash);
		
		/**
		 * Button Check creates new instance of check and passes it to CheckEditPanel
		 */
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel = new CheckEditPanel(currentFrame, store, session, sale, new Check());
				panel.setBounds(171, 20, 273, 244);
				add(panel);
				revalidate();
				repaint();
			}
		});
		btnCheck.setBounds(17, 211, 117, 29);
		add(btnCheck);
		
		/**
		 * Button Credit creates new instance of Credit and passes it to CreditEditPanel.
		 */
		JButton btnCredit = new JButton("Credit");
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel = new CreditEditPanel(currentFrame, store, session, sale, new Credit());
				panel.setBounds(171, 20, 273, 244);
				add(panel);
				revalidate();
				repaint();
			}
		});
		btnCredit.setBounds(17, 252, 117, 29);
		add(btnCredit);
		
		
		//Label for message to enter payment type
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPayment.setBounds(175, 16, 105, 16);
		add(lblPayment);
		
		//Label for message to enter amount of payment due
		JLabel lblPaymnetDue = new JLabel("Paymnet Due :");
		lblPaymnetDue.setBounds(17, 31, 108, 16);
		add(lblPaymnetDue);
		
		//Text field that displays the payment due.
		textField = new JTextField(sale.calcTotal().toString());
		textField.setEditable(false);
		textField.setBounds(10, 59, 124, 28);
		add(textField);
		textField.setColumns(10);
		
		//Label for message to enter the amount tendered
		JLabel lblAmountTendered = new JLabel("Amount Tendered :");
		lblAmountTendered.setBounds(17, 99, 124, 16);
		add(lblAmountTendered);
		
		//Text field that displays the amount tendered
		textField_1 = new JTextField(sale.calcAmtTendered().toString());
		textField_1.setEditable(false);
		textField_1.setBounds(10, 130, 124, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		/**
		 * Button Payment Complete completes sale and takes user back to POSSale.
		 */
		JButton btnPaymentComplete = new JButton("Payment Complete");
		btnPaymentComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSSale(currentFrame,store,session,sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnPaymentComplete.setBounds(146, 271, 171, 29);
		//if amount tendered is equal or bigger than payment due, Payment Complete button is enabled.
		if (sale.calcAmtTendered().compareTo(sale.calcTotal()) >= 0)
			btnPaymentComplete.setEnabled(true);
		else btnPaymentComplete.setEnabled(false);
		add(btnPaymentComplete);

	}
}
