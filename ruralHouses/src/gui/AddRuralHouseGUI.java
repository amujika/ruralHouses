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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
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
		lblDescription.setBounds(32, 60, 68, 23);
		contentPane.add(lblDescription);

		JLabel lblTown = new JLabel("Town:");
		lblTown.setBounds(223, 34, 46, 14);
		contentPane.add(lblTown);

		txtTown = new JTextField();
		txtTown.setBounds(261, 31, 86, 20);
		contentPane.add(txtTown);
		txtTown.setColumns(10);

		txtDescription = new JTextField();
		txtDescription.setBounds(109, 63, 238, 107);
		contentPane.add(txtDescription);
		txtDescription.setColumns(10);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBack.setBounds(131, 228, 89, 23);
		contentPane.add(btnBack);

		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int houseNumber = Integer.parseInt(txtHouseNum.getText());
				String town = txtTown.getText();
				String description = txtDescription.getText();
				//chooser returns File but we need Image
				String image = chooser.getSelectedFile().getAbsolutePath();

				try {
					facade.addRuralHouse(houseNumber, owner , description, image, town);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				setVisible(false);

			}
		});
	btnFinish.setBounds(243, 228, 89, 23);

		contentPane.add(btnFinish);

		JButton btnImage = new JButton("Image");
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
		btnImage.setBounds(109, 182, 117, 25);
		contentPane.add(btnImage);
	}

}