package POSHI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoreEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel with passed current frame and store.
	 */
	public StoreEditPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		//Title of the panel
		JLabel lblEditStore = new JLabel("Edit Store");
		lblEditStore.setBounds(193, 27, 61, 16);
		add(lblEditStore);
		
		//Label that indicates the name of the store
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(40, 70, 61, 16);
		add(lblName);
		
		//Text Field that displays the name of the store
		textField = new JTextField(store.getName());
		textField.setBounds(103, 64, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		/**
		 * Button Save saves new name of the store and takes user to home screen.
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.setName(textField.getText());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(69, 216, 117, 29);
		add(btnSave);
		
		/**
		 * Button Cancel takes user back to home screen.
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(215, 216, 117, 29);
		add(btnCancel);

	}
}
