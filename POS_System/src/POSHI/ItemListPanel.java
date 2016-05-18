package POSHI;

import javax.swing.JPanel;

import POSPD.Item;
import POSPD.Store;
import POSPD.TaxCategory;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ItemListPanel extends JPanel {
	DefaultListModel listModel;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnAdd;
	/**
	 * Create the panel with passed current frame and store. 
	 */
	public ItemListPanel(JFrame currentFrame,Store store) {
		setLayout(null);
		
		//Title of the panel
		JLabel lblSelectItem = new JLabel("Select Item");
		lblSelectItem.setBounds(180, 26, 94, 16);
		add(lblSelectItem);
		
		//get all items
		listModel = new DefaultListModel();
		for (Entry<String, Item> itemEntry : store.getItems().entrySet())
		listModel.addElement(itemEntry.getValue());
		
		//JList with selection listener. When item is selected edit button gets enabled and if the item is not used, delete button gets enabled too
		JList list = new JList(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) 
				{
					btnEdit.setEnabled(true);
				}
				Item si = (Item)list.getSelectedValue();
				if (list.getSelectedValue() == null || si.isUsed())
				{
					btnDelete.setEnabled(false);
				}
				else
				{
					btnDelete.setEnabled(true);
				}
			}
		});
		list.setBounds(124, 75, 199, 134);
		add(list);
		
		/**
		 * Button edit gets selected item and passes it to new ItemEditPanel
		 */
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemEditPanel itemEditPanel = 
						new ItemEditPanel(currentFrame,store,(Item)list.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(itemEditPanel);
				currentFrame.revalidate();
			}
		});
		btnEdit.setBounds(41, 235, 117, 29);
		btnEdit.setEnabled(false);
		add(btnEdit);
		
		/**
		 * Button Add passes new item instance to ItemEditPanel.
		 */
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemEditPanel itemEditPanel = 
						new ItemEditPanel(currentFrame, store, new Item(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(itemEditPanel);
				currentFrame.revalidate();	
			}
		});
		btnAdd.setBounds(157, 235, 117, 29);
		add(btnAdd);
		
		/**
		 * Button Delete gets selected value and deletes it from the list.
		 */
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeItem((Item)list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
		btnDelete.setBounds(280, 235, 117, 29);
		btnDelete.setEnabled(false);
		add(btnDelete);

	}
}
