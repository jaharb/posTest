package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionListener;

import POSPD.Register;
import POSPD.Store;
import POSPD.TaxCategory;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;

public class RegisterListPanel extends JPanel {

	private DefaultListModel listModel;
	private JButton btnDelete;
	private JButton btnEdit;
	/**
	 * Create the panel with passed current frame and store.
	 */
	public RegisterListPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		//Title of the panel
		JLabel lblSelectRegister = new JLabel("Select Register");
		lblSelectRegister.setBounds(172, 31, 99, 16);
		add(lblSelectRegister);
		
		//Gets all registers
		listModel = new DefaultListModel();
		for (Entry<String, Register> tcEntry : store.getRegisters().entrySet())
		listModel.addElement(tcEntry.getValue());
		
		//JList with selection listener. When register is selected Edit button is enabled, and if register 
		//that is selected is not used, delete button is enabled too.
		JList list = new JList(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) 
				{
					btnEdit.setEnabled(true);
				}
				
				if (list.getSelectedValue() == null || store.isRegisterUsed((Register)list.getSelectedValue()))
				{
					btnDelete.setEnabled(false);
				}
				else
				{
					btnDelete.setEnabled(true);
				}
					
			}
		});
		list.setBounds(179, 70, 89, 107);
		add(list);
		
		/*
		 * Button Edit passes selected register to Register Edit Panel and takes
		 * user to that panel.
		 */
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterEditPanel registerEditPanel = 
						new RegisterEditPanel(currentFrame,store, (Register)list.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(registerEditPanel);
				currentFrame.revalidate();	
			}
		});
		btnEdit.setBounds(55, 222, 117, 29);
		btnEdit.setEnabled(false);
		add(btnEdit);
		
		/*
		 * Button Add passes new Register() to Register Edit Panel and 
		 * and takes user to that panel.
		 */
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterEditPanel registerEditPanel = 
						new RegisterEditPanel(currentFrame,store, new Register(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(registerEditPanel);
				currentFrame.revalidate();	
			}
		});
		btnAdd.setBounds(172, 222, 117, 29);
		add(btnAdd);
		
		/*
		 * Button Delete removes selected value from the list and store.
		 */
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeRegister((Register)list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
		btnDelete.setBounds(290, 222, 117, 29);
		btnDelete.setEnabled(false);
		add(btnDelete);

	}
}
