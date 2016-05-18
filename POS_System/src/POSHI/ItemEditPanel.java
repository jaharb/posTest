package POSHI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Item;
import POSPD.Price;
import POSPD.Store;
import POSPD.TaxCategory;
import POSPD.UPC;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ItemEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	private DefaultListModel upcListModel;
	private DefaultListModel priceListModel;
	private JButton btnUpcEdit;
	private JButton btnEdit_1;

	/**
	 * Create the panel with passed current frame, store, item and parameter that
	 * indicates if it is new item that is being edited or the one that is currently
	 * in the inventory.
	 */
	public ItemEditPanel(JFrame currentFrame, Store store, Item item, boolean isAdd) {
		setLayout(null);
		
		//title of the panel
		JLabel lblEditItem = new JLabel("Edit Item");
		lblEditItem.setBounds(186, 23, 56, 16);
		add(lblEditItem);
		
		//label for the message to enter the item number
		JLabel lblItemNumber = new JLabel("Item Number : ");
		lblItemNumber.setBounds(6, 57, 94, 16);
		add(lblItemNumber);
		
		//text field to enter the item number
		textField = new JTextField(item.getNumber());
		textField.setBounds(102, 51, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		//label for the message to enter the name of the item
		JLabel lblDescription = new JLabel("Description : ");
		lblDescription.setBounds(6, 91, 94, 16);
		add(lblDescription);
		
		//text field to enter the name of the item
		textField_1 = new JTextField(item.getDescription());
		textField_1.setBounds(102, 85, 194, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		//label for the message to enter the tax category of the item
		JLabel lblTaxCategory = new JLabel("Tax Category : ");
		lblTaxCategory.setBounds(6, 126, 110, 16);
		add(lblTaxCategory);
		
		//combo box for the tax categories. If it is old item that is being edited, old value is passed
		DefaultComboBoxModel tcList = new DefaultComboBoxModel(store.getTaxCategoryList().toArray());
		comboBox = new JComboBox(tcList);
		
		if(!isAdd) comboBox.setSelectedItem(item.getTaxCategory());
		
		comboBox.setBounds(102, 122, 134, 27);
		add(comboBox);
		
		/**
		 * Button Save performs following action: If the item that is being edited
		 * is one that is already in inventory, it deletes this item and it adds new 
		 * edited item to inventory. However, if the item that is being edited is
		 * new item, not already in inventory, it just adds it to the store.
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd && !item.getNumber().equals(textField.getText()))
				{
					store.removeItem(item);
					item.setNumber(textField.getText());
					store.addItem(item);;
				}
				if (isAdd) 
				{
					item.setNumber(textField.getText());
					store.addItem(item);
				}
				
				item.setDescription(textField_1.getText());
				item.setTaxCategory((TaxCategory) comboBox.getSelectedItem());
			
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(36, 253, 117, 29);
		add(btnSave);
		
		/**
		 * Button Cancel takes user to ItemListPanel panel.
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(165, 253, 117, 29);
		add(btnCancel);
		//gets upcs 
		upcListModel = new DefaultListModel();
		for (Entry<String, UPC> upcEntry :item.getUpcs().entrySet())
			upcListModel.addElement(upcEntry.getValue());
		
		//JList with selection listener. When upc is selected, edit upc button is enabled 
		JList upcList = new JList(upcListModel);
		upcList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (upcList.getSelectedValue() != null) 
				{
					btnUpcEdit.setEnabled(true);
				}
			}
		});
		upcList.setBounds(308, 23, 117, 67);
		add(upcList);
		
		/**
		 * Button Edit performs following action: 
		 * gets selected upc and passes it to new UPCEditPanel.
		 */
		btnUpcEdit = new JButton("Edit");
		btnUpcEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UPCEditPanel upcEditPanel = 
						new UPCEditPanel(currentFrame,store,item,(UPC)upcList.getSelectedValue(),isAdd,false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(upcEditPanel);
				currentFrame.revalidate();
			}
		});
		btnUpcEdit.setBounds(304, 102, 68, 29);
		btnUpcEdit.setEnabled(false);
		add(btnUpcEdit);
		
		/**
		 * Button Add performs following action: 
		 * creates new UPCEditPanel and it passes new UPC() and true for isAdd
		 */
		JButton btnUpcAdd = new JButton("Add");
		btnUpcAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UPCEditPanel upcEditPanel = 
						new UPCEditPanel(currentFrame,store,item,new UPC(),isAdd, true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(upcEditPanel);
				currentFrame.revalidate();
			}
		});
		btnUpcAdd.setBounds(370, 102, 62, 29);
		add(btnUpcAdd);
		
		/**
		 * Button Delete removes upc from store and from item.
		 */
		JButton btnUpcDelete = new JButton("Delete");
		btnUpcDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeUPC((UPC)upcList.getSelectedValue());
				item.removeUPC((UPC)upcList.getSelectedValue());
				upcListModel.removeElement(upcList.getSelectedValue());
			}
		});
		btnUpcDelete.setBounds(337, 130, 75, 29);
		add(btnUpcDelete);
		
		//gets prices
		priceListModel = new DefaultListModel();
		for (Price price :item.getPrices() )
		priceListModel.addElement(price);
		
		//JList with selection listener. When price is selected, price edit button is enabled.
		JList priceList = new JList(priceListModel);
		priceList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (priceList.getSelectedValue() != null) 
				{
					btnEdit_1.setEnabled(true);
				}
			}
		});
		priceList.setBounds(308, 168, 123, 51);
		add(priceList);
		
		/**
		 * Button Edit (for price) passes selected price and item associated with price
		 * to the price edit panel.
		 */
		btnEdit_1 = new JButton("Edit");
		btnEdit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PriceEditPanel priceEditPanel = 
						new PriceEditPanel(currentFrame,store,item,(Price)priceList.getSelectedValue(),isAdd,false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(priceEditPanel);
				currentFrame.revalidate();
			}
		});
		btnEdit_1.setBounds(295, 235, 75, 29);
		btnEdit_1.setEnabled(false);
		add(btnEdit_1);
		
		/**
		 * Button Add(price) creates new Price and passes it and isAdd as true to new
		 * PriceEditPanel.
		 */
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PriceEditPanel priceEditPanel = 
						new PriceEditPanel(currentFrame,store,item,new Price(),isAdd, true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(priceEditPanel);
				currentFrame.revalidate();
			}
		});
		btnAdd_1.setBounds(370, 235, 62, 29);
		add(btnAdd_1);
		
		/**
		 * Button Delete (for price) gets selected price and deletes it.
		 */
		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				item.removePrice((Price)upcList.getSelectedValue());
				priceListModel.removeElement(priceList.getSelectedValue());
			}
		});
		btnDelete_1.setBounds(331, 265, 68, 29);
		add(btnDelete_1);

	}
}
