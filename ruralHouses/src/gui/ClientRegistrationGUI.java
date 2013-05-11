package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

import domain.Client;

import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

public class ClientRegistrationGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtPasswordAgain;
	private JTextField txtTelephone;
	
	private ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientRegistrationGUI frame = new ClientRegistrationGUI();
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
	public ClientRegistrationGUI() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(69, 45, 46, 25);
		contentPane.add(lblName);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(69, 81, 80, 28);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(69, 120, 68, 25);
		contentPane.add(lblPassword);
		
		JLabel lblPasswordAgain = new JLabel("Password (Again):");
		lblPasswordAgain.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPasswordAgain.setBounds(69, 156, 110, 17);
		contentPane.add(lblPasswordAgain);
		
		JLabel lblRegistration = new JLabel("REGISTRATION");
		lblRegistration.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblRegistration.setBounds(121, 11, 203, 25);
		contentPane.add(lblRegistration);
		
		txtName = new JTextField();
		txtName.setBounds(191, 49, 148, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(191, 87, 148, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(191, 124, 148, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtPasswordAgain = new JTextField();
		txtPasswordAgain.setBounds(191, 156, 148, 20);
		contentPane.add(txtPasswordAgain);
		txtPasswordAgain.setColumns(10);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = txtName.getText();
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				String passwordAgain = txtPasswordAgain.getText();
				String telephone = txtTelephone.getText();
				Client client = new Client (name, username, password, telephone);
				if (password.compareTo(passwordAgain)==0){
					try {
						if(facade.clientRegistered(client)==false){
							facade.registerClient(client);
							JOptionPane.showMessageDialog(null, "Registration succesfully done!");
							setVisible(false);
						}else{
							JOptionPane.showMessageDialog(null, "Client was already registered.");
							setVisible(false);
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Password doesn't match.");
					JFrame a = new ClientRegistrationGUI();
					a.setVisible(true);
					setVisible(false);
				}
				
			}
		});
		btnAccept.setBounds(250, 228, 89, 23);
		contentPane.add(btnAccept);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBack.setBounds(69, 228, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblTelephone = new JLabel("Telephone:");
		lblTelephone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelephone.setBounds(69, 184, 74, 14);
		contentPane.add(lblTelephone);
		
		txtTelephone = new JTextField();
		txtTelephone.setBounds(191, 187, 148, 20);
		contentPane.add(txtTelephone);
		txtTelephone.setColumns(10);
	}
}
