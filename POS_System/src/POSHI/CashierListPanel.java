package POSHI;

import javax.swing.JPanel;

import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

import POSPD.Cashier;
import POSPD.Store;
import POSPD.TaxCategory;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CashierListPanel extends JPanel {
	DefaultListModel listModel;
	JList list;
	/**
	 * Create the panel with passed current frame and store. 
	 */
	public CashierListPanel(JFrame currentFrame, Store store) {
		
		setLayout(null);
		
		/**
		 * Button Add performs following action: sets isAdd 
		 * to true, creates new Cashier and passes that to new cashier edit panel.
		 */
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CashierEditPanel cashierEditPanel = 
						new CashierEditPanel(currentFrame,store, new Cashier(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(cashierEditPanel);
				currentFrame.revalidate();	
			}
		});
		btnAdd.setBounds(84, 232, 117, 29);
		add(btnAdd);
		
		/**
		 * Button Edit performs following action: sets isAdd to false, gets selected cashier
		 * and passes that to new cashier edit panel. 
		 * 
		 * This button is not enabled until cashier has being selected.
		 */
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CashierEditPanel cashierEditPanel = 
						new CashierEditPanel(currentFrame,store, (Cashier)list.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(cashierEditPanel);
				currentFrame.revalidate();	
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setBounds(213, 232, 117, 29);
		add(btnEdit);
		/**
		 * Button Delete performs following action: gets selected cashier and removes it 
		 * from the store.
		 * 
		 * This button is not enabled until cashier has been selected. 
		 */
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeCashier((Cashier)list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
		btnDelete.setBounds(327, 232, 117, 29);
		btnDelete.setEnabled(false);
		add(btnDelete);
		
		//gets all of the cashiers from the store.
		listModel = new DefaultListModel();
//		for (Entry<String, Cashier> cashierEntry : store.getCashiers().entrySet())
//		listModel.addElement(cashierEntry.getValue());
		for(Cashier cashier: store.getCashiers().values())
			listModel.addElement(cashier);
		
		//list with all cashiers that has action listener.
		list = new JList(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//if cashier is selected, edit button is enabled
				if (list.getSelectedValue() != null) 
				{
					btnEdit.setEnabled(true);
				}
				//doesn't let deletion of cashier that is being used.
				if (list.getSelectedValue() == null || ((Cashier)list.getSelectedValue()).isUsed())
				{
					btnDelete.setEnabled(false);
				}
				else
				{
					btnDelete.setEnabled(true);
				}
					
			}
			
		});
		list.setBounds(162, 37, 208, 169);
		add(list);

	}
}
