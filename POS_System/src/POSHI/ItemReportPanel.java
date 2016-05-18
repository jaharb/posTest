package POSHI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.Item;
import POSPD.Store;


import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;

public class ItemReportPanel extends JPanel {
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Create the panel with passed current frame and store.
	 */
	public ItemReportPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		//Title of the panel
		JLabel lblItemReport = new JLabel("Item Report");
		lblItemReport.setBounds(168, 23, 94, 16);
		add(lblItemReport);
		
		//Label for the message to enter date for the report
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(119, 51, 61, 16);
		add(lblDate);
		
		//Text field to enter the date
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] rd = textField.getText().split("/");
				String text = generateItemReport(store,new GregorianCalendar(Integer.parseInt(rd[2]), Integer.parseInt(rd[0])-1,Integer.parseInt(rd[1])));
				textArea.setText(text);
			}
		});
		textField.setBounds(197, 45, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		/**
		 * Button Close takes user to POSHome
		 */
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnClose.setBounds(168, 265, 117, 29);
		add(btnClose);
		
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(88, 87, 265, 161);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(88, 87, 265, 161);
	//	add(textArea);
		add(scrollPane);
		
		
	}
	/**
	 * generateItemReport same as generateCashierReport gets store and date passed
	 *  				  and prints all of the sold items for that date.
	 * @param store
	 * @param date
	 * 
	 * @return report
	 */
	public String generateItemReport(Store store, GregorianCalendar date)
	{
		String report = "";
		String nl = "\n";
		String tab = "\t";
		report  += "Item Report for :" +nl;
		report += nl;
		
		for (Entry<String, Item> itemElement: store.getItems().entrySet())
		{
			Item item = itemElement.getValue();
			report += item.getNumber()+" "+item.getDescription()+tab+tab+item.calcItemSoldCount(date)+nl;
		}
		return report;
	}
}
