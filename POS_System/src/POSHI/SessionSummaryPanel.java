package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.Session;
import POSPD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class SessionSummaryPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel with passed current frame, store and session.
	 */
	public SessionSummaryPanel(JFrame currentFrame, Store store, Session session) {
		setLayout(null);
		
		//Title of the panel
		JLabel lblSessionSummary = new JLabel("Session Summary");
		lblSessionSummary.setBounds(154, 22, 122, 16);
		add(lblSessionSummary);
		
		//Label that indicates the name of the cashier associated with session
		JLabel lblCashier = new JLabel("Cashier");
		lblCashier.setBounds(29, 59, 61, 16);
		add(lblCashier);
		
		//Label that displays the name of the cashier associated with session
		JLabel label = new JLabel(session.getCashier().toString());
		label.setBounds(102, 59, 61, 16);
		add(label);
		
		//Label that indicates the number of the register associated with session
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(29, 87, 61, 16);
		add(lblRegister);
		
		//Label that displays the number of the register associated with session
		JLabel label_1 = new JLabel(session.getRegister().toString());
		label_1.setBounds(102, 87, 61, 16);
		add(label_1);
		
		//Label that indicates the number of sales
		JLabel lblNumberSales = new JLabel("Number Sales ;");
		lblNumberSales.setBounds(29, 121, 107, 16);
		add(lblNumberSales);
		
		//Text Field that displays the number of sales
		textField = new JTextField(new Integer(session.getNumberSales()).toString());
		textField.setEditable(false);
		textField.setBounds(154, 115, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		//Label that indicates the total amount of sales in dollars
		JLabel lblTotalSales = new JLabel("Total Sales :");
		lblTotalSales.setBounds(29, 149, 90, 16);
		add(lblTotalSales);
		
		//Text Field that displays the total amount of sales in dollars
		textField_1 = new JTextField(session.calcTotal().toString());
		textField_1.setEditable(false);
		textField_1.setBounds(154, 143, 134, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		//Label for message to enter the cash amount
		JLabel lblEnterCash = new JLabel("Enter Cash :");
		lblEnterCash.setBounds(29, 177, 90, 16);
		add(lblEnterCash);
		
		//Text Field that sets Text Field 3 to equal difference between start and end cash amount
		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_3.setText(session.calcCashCountDiff(new BigDecimal(textField_2.getText())).toString());
			}
		});
		textField_2.setBounds(154, 171, 134, 28);
		add(textField_2);
		textField_2.setColumns(10);
		
		//Label that indicates the start and end cash amount difference
		JLabel lblCashCountDiff = new JLabel("Cash Count Diff :");
		lblCashCountDiff.setBounds(29, 205, 122, 16);
		add(lblCashCountDiff);
		
		//Text Field that displays the start and end cash difference
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(154, 199, 134, 28);
		add(textField_3);
		textField_3.setColumns(10);
		
		/**
		 * Button End Session takes user to home screen.
		 */
		JButton btnEndSession = new JButton("End Session");
		btnEndSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEndSession.setBounds(159, 252, 117, 29);
		add(btnEndSession);

	}

}
