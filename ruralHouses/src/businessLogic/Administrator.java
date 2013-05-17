package businessLogic;

import javax.swing.*;
import java.awt.*;

import businessLogic.FacadeImplementation;
import businessLogic.ApplicationFacadeInterface;

import java.rmi.*;

import configuration.Config;
import dataAccess.DB4oManager;
import domain.Owner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Administrator extends JFrame {

	public static boolean clientServer = !false;

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	public static ApplicationFacadeInterface facadeInterface;
	private JLabel lblVillatripasDeArriba;
	private JLabel lblTheBestRural;
	private JTextField textFieldName;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldBank;

	private ApplicationFacadeInterface facade;

	public Administrator() {
		super();
		initialize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//Falta Inicializar Facade

	private void initialize() {
		this.setSize(460, 360);
		this.setContentPane(getJContentPane());
		this.setTitle("Administrator");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(152, 251, 152));
			jContentPane.setLayout(null);
			jContentPane.add(getLblVillatripasDeArriba());
			jContentPane.add(getLblTheBestRural());

			JLabel lblRegisterOwner = new JLabel("Register Owner");
			lblRegisterOwner.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblRegisterOwner.setBounds(165, 92, 135, 24);
			jContentPane.add(lblRegisterOwner);

			JLabel lblName = new JLabel("Name");
			lblName.setBounds(48, 137, 46, 14);
			jContentPane.add(lblName);

			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(48, 199, 46, 14);
			jContentPane.add(lblPassword);

			JLabel lblUsername = new JLabel("Username");
			lblUsername.setBounds(48, 168, 55, 14);
			jContentPane.add(lblUsername);

			textFieldName = new JTextField();
			textFieldName.setBounds(133, 134, 216, 20);
			jContentPane.add(textFieldName);
			textFieldName.setColumns(10);

			textFieldUsername = new JTextField();
			textFieldUsername.setBounds(133, 165, 216, 20);
			jContentPane.add(textFieldUsername);
			textFieldUsername.setColumns(10);

			textFieldPassword = new JTextField();
			textFieldPassword.setBounds(133, 196, 216, 20);
			jContentPane.add(textFieldPassword);
			textFieldPassword.setColumns(10);

			JButton btnRegister = new JButton("Register");
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = textFieldName.getText();
					String username = textFieldUsername.getText();
					String password = textFieldPassword.getText();
					String bank = textFieldBank.getText();	

					Owner owner = new Owner(name, username, password, bank);

					try {
						facadeInterface.registerOwner(owner);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			});

			btnRegister.setBounds(195, 267, 89, 23);
			jContentPane.add(btnRegister);

			JLabel lblBankAccount = new JLabel("Bank Account");
			lblBankAccount.setBounds(48, 235, 74, 14);
			jContentPane.add(lblBankAccount);

			textFieldBank = new JTextField();
			textFieldBank.setBounds(132, 227, 217, 20);
			jContentPane.add(textFieldBank);
			textFieldBank.setColumns(10);
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
		JFrame a = new Administrator();
		a.setVisible(true);
	}


	private JLabel getLblVillatripasDeArriba() {
		if (lblVillatripasDeArriba == null) {
			lblVillatripasDeArriba = new JLabel("Villatripas de Arriba");
			lblVillatripasDeArriba.setForeground(new Color(47, 79, 79));
			lblVillatripasDeArriba.setFont(new Font("Calibri", Font.BOLD, 30));
			lblVillatripasDeArriba.setBounds(99, 11, 250, 50);
		}
		return lblVillatripasDeArriba;
	}
	private JLabel getLblTheBestRural() {
		if (lblTheBestRural == null) {
			lblTheBestRural = new JLabel("The best rural houses, the best prices!");
			lblTheBestRural.setFont(new Font("Calibri", Font.BOLD, 15));
			lblTheBestRural.setBounds(109, 57, 240, 24);
		}
		return lblTheBestRural;
	}
} //@jve:decl-index=0:visual-constraint="0,0"
