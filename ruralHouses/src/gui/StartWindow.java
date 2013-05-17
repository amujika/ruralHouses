package gui;
import javax.swing.*;

import java.awt.*;

import businessLogic.FacadeImplementation;
import businessLogic.ApplicationFacadeInterface;

import java.rmi.*;

import configuration.Config;

import domain.Client;
import domain.Owner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartWindow extends JFrame {

	public static boolean clientServer = !false;

	private static final long serialVersionUID = 1L;

	public static Owner OWNER;
	public static Client CLIENT;

	private JPanel jContentPane = null;

	public static ApplicationFacadeInterface facadeInterface;
	private JButton btnUser;
	private JButton btnOwner;
	private JLabel lblVillatripasDeArriba;
	private JLabel lblTheBestRural;

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
		this.setSize(460, 360);
		this.setContentPane(getJContentPane());
		this.setTitle("Use Cases");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(152, 251, 152));
			jContentPane.setLayout(null);
			jContentPane.add(getBtnUser());
			jContentPane.add(getBtnOwner());
			jContentPane.add(getLblVillatripasDeArriba());
			jContentPane.add(getLblTheBestRural());
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
			btnUser.setBackground(new Color(152, 251, 152));
			btnUser.setFont(new Font("Calibri", Font.BOLD, 20));
			btnUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StartWindow.this.setVisible(false);
					JFrame a = new UserGUI();
					a.setVisible(true);

				}
			});
			btnUser.setBounds(109, 131, 230, 43);
		}
		return btnUser;
	}
	private JButton getBtnOwner() {
		if (btnOwner == null) {
			btnOwner = new JButton("Owner");
			btnOwner.setBackground(new Color(152, 251, 152));
			btnOwner.setFont(new Font("Calibri", Font.BOLD, 20));
			btnOwner.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StartWindow.this.setVisible(false);
					JFrame a = new OwnerLoginGUI();
					a.setVisible(true);					
				}
			});
			btnOwner.setBounds(109, 185, 230, 43);
		}
		return btnOwner;
	}


	private JLabel getLblVillatripasDeArriba() {
		if (lblVillatripasDeArriba == null) {
			lblVillatripasDeArriba = new JLabel("Villatripas de Arriba");
			lblVillatripasDeArriba.setForeground(new Color(47, 79, 79));
			lblVillatripasDeArriba.setFont(new Font("Calibri", Font.BOLD, 30));
			lblVillatripasDeArriba.setBounds(99, 28, 250, 50);
		}
		return lblVillatripasDeArriba;
	}
	private JLabel getLblTheBestRural() {
		if (lblTheBestRural == null) {
			lblTheBestRural = new JLabel("The best rural houses, the best prices!");
			lblTheBestRural.setFont(new Font("Calibri", Font.BOLD, 15));
			lblTheBestRural.setBounds(109, 81, 240, 24);
		}
		return lblTheBestRural;
	}
} //@jve:decl-index=0:visual-constraint="0,0"
