package POSHI;

import javax.swing.JPanel;

import POSPD.Store;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class POSHome extends JPanel {

	/**
	 * Create the panel with passed store.
	 */
	public POSHome(Store store) {
		setLayout(null);
		
		//Prints out name of the store.
		JLabel lblNewLabel = new JLabel(store.getName());
		lblNewLabel.setBounds(111, 69, 235, 16);
		add(lblNewLabel);

	}

}
