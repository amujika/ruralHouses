package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterface;

import domain.Client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

public class ClientAuthenticationGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtPassword;
	
	private ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();

	/**
	 * Create the frame.
	 */
	public ClientAuthenticationGUI() {
		setBounds(100, 100, 460, 360);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(84, 114, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(84, 162, 92, 14);
		contentPane.add(lblPassword);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(155, 113, 150, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(155, 161, 150, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBackground(new Color(152, 251, 152));
		btnCancel.setBounds(84, 260, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = txtEmail.getText();
				String password = txtPassword.getText();
				Client client = new Client(email,password,null);
				try {
					if(facade.clientRegistered(client)!=null){
						JOptionPane.showMessageDialog(null, "Access granted.");
						StartWindow.CLIENT=facade.clientRegistered(client);
						setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null, "Access denied.");						
					}
				} catch (HeadlessException | RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAccept.setBackground(new Color(152, 251, 152));
		btnAccept.setBounds(216, 260, 89, 23);
		contentPane.add(btnAccept);
	}

}
