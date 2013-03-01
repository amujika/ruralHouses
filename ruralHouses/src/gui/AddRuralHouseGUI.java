package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import businessLogic.AddRuralHouseBL;

import domain.Owner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddRuralHouseGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtHouseNum;
	private JTextField txtTown;
	private JTextField txtDescription;
	private AddRuralHouseBL BL = new AddRuralHouseBL();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRuralHouseGUI frame = new AddRuralHouseGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddRuralHouseGUI() {
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
		lblDescription.setBounds(32, 93, 68, 23);
		contentPane.add(lblDescription);
		
		JLabel lblTown = new JLabel("Town:");
		lblTown.setBounds(223, 34, 46, 14);
		contentPane.add(lblTown);
		
		txtTown = new JTextField();
		txtTown.setBounds(261, 31, 86, 20);
		contentPane.add(txtTown);
		txtTown.setColumns(10);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(109, 94, 238, 107);
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
				
				Owner aux = StartWindow.OWNER;
				BL.addRuralHouse(houseNumber, aux, description, town);
				
				setVisible(false);
				
			}
		});
		btnFinish.setBounds(243, 228, 89, 23);
		
		contentPane.add(btnFinish);
	}
}
