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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHouseNumber = new JLabel("House number:");
		lblHouseNumber.setBounds(32, 21, 85, 41);
		contentPane.add(lblHouseNumber);
		
		textField = new JTextField();
		textField.setBounds(109, 31, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(32, 93, 68, 23);
		contentPane.add(lblDescription);
		
		JLabel lblTown = new JLabel("Town:");
		lblTown.setBounds(223, 34, 46, 14);
		contentPane.add(lblTown);
		
		textField_1 = new JTextField();
		textField_1.setBounds(261, 31, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(109, 94, 238, 107);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnBack = new JButton("Back");
//		btnBack.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});
		btnBack.setBounds(131, 228, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				int houseNumber = Integer.parseInt(textField.getText());
				String town = textField_1.getText();
				String description = textField_2.getText();
				
				Owner aux = new Owner ("Jon", "userJon", "passJon");
				BL.addRuralHouse(houseNumber, aux, description, town);
			}
		});
		btnFinish.setBounds(243, 228, 89, 23);
		
		contentPane.add(btnFinish);
	}
}
