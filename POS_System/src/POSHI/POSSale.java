package POSHI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.*;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class POSSale extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private Sale currentSale;
	private JLabel lblMessage;
	private JList list;
	private DefaultListModel<SaleLineItem> listModel;
	private JCheckBox chckbxTaxFree;
	private JButton btnCompleteSale;
	private JLabel lblCashier;
	private JLabel lblRegister;
	private JLabel label;
	private JLabel label_1;
	private JButton btnEndSession;


	/**
	 * Create the panel with passed current frame, store, session and sale.
	 */
	public POSSale(JFrame currentFrame, Store store, Session session,Sale sale) {
		
		 
		currentSale = sale;
		
		setLayout(null);
		
		
		/**
		 * Text Field with action listener. When UPC is entered, Item associated with
		 * this UPC is found. If item is not found, error message is displayed. If found, it 
		 * creates new SaleLineItem, finds quantity, calculates and displays subtotal, total, tax
		 * and if payment is enough it enables end sale payment. 
		 */
		textField = new JTextField();
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = store.findItemForUPC(textField.getText());
				if (item ==null)
				{  
					textField.setText("");
					lblMessage.setText("Item not found. Enter UPC again.");
					textField.requestFocusInWindow();
				}
				else
				{
					int quantity = Integer.parseInt(textField_1.getText());
					SaleLineItem sli = new SaleLineItem(currentSale,item,quantity);
					listModel.addElement(sli);
					textField_2.setText(currentSale.calcSubTotal().toString());
					textField_3.setText(currentSale.calcTax().toString());
					textField_4.setText(currentSale.calcTotal().toString());
					textField.setText("");
					lblMessage.setText("");
					textField_1.setText("1");
					if (currentSale.isPaymentEnough()) btnCompleteSale.setEnabled(true);
					else btnCompleteSale.setEnabled(false);
					textField.requestFocusInWindow();
					btnEndSession.setEnabled(false);
					 
				}
				
			}
		});
		textField.setBounds(47, 69, 134, 28);
		add(textField);
		
		//Title of the panel
		JLabel lblSale = new JLabel("Sale");
		lblSale.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSale.setBounds(235, 18, 61, 16);
		add(lblSale);
		
		//Label for the error message.
		lblMessage = new JLabel("");
		lblMessage.setForeground(Color.RED);
		lblMessage.setBounds(47, 46, 280, 16);
		add(lblMessage);
		
		//Label for message to indicate that following is item.
		JLabel lblItem = new JLabel("Item :");
		lblItem.setBounds(6, 75, 61, 16);
		add(lblItem);
		
		//Label for message to indicate quantity of the item.
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setBounds(193, 75, 74, 16);
		add(lblQuantity);
		
		textField_1 = new JTextField("1");
		textField_1.setBounds(266, 69, 61, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		//gets all of the sale line items for the current sale.
		listModel = new DefaultListModel();
		for (SaleLineItem sli : currentSale.getSaleLineItems()) listModel.addElement(sli);
		list = new JList(listModel);
		list.setBounds(16, 103, 280, 106);
		add(list);
		
		//Label that indicates subtotal
		JLabel lblSubtotal = new JLabel("SubTotal :");
		lblSubtotal.setBounds(308, 113, 64, 16);
		add(lblSubtotal);
		
		//Text Field that displays the subtotal
		textField_2 = new JTextField(sale.calcSubTotal().toString());
		textField_2.setBounds(384, 107, 92, 28);
		add(textField_2);
		textField_2.setColumns(10);
		
		//Label that indicates the tax
		JLabel lblTax = new JLabel("Tax :");
		lblTax.setBounds(341, 147, 31, 16);
		add(lblTax);
		
		//Text Field that displays the tax
		textField_3 = new JTextField(sale.calcTax().toString());
		textField_3.setBounds(384, 141, 92, 28);
		add(textField_3);
		textField_3.setColumns(10);
		
		//Label that indicates the total
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setBounds(332, 178, 40, 16);
		add(lblTotal);
		
		//Text Field that displays the total
		textField_4 = new JTextField(sale.calcTotal().toString());
		textField_4.setBounds(384, 181, 92, 28);
		add(textField_4);
		textField_4.setColumns(10);
		
		/**
		 * Button Payment takes user to POSPayment panel and passes current session and
		 * sale to that panel.
		 */
		JButton btnPaymnet = new JButton("Payment");
		btnPaymnet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame,store, session,sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnPaymnet.setBounds(6, 226, 117, 29);
		add(btnPaymnet);
		
		//Label that indicates the amount tendered
		JLabel lblAmtTendered = new JLabel("Amt Tendered :");
		lblAmtTendered.setBounds(276, 225, 96, 16);
		add(lblAmtTendered);
		
		//TextField that displays the amount tendered
		textField_5 = new JTextField(sale.calcAmtTendered().toString());
		textField_5.setBounds(384, 223, 92, 28);
		add(textField_5);
		textField_5.setColumns(10);
		
		//Label that indicates the change
		JLabel lblChange = new JLabel("Change :");
		lblChange.setBounds(311, 253, 61, 16);
		add(lblChange);
		
		//Text Field that displays the change
		textField_6 = new JTextField(sale.calcChange().toString());
		textField_6.setBounds(384, 257, 92, 28);
		add(textField_6);
		textField_6.setColumns(10);
		
		/**
		 * Button Cancel Sale creates new Sale and passes it to new POSSale panel and 
		 * takes user to that panel.
		 */
		JButton btnCancelSale = new JButton("Cancel Sale");
		btnCancelSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSSale(currentFrame,store,session,new Sale()));
				currentFrame.getContentPane().revalidate(); 
			}
		});
		btnCancelSale.setBounds(6, 267, 117, 29);
		add(btnCancelSale);
		
		/**
		 * Button End Session takes user to Session Summary Panel that displays info
		 * about this session.
		 */
		btnEndSession = new JButton("End Session");
		btnEndSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new SessionSummaryPanel(currentFrame, store,session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEndSession.setBounds(115, 267, 117, 29);
		if (sale.getSaleLineItems().size()==0)
			btnEndSession.setEnabled(true);
		else
			btnEndSession.setEnabled(false);
		add(btnEndSession);
		
		//Check box if selected sets current sale to be tax free. Otherwise it calculates the tax and total with tax.
		chckbxTaxFree = new JCheckBox("Tax Free");
		if (sale.getTaxFree())chckbxTaxFree.doClick();
		chckbxTaxFree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbxTaxFree.isSelected())
				{
					currentSale.setTaxFree(true);
				}
				else
				{
					currentSale.setTaxFree(false);
				}
				textField_3.setText(sale.calcTax().toString());
				textField_4.setText(sale.calcTotal().toString());
			}
		});
		chckbxTaxFree.setBounds(374, 46, 102, 23);
		add(chckbxTaxFree);
		
		/**
		 * Button Complete Sale creates new sale, passes that sale to new POSSale panel
		 * and takes user to that panel. This button is enabled if payment is enough.
		 */
		btnCompleteSale = new JButton("Complete Sale");
		btnCompleteSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.addSale(currentSale);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSSale(currentFrame,store,session,new Sale()));
				currentFrame.getContentPane().revalidate();
			}
		});
		if (currentSale.isPaymentEnough()) btnCompleteSale.setEnabled(true);
		else btnCompleteSale.setEnabled(false);
		btnCompleteSale.setBounds(115, 226, 117, 29);
		add(btnCompleteSale);
		
		//Label that indicates cashier
		lblCashier = new JLabel("Cashier :");
		lblCashier.setBounds(6, 6, 61, 16);
		add(lblCashier);
		
		//Label that indicates register
		lblRegister = new JLabel("Register :");
		lblRegister.setBounds(6, 29, 61, 16);
		add(lblRegister);
		
		//label that prints out cashier's name
		label = new JLabel(session.getCashier().toString());
		label.setBounds(67, 6, 61, 16);
		add(label);
		
		//label that prints out number of the register.
		label_1 = new JLabel(session.getRegister().getNumber());
		label_1.setBounds(67, 29, 61, 16);
		add(label_1);
		

	}
}
