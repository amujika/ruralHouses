package gui;
import javax.swing.*;

import java.awt.*;

import businessLogic.FacadeImplementation;
import businessLogic.ApplicationFacadeInterface;

import java.rmi.*;
import java.util.Vector;

import configuration.Config;
import javax.swing.border.BevelBorder;

import domain.Owner;
import domain.RuralHouse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartWindow extends JFrame {
	
	public static boolean clientServer = !false;

	private static final long serialVersionUID = 1L;
	
	public static Owner OWNER;

	private JPanel jContentPane = null;

	public static ApplicationFacadeInterface facadeInterface;
	private JButton btnUser;
	private JButton btnOwner;
	
	public StartWindow() {
		super();
		initialize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static ApplicationFacadeInterface getBusinessLogic(){
		return facadeInterface;
	}
	
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(350, 291);
		this.setContentPane(getJContentPane());
		this.setTitle("Use Cases");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnUser());
			jContentPane.add(getBtnOwner());
		}
		return jContentPane;
	}

	public static void main(String[] args) {		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			Config c=Config.getInstance();
			if (!clientServer)
				facadeInterface=new FacadeImplementation();
			else {
				final String serverRMI = c.getServerRMI();
				// Remote service name
				String serviceName = "/"+c.getServiceRMI();
				//System.setSecurityManager(new RMISecurityManager());
				// RMI server port number
				int portNumber = Integer.parseInt(c.getPortRMI());
				// RMI server host IP IP 
				facadeInterface = (ApplicationFacadeInterface) Naming.lookup("rmi://"
					+ serverRMI + ":" + portNumber + serviceName);
			} 

		}
		catch (Exception e) {
			//System.out.println(e.toString());
			e.printStackTrace();
		}
		JFrame a = new StartWindow();
		a.setVisible(true);
	}
	
	private JButton getBtnUser() {
		if (btnUser == null) {
			btnUser = new JButton("User");
			btnUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StartWindow.this.setVisible(false);
					JFrame a = new UserGUI();
					a.setVisible(true);
					
				}
			});
			btnUser.setBounds(51, 65, 230, 43);
		}
		return btnUser;
	}
	private JButton getBtnOwner() {
		if (btnOwner == null) {
			btnOwner = new JButton("Owner");
			btnOwner.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StartWindow.this.setVisible(false);
					JFrame a = new OwnerLoginGUI();
					a.setVisible(true);					
				}
			});
			btnOwner.setBounds(51, 150, 230, 38);
		}
		return btnOwner;
	}
} //@jve:decl-index=0:visual-constraint="0,0"
