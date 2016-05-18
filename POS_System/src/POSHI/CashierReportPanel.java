package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.Cashier;
import POSPD.Item;
import POSPD.Store;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;

public class CashierReportPanel extends JPanel {
	private JTextField textField;
	private JTextArea textArea;
	/**
	 * Create the panel with passed current frame and store. 
	 */
	public CashierReportPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		//Title of the panel.
		JLabel lblCashierReport = new JLabel("Cashier Report");
		lblCashierReport.setBounds(160, 28, 113, 16);
		add(lblCashierReport);
		//Label that indicates the date of the report.
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(119, 56, 61, 16);
		add(lblDate);
		/**
		 * Text field that has action listener. 
		 * It gets the date specified and sends it as parameter to 
		 * generateCashierReport(). It prints the report into the text area.
		 */
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] rd = textField.getText().split("/");
				String text = generateCashierReport(store,new GregorianCalendar(Integer.parseInt(rd[2]), Integer.parseInt(rd[0])-1,Integer.parseInt(rd[1])));
				textArea.setText(text);
			}
		});
		textField.setBounds(170, 56, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(94, 94, 269, 141);
		add(textArea);
		
		/**
		 * Button Close performs following action: takes user to Home.
		 */
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnClose.setBounds(172, 254, 117, 29);
		add(btnClose);

	}
	/**
	 * generateCashierReport gets passed date and store and prints report for 
	 * 					     cashier for the date.
	 * @param store
	 * @param date
	 * 
	 * @return report
	 */
	public String generateCashierReport(Store store, GregorianCalendar date)
	{
		String report = "";
		String nl = "\n";
		String tab = "\t";
		report  += "Cashier Report for :" +nl;
		report += nl;
		
		for (Entry<String, Cashier> cashierElement: store.getCashiers().entrySet())
		{
			Cashier cashier = cashierElement.getValue();
			report += cashier.getNumber()+" "+cashier.getPerson().getName()+tab+tab+cashier.calcNumberSales(date)+" "+cashier.calcDollarSales(date).toString()+nl;
		}
		return report;
	}
}
