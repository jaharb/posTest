package POSHI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POSPD.Store;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class POSJFrame extends JFrame {

	private JPanel contentPane;
	private JFrame currentFrame;

	/**
	 * Create the frame with passed store.
	 */
	public POSJFrame(Store store) {
		currentFrame = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		
		//menu bar for the menus
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//menu for menu items
		JMenu mnMaintain = new JMenu("Maintain");
		menuBar.add(mnMaintain);
		
		//menu item in Maintain. When clicked takes user to Store page
		JMenuItem mntmStore = new JMenuItem("Store");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new StoreEditPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmStore);
		
		//menu item in Maintain. When clicked takes user to Cashier page
		JMenuItem mntmCashier = new JMenuItem("Cashier");
		mntmCashier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new CashierListPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmCashier);
		
		//Menu item in Maintain. When clicked takes user to Register page
		JMenuItem mntmRegister = new JMenuItem("Register");
		mntmRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new RegisterListPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmRegister);
		
		//Menu item in Maintain. When clicked takes user to Item page
		JMenuItem mntmItem = new JMenuItem("Item");
		mntmItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ItemListPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmItem);
		
		//Menu item in Maintain. When clicked takes user to Tax Category page
		JMenuItem mntmTaxCategory = new JMenuItem("Tax Category");
		mntmTaxCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new TaxCategoryListPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmTaxCategory);
		
		//Menu for menu items 
		JMenu mnPos = new JMenu("POS");
		menuBar.add(mnPos);
		
		//Menu item in POS. When clicked it takes user to Login page
		JMenuItem mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new POSLogin(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnPos.add(mntmLogin);
		
		//Menu for menu items
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		//Menu item  in Reports. When clicked takes user to Item Report page
		JMenuItem mntmItemReport = new JMenuItem("Item Report");
		mntmItemReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ItemReportPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmItemReport);
		
		//Menu item in Reports. When clicked takes user to Cashier Report page
		JMenuItem mntmCashierReport = new JMenuItem("Cashier Report");
		mntmCashierReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new CashierReportPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmCashierReport);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		getContentPane().removeAll();

		getContentPane().add(new POSHome(store));

		getContentPane().revalidate();
	}
	/**
	 * Launch the application.
	 */

	public static void run(Store store) {
		try {
			JFrame frame = new POSJFrame(store);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
