package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.*;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class UPCEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel with passed current frame, store, item, upc, parameter
	 * that indicates if item is one that is being added and parameter that indicates
	 * if the upc is one that is being added or edited.
	 */
	public UPCEditPanel(JFrame currentFrame, Store store, Item item, UPC upc,boolean isItemAdd, boolean isAdd) {
		setLayout(null);
		
		//Title of the panel
		JLabel lblNewLabel = new JLabel("Edit UPC");
		lblNewLabel.setBounds(188, 26, 61, 16);
		add(lblNewLabel);
		
		//Label that indicates the upc code
		JLabel lblUpcCode = new JLabel("UPC Code :");
		lblUpcCode.setBounds(78, 75, 94, 16);
		add(lblUpcCode);
		
		//Text Field that displays the upc.
		textField = new JTextField(upc.getUpc());
		textField.setBounds(167, 69, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		/**
		 * Button Save determines if the upc is the new in the store. If it is
		 * it just adds it. If it is already in the store it removes it and adds the
		 * edited version. It takes user to Item Edit Panel.
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd && !upc.getUpc().equals(textField.getText()))
				{
					store.removeUPC(upc);
					item.removeUPC(upc);
					upc.setUpc(textField.getText());
					upc.setItem(item);
					item.addUPC(upc);
					store.addUPC(upc);
				}
				if (isAdd) 
				{
					upc.setUpc(textField.getText());
					upc.setItem(item);
					item.addUPC(upc);
					store.addUPC(upc); 
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame,store,item,isItemAdd));
				currentFrame.getContentPane().revalidate();
			}
			
		});
		btnSave.setBounds(89, 211, 117, 29);
		add(btnSave);
		
		/**
		 * Button Cancel takes user to Item Edit Panel.
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame,store,item,isItemAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(230, 211, 117, 29);
		add(btnCancel);

	}
}
