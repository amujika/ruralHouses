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
	private JTextField txtBedRooms;
	private JTextField txtBathRooms;
	private JCheckBox chckbxWifi = new JCheckBox();
	private JTextField txtKitchens;
	private JTextField txtDiningRooms;
	private JTextField txtParkingSpaces;

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

				int bedRooms = Integer.parseInt(txtBedRooms.getText());
				int bathRooms = Integer.parseInt(txtBathRooms.getText());
				int kitchens = Integer.parseInt(txtKitchens.getText());
				int diningRooms = Integer.parseInt(txtDiningRooms.getText());
				int parkingSpaces = Integer.parseInt(txtParkingSpaces.getText());
				Boolean wifi = (Boolean)chckbxWifi.isSelected();
			
				
				rh.setBedRooms(bedRooms);
				rh.setBathRooms(bathRooms);
				rh.setKitchens(kitchens);
				rh.setDiningRooms(diningRooms);
				rh.setParkingSpaces(parkingSpaces);
				rh.setWifi(wifi);

				

				try {
					if(bedRooms<3 || bathRooms<2 || kitchens<1){
						JOptionPane.showMessageDialog(null, "The number of bedrooms should be 3 at least, the number of bathrooms 2 and at leas one kitchen.");
					}else{
					facade.storeRuralHouse(rh);
					setVisible(false);}
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

		JLabel lblBedRooms = new JLabel("Number of bedrooms:");
		lblBedRooms.setBounds(37, 119, 104, 14);
		contentPane.add(lblBedRooms);

		txtBedRooms = new JTextField();
		txtBedRooms.setBounds(151, 116, 42, 20);
		contentPane.add(txtBedRooms);
		txtBedRooms.setColumns(10);

		JLabel lblBathRooms = new JLabel("Number of bathrooms:");
		lblBathRooms.setBounds(33, 150, 108, 14);
		contentPane.add(lblBathRooms);

		JCheckBox chckbxWifi = new JCheckBox("Wifi");
		chckbxWifi.setBackground(new Color(152, 251, 152));
		chckbxWifi.setBounds(271, 184, 97, 23);
		contentPane.add(chckbxWifi);

		txtBathRooms = new JTextField();
		txtBathRooms.setBounds(151, 147, 42, 20);
		contentPane.add(txtBathRooms);
		txtBathRooms.setColumns(10);
		
		JLabel lblKitchens = new JLabel("Numer of kitchens:");
		lblKitchens.setBounds(231, 119, 97, 14);
		contentPane.add(lblKitchens);
		
		JLabel lblDiningRooms = new JLabel("Number of dining rooms:");
		lblDiningRooms.setBounds(203, 150, 125, 14);
		contentPane.add(lblDiningRooms);
		
		txtKitchens = new JTextField();
		txtKitchens.setBounds(326, 116, 42, 20);
		contentPane.add(txtKitchens);
		txtKitchens.setColumns(10);
		
		txtDiningRooms = new JTextField();
		txtDiningRooms.setBounds(326, 147, 42, 20);
		contentPane.add(txtDiningRooms);
		txtDiningRooms.setColumns(10);
		
		JLabel lblParkingSpaces = new JLabel("Number of parking spaces:");
		lblParkingSpaces.setBounds(13, 188, 128, 14);
		contentPane.add(lblParkingSpaces);
		
		txtParkingSpaces = new JTextField();
		txtParkingSpaces.setColumns(10);
		txtParkingSpaces.setBounds(151, 185, 42, 20);
		contentPane.add(txtParkingSpaces);
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
