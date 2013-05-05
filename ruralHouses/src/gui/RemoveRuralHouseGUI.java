package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import businessLogic.AddRuralHouseBL;
import businessLogic.ApplicationFacadeInterface;
import businessLogic.RemoveRuralHouseBL;

import domain.Owner;
import domain.RuralHouse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Vector;

public class RemoveRuralHouseGUI extends JFrame {

	private JPanel contentPane = null;	

//	private RemoveRuralHouseBL BL = new RemoveRuralHouseBL();
	private ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();

	private DefaultComboBoxModel<RuralHouse> ruralHouses = new DefaultComboBoxModel<RuralHouse>();

	/**
	 * Create the frame.
	 */

	public RemoveRuralHouseGUI() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		contentPane.add(getRuralHousesJCB());



		JLabel lblChooseRH = new JLabel("Choose a rural house to remove:");
		lblChooseRH.setBounds(97, 36, 206, 20);
		contentPane.add(lblChooseRH);

		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RuralHouse rh = (RuralHouse) ruralHouses.getSelectedItem();			
				try {
					facade.RemoveRuralHouse(rh);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnAccept.setBounds(97, 201, 89, 23);
		contentPane.add(btnAccept);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(214, 201, 89, 23);
		contentPane.add(btnCancel);
	}

	private JComboBox getRuralHousesJCB(){
		JComboBox JCBChooseRH = new JComboBox();
		JCBChooseRH.setBounds(97, 69, 206, 20);
		Owner owner = StartWindow.OWNER;
		Vector<RuralHouse> houseList = null;

		try {
			//Obtain the business logic from a StartWindow class (local or remote)
			ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
			houseList=facade.getRuralHouses(owner);
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}

		if (houseList.isEmpty()) 
			System.out.println("Owner does not exist or has no registered houses ");

		for (RuralHouse v : houseList)
			ruralHouses.addElement(v);

		JCBChooseRH.setModel(ruralHouses);


		return JCBChooseRH;
	}
}
