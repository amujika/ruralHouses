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
import javax.swing.JTextField;


import businessLogic.ApplicationFacadeInterface;

import domain.Owner;
import domain.RuralHouse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JCheckBox;

public class ChangePropertiesRuralHouseGUI extends JFrame {

	private JPanel contentPane = null;

	//	private RemoveRuralHouseBL BL = new RemoveRuralHouseBL();
	private ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();

	private DefaultComboBoxModel<RuralHouse> ruralHouses = new DefaultComboBoxModel<RuralHouse>();
	private JTextField txtNumberOfRooms;
	private JTextField txtNumberOfBeds;
	private JCheckBox chckbxWifi = new JCheckBox();

	/**
	 * Create the frame.
	 */
	public ChangePropertiesRuralHouseGUI() {
		setBounds(100, 100, 460, 358);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.add(getRuralHousesJCB());

		JLabel lblChooseRH = new JLabel("Choose a rural house to change it's properties:");
		lblChooseRH.setBounds(97, 31, 231, 20);
		contentPane.add(lblChooseRH);

		JButton btnAccept = new JButton("Accept");
		btnAccept.setBackground(new Color(152, 251, 152));
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RuralHouse rh = (RuralHouse) ruralHouses.getSelectedItem();

				int numberOfRooms = Integer.parseInt(txtNumberOfRooms.getText());
				int numberOfBeds = Integer.parseInt(txtNumberOfBeds.getText());
				Boolean wifi = (Boolean)chckbxWifi.isSelected();
			
				rh.setNumberOfRooms(numberOfRooms);
				rh.setNumberOfBeds(numberOfBeds);
				rh.setWifi(wifi);

				setVisible(false);

				try {
					facade.storeRuralHouse(rh);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAccept.setBounds(239, 258, 89, 23);
		contentPane.add(btnAccept);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(152, 251, 152));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(97, 258, 89, 23);
		contentPane.add(btnCancel);

		JLabel lblNumberOfRooms = new JLabel("Number of Rooms:");
		lblNumberOfRooms.setBounds(97, 119, 80, 14);
		contentPane.add(lblNumberOfRooms);

		txtNumberOfRooms = new JTextField();
		txtNumberOfRooms.setBounds(187, 116, 141, 20);
		contentPane.add(txtNumberOfRooms);
		txtNumberOfRooms.setColumns(10);

		JLabel lblNumberOfBeds = new JLabel("Number of Beds:");
		lblNumberOfBeds.setBounds(97, 150, 80, 14);
		contentPane.add(lblNumberOfBeds);

		JCheckBox chckbxWifi = new JCheckBox("Wifi");
		chckbxWifi.setBounds(187, 174, 97, 23);
		contentPane.add(chckbxWifi);

		txtNumberOfBeds = new JTextField();
		txtNumberOfBeds.setBounds(187, 147, 141, 20);
		contentPane.add(txtNumberOfBeds);
		txtNumberOfBeds.setColumns(10);
	}

	private JComboBox getRuralHousesJCB(){
		JComboBox JCBChooseRH = new JComboBox();
		JCBChooseRH.setBounds(97, 62, 231, 20);
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
