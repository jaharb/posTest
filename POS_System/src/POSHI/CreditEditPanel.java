package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.Check;
import POSPD.Credit;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class CreditEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox comboBox;

	/**
	 * Create the panel with passed current frame, session, sale and credit.
	 */
	public CreditEditPanel(JFrame currentFrame, Store store,Session session, Sale sale, Credit credit ) {
		setLayout(null);
		
		//title of the panel
		JLabel lblEnterCreditPayment = new JLabel("Enter Credit Payment");
		lblEnterCreditPayment.setBounds(53, 24, 148, 16);
		add(lblEnterCreditPayment);
		
		JLabel lblAmount = new JLabel("Amount :");
		lblAmount.setBounds(30, 52, 61, 16);
		add(lblAmount);
		
		textField = new JTextField();
		textField.setBounds(117, 46, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblCardType = new JLabel("Card Type :");
		lblCardType.setBounds(23, 100, 80, 16);
		add(lblCardType);
		
		String[] cardType = {"Visa","Master Card", "AMEX"};
		comboBox = new JComboBox(cardType);
		comboBox.setBounds(117, 96, 134, 27);
		add(comboBox);
		
		JLabel lblAccontNumber = new JLabel("Accont Num :");
		lblAccontNumber.setBounds(23, 145, 98, 16);
		add(lblAccontNumber);
		
		textField_1 = new JTextField();
		textField_1.setBounds(117, 139, 134, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblExpireDate = new JLabel("Expire Date :");
		lblExpireDate.setBounds(23, 188, 80, 16);
		add(lblExpireDate);
		
		textField_2 = new JTextField();
		textField_2.setBounds(117, 179, 134, 28);
		add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				credit.setAmount(new BigDecimal(textField.getText()));
				credit.setCardType((String)comboBox.getSelectedItem());
				credit.setAcctNumber(textField_1.getText());
				String[] ed = textField_2.getText().split("/");
				credit.setExpireDate(new GregorianCalendar(Integer.parseInt(ed[1]),Integer.parseInt(ed[0])-1,1));
				
				sale.addPayment(credit);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame,store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(30, 219, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame,store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(159, 219, 117, 29);
		add(btnCancel);

	}
}
