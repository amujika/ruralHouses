package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

import businessLogic.ApplicationFacadeInterface;

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
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	//	private OwnerLoginBL BL = new OwnerLoginBL();
	private ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();

	/**
	 * Create the frame.
	 */
	public OwnerLoginGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		//setType(Type.UTILITY);
		setTitle("User Login");
		setResizable(false);
		setBounds(100, 100, 333, 262);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUserAuthen = new JLabel("User authentication:");		
		lblUserAuthen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setToolTipText("Please, introduce your password");
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setToolTipText("Please, introduce your username");
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		
		final JLabel lblAccess = new JLabel("");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(152, 251, 152));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = txtUsername.getText();
				String password = new String(txtPassword.getPassword());

				Owner ownerTriesToLogIn = new Owner(null, username, password);
				
				try {
					ownerTriesToLogIn = facade.ownerloginBL(ownerTriesToLogIn);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
				if (ownerTriesToLogIn == null) {
					lblAccess.setForeground(new Color(253, 0, 0));
					JOptionPane.showMessageDialog(null, "Access denied!");
				} else {
					lblAccess.setForeground(new Color(0, 128, 0));
					JOptionPane.showMessageDialog(null, "Access granted");
					StartWindow.OWNER = ownerTriesToLogIn;
					JFrame a = new OwnerGUI();
					a.setVisible(true);
					OwnerLoginGUI.this.setVisible(false);					
				} 
				
				txtUsername.setText("");
				txtPassword.setText("");				
				
			}
		});
		
		txtPassword = new JPasswordField();	
		
		lblAccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccess.setForeground(new Color(0, 128, 0));
		lblAccess.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(62, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnLogin)
							.addGap(125))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassword)
								.addComponent(lblUsername))
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
							.addGap(67))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblAccess, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addGap(53))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblUserAuthen, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addGap(89))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUserAuthen, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addComponent(lblAccess, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(btnLogin)
					.addGap(28))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
