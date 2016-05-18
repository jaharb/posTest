package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.Cash;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class CashEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel with passed store, session, cash and sale parameters.
	 */
	public CashEditPanel(JFrame currentFrame, Store store, Session session, Sale sale, Cash cash) {
		setLayout(null);
		
		//title of the panel
		JLabel lblEnterCashPayment = new JLabel("Enter Cash Payment");
		lblEnterCashPayment.setBounds(74, 21, 124, 16);
		add(lblEnterCashPayment);
		
		//label for the message to enter the amount tendered
		JLabel lblAmountTendered = new JLabel("Amount Tendered :");
		lblAmountTendered.setBounds(26, 49, 124, 16);
		add(lblAmountTendered);
		
		//text field to enter the amount tendered
		textField = new JTextField();
		textField.setBounds(162, 43, 86, 28);
		add(textField);
		textField.setColumns(10);
		
		/**
		 * Button Save performs following action : gets amount from 
		 * text field and sets amount tendered and amount of cash and then adds this payment
		 * to the sale. Then it goes to POSPayment panel.
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cash.setAmtTendered(new BigDecimal(textField.getText()));
				cash.setAmount(sale.calcAmount(new BigDecimal(textField.getText())));
				System.out.println("Cash Payment Amount :"+cash.getAmount().toString());
				sale.addPayment(cash);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame,store, session, sale));
				currentFrame.getContentPane().revalidate();
				
			}
		});
		btnSave.setBounds(26, 105, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame,store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(149, 105, 117, 29);
		add(btnCancel);

	}
}
