package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import businessLogic.OwnerLoginBL;

import com.db4o.ObjectContainer;

import dataAccess.DB4oManager;
import java.rmi.RemoteException;
//Imported by me:
import java.util.Vector;
import domain.Owner;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class OwnerLoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	private OwnerLoginBL BL = new OwnerLoginBL();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OwnerLoginGUI frame = new OwnerLoginGUI();
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
	public OwnerLoginGUI() {
		setAlwaysOnTop(true);
		//setType(Type.UTILITY);
		setTitle("User Login");
		setResizable(false);
		setBounds(100, 100, 333, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("User authentication:");
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setToolTipText("Please, introduce your password");
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setToolTipText("Please, introduce your username");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		final JLabel lblNewLabel_3 = new JLabel("");
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = textField.getText();
				String password = new String(passwordField.getPassword());

				Owner ownerTriesToLogIn = new Owner(null, username, password);

				System.out.println("The owner we are looking for: NAME: " + ownerTriesToLogIn.getName() + " USER: " + ownerTriesToLogIn.getUsername() + " PASS: " + ownerTriesToLogIn.getPassword());
				
				if (BL.ownerloginBL(ownerTriesToLogIn) == null) {
					lblNewLabel_3.setForeground(new Color(253, 0, 0));
					lblNewLabel_3.setText("ACCESS DENIED!");
				} else {
					lblNewLabel_3.setForeground(new Color(0, 128, 0));
					lblNewLabel_3.setText("ACCESS GRANTED!");		
				} // END if
				
				textField.setText("");
				passwordField.setText("");				
				
			}
		}); // END addActionListener
		
		passwordField = new JPasswordField();	
		
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(0, 128, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(62, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(125))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(105))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2))
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
							.addGap(67))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addGap(53))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(btnNewButton)
					.addGap(28))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
