package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;


import businessLogic.ApplicationFacadeInterface;

import domain.Owner;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class AddRuralHouseGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtHouseNum;
	private JTextField txtTown;
	private JTextField txtBedRooms;
	private JTextField txtBathRooms;
	private JCheckBox chckbxWifi = new JCheckBox();
	private JFileChooser chooser; 

	//	private AddRuralHouseBL BL = new AddRuralHouseBL();
	private ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
	private JTextField txtKitchens;
	private JTextField txtDiningRooms;
	private JTextField txtParkingSpaces;

	/**
	 * Create the frame.
	 */
	public AddRuralHouseGUI(final Owner owner) {
		setBounds(100, 100, 460, 360);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHouseNum = new JLabel("House number:");
		lblHouseNum.setBounds(32, 21, 93, 41);
		contentPane.add(lblHouseNum);

		txtHouseNum = new JTextField();
		txtHouseNum.setBounds(121, 31, 74, 20);
		contentPane.add(txtHouseNum);
		txtHouseNum.setColumns(10);

		JLabel lblBedRooms = new JLabel("Number of bedrooms:");
		lblBedRooms.setBounds(32, 91, 107, 23);
		contentPane.add(lblBedRooms);

		JLabel lblTown = new JLabel("Town:");
		lblTown.setBounds(223, 34, 46, 14);
		contentPane.add(lblTown);

		txtTown = new JTextField();
		txtTown.setBounds(261, 31, 86, 20);
		contentPane.add(txtTown);
		txtTown.setColumns(10);

		txtBedRooms = new JTextField();
		txtBedRooms.setBounds(149, 92, 46, 20);
		contentPane.add(txtBedRooms);
		txtBedRooms.setColumns(10);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(152, 251, 152));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(109, 266, 89, 23);
		contentPane.add(btnCancel);

		JButton btnAccept = new JButton("Accept");
		btnAccept.setBackground(new Color(152, 251, 152));
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int houseNumber = Integer.parseInt(txtHouseNum.getText());
				String town = txtTown.getText();
				int bedRooms = Integer.parseInt(txtBedRooms.getText());
				int bathRooms = Integer.parseInt(txtBathRooms.getText());
				int kitchens = Integer.parseInt(txtKitchens.getText());
				int diningRooms = Integer.parseInt(txtDiningRooms.getText());
				int parkingSpaces = Integer.parseInt(txtParkingSpaces.getText());
				Boolean wifi = (Boolean)chckbxWifi.isSelected();
				//chooser returns File but we need Image
				String image = null;
		        if (chooser != null)
		        	image = chooser.getSelectedFile().getAbsolutePath();

				try {
					if(bedRooms<3 || bathRooms<2 || kitchens<1){
						JOptionPane.showMessageDialog(null, "The number of bedrooms should be 3 at least, the number of bathrooms 2 and at leas one kitchen.");
					}
					else{
					facade.addRuralHouse(houseNumber, owner , bedRooms, bathRooms, kitchens, diningRooms, parkingSpaces, wifi, image, town);
					StartWindow.OWNER = facade.ownerloginBL(new Owner(owner.getName(),owner.getUsername(), owner.getPassword()));
					setVisible(false);}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				

			}
		});
		btnAccept.setBounds(258, 266, 89, 23);

		contentPane.add(btnAccept);

		JButton btnImage = new JButton("Search image");
		btnImage.setBackground(new Color(152, 251, 152));
		btnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new java.io.File("."));
				String choosertitle = null;
				chooser.setDialogTitle(choosertitle);
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setAcceptAllFileFilterUsed(true);

				//Print for test if it works
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					System.out.println("getCurrentDirectory(): " 
							+  chooser.getCurrentDirectory());
					System.out.println("getSelectedFile() : " 
							+  chooser.getSelectedFile());
				}
				else {
					System.out.println("No Selection ");
				}
			}
		});
		btnImage.setBounds(172, 216, 117, 25);
		contentPane.add(btnImage);

		JLabel lblBathRooms = new JLabel("Number of bathrooms:");
		lblBathRooms.setBounds(31, 126, 108, 14);
		contentPane.add(lblBathRooms);

		txtBathRooms = new JTextField();
		txtBathRooms.setBounds(149, 123, 46, 20);
		contentPane.add(txtBathRooms);
		txtBathRooms.setColumns(10);

		JCheckBox chckbxWifi = new JCheckBox("Wifi");
		chckbxWifi.setBackground(new Color(152, 251, 152));
		chckbxWifi.setBounds(224, 156, 97, 23);
		contentPane.add(chckbxWifi);
		
		JLabel lblKitchens = new JLabel("Number of kitchens:");
		lblKitchens.setBounds(204, 95, 97, 14);
		contentPane.add(lblKitchens);
		
		JLabel lblDiningRooms = new JLabel("Number of dining rooms:");
		lblDiningRooms.setBounds(204, 125, 133, 14);
		contentPane.add(lblDiningRooms);
		
		txtKitchens = new JTextField();
		txtKitchens.setBounds(331, 92, 46, 20);
		contentPane.add(txtKitchens);
		txtKitchens.setColumns(10);
		
		txtDiningRooms = new JTextField();
		txtDiningRooms.setBounds(331, 122, 46, 20);
		contentPane.add(txtDiningRooms);
		txtDiningRooms.setColumns(10);
		
		JLabel lblParkingSpaces = new JLabel("Number of parking spaces:");
		lblParkingSpaces.setBounds(10, 160, 133, 14);
		contentPane.add(lblParkingSpaces);
		
		txtParkingSpaces = new JTextField();
		txtParkingSpaces.setBounds(149, 157, 46, 20);
		contentPane.add(txtParkingSpaces);
		txtParkingSpaces.setColumns(10);
	}
}