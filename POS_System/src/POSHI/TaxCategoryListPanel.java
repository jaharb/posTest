package POSHI;

import javax.swing.JPanel;

import POSPD.*;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class TaxCategoryListPanel extends JPanel {
	JList list;
	JButton btnDelete;
	JButton btnEdit;
	DefaultListModel listModel;
	/**
	 * Create the panel with passed current frame and store.
	 */
	public TaxCategoryListPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		/**
		 * Button Edit passes selected value to Tax Category Edit Panel and takes
		 * user to that panel. It is not enabled until value is selected.
		 */
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxCategoryEditPanel tcEditPanel = 
						new TaxCategoryEditPanel(currentFrame,store, (TaxCategory)list.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(tcEditPanel);
				currentFrame.revalidate();	
			}
		});
		btnEdit.setBounds(42, 221, 117, 29);
		btnEdit.setEnabled(false);
		add(btnEdit);
		
		/**
		 * Button Add passes new TaxCategory() to Tax Category Edit Panel and
		 * it takes user to that panel. It is enabled no matter what. 
		 */
		JButton btnCancel = new JButton("Add");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxCategoryEditPanel tcEditPanel = 
						new TaxCategoryEditPanel(currentFrame,store, new TaxCategory(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(tcEditPanel);
				currentFrame.revalidate();	
			}
		});
		btnCancel.setBounds(171, 221, 117, 29);
		add(btnCancel);
		
		/**
		 * Button Delete deletes selected value.
		 */
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeTaxCategory((TaxCategory)list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
		btnDelete.setBounds(300, 221, 117, 29);
		btnDelete.setEnabled(false);
		add(btnDelete);
		
		//gets all of the tax categories.
		listModel = new DefaultListModel();
		for (Entry<String, TaxCategory> tcEntry : store.getTaxCategories().entrySet())
		listModel.addElement(tcEntry.getValue());
		
		//JList with selection listener. When value gets selected it enables the edit button and it enables delete button too if value is not used
		list = new JList(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (list.getSelectedValue() != null) 
				{
					btnEdit.setEnabled(true);
				}
				
				if (list.getSelectedValue() == null || store.isTaxCategoryUsed((TaxCategory)list.getSelectedValue()))
				{
					btnDelete.setEnabled(false);
				}
				else
				{
					btnDelete.setEnabled(true);
				}
					
			}
		});
		list.setBounds(158, 52, 152, 149);
		add(list);

	}
}
