package gui;
//I'm Goiatz hello!
//ezequiel
import javax.swing.*;
import java.awt.*;

import businessLogic.FacadeImplementation;
import businessLogic.ApplicationFacadeInterface;

import java.rmi.*;
import configuration.Config;

public class StartWindow extends JFrame {
	
	public static boolean clientServer = false;

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton bookHouseButton = null;
	private JButton createOfferButton = null;
	private JButton searchAvailabilityButton = null;
	private JButton exitButton = null;

	public static ApplicationFacadeInterface facadeInterface;
	
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
		this.setSize(350, 400);
		this.setContentPane(getJContentPane());
		this.setTitle("Use Cases");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
//			jContentPane.setLayout(null);
			jContentPane.setLayout(new BoxLayout(jContentPane,
					BoxLayout.Y_AXIS));
			jContentPane.add(Box.createRigidArea(new Dimension(0,40)));
			jContentPane.add(getBookHouseButton());
			jContentPane.add(Box.createRigidArea(new Dimension(0,40)));
			jContentPane.add(getSearchAvailabilityButton());
			jContentPane.add(Box.createRigidArea(new Dimension(0,40)));
			jContentPane.add(getCreateOfferButton());
			jContentPane.add(Box.createRigidArea(new Dimension(0,40)));
			jContentPane.add(getExitButton());
			jContentPane.add(Box.createRigidArea(new Dimension(0,40)));
		}
		return jContentPane;
	}

	private JButton getBookHouseButton() {
		if (bookHouseButton == null) {
			bookHouseButton = new JButton();
			bookHouseButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
			bookHouseButton.setText("Book rural house");
			bookHouseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			bookHouseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new BookRuralHouseGUI();
					a.setVisible(true);
				}
			});
		}
		return bookHouseButton;
	}

	private JButton getCreateOfferButton() {
		if (createOfferButton == null) {
			createOfferButton = new JButton();
			createOfferButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
			createOfferButton.setText("Introduce new offer");
			createOfferButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			createOfferButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new IntroduceOfferGUI();
					a.setVisible(true);
				}
			});
		}
		return createOfferButton;
	}
	
	private JButton getSearchAvailabilityButton() {
		if (searchAvailabilityButton == null) {
			searchAvailabilityButton = new JButton();
			searchAvailabilityButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
			searchAvailabilityButton.setText("Search availability");
			searchAvailabilityButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			searchAvailabilityButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new SearchOffersGUI();
					a.setVisible(true);
				}
			});
		}
		return searchAvailabilityButton;
	}

	private JButton getExitButton() {
		if (exitButton == null) {
			exitButton = new JButton();
			exitButton.setText("Exit");
			exitButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
			exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			exitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//JFrame a = new QueryAvailabilityWindow();
					ApplicationFacadeInterface facade=StartWindow.facadeInterface;
					try {
						facade.close();
						setVisible(false);
					}
					catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}
			});
		}
		return exitButton;
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
		int x=0;
		JFrame a = new StartWindow();
		a.setVisible(true);
	}

} //@jve:decl-index=0:visual-constraint="0,0"
