package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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

public class AddRuralHouseGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtHouseNum;
	private JTextField txtTown;
	private JTextField txtDescription;
	private JFileChooser chooser; 

//	private AddRuralHouseBL BL = new AddRuralHouseBL();
	private ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();

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
		lblHouseNum.setBounds(32, 21, 85, 41);
		contentPane.add(lblHouseNum);

		txtHouseNum = new JTextField();
		txtHouseNum.setBounds(109, 31, 86, 20);
		contentPane.add(txtHouseNum);
		txtHouseNum.setColumns(10);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(32, 73, 68, 23);
		contentPane.add(lblDescription);

		JLabel lblTown = new JLabel("Town:");
		lblTown.setBounds(223, 34, 46, 14);
		contentPane.add(lblTown);

		txtTown = new JTextField();
		txtTown.setBounds(261, 31, 86, 20);
		contentPane.add(txtTown);
		txtTown.setColumns(10);

		txtDescription = new JTextField();
		txtDescription.setBounds(109, 77, 238, 142);
		contentPane.add(txtDescription);
		txtDescription.setColumns(10);

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
				String description = txtDescription.getText();
				//chooser returns File but we need Image
				String image = chooser.getSelectedFile().getAbsolutePath();

				try {
					facade.addRuralHouse(houseNumber, owner , description, image, town);
					StartWindow.OWNER = facade.ownerloginBL(new Owner(owner.getName(),owner.getUsername(), owner.getPassword()));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				setVisible(false);

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
		btnImage.setBounds(172, 230, 117, 25);
		contentPane.add(btnImage);
	}

}