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
	
	public static boolean clientServer = false;

	private static final long serialVersionUID = 1L;
	
	public static Owner OWNER;

	private JPanel jContentPane = null;
	private JButton btnBookRH = null;
	private JButton btnIntroduceOffer = null;
	private JButton searchAvailabilityButton = null;
	private JButton btnExit = null;

	public static ApplicationFacadeInterface facadeInterface;
	private JButton btnLogin;
	
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
			jContentPane.setLayout(null);
			jContentPane.add(getSearchAvailabilityButton());
			jContentPane.add(getBtnBookRH());
			jContentPane.add(getBtnIntroduceOffer());
			jContentPane.add(getBtnExit());
			jContentPane.add(getBtnLogin());
			jContentPane.add(getBtnAddRuralHouse());	
			jContentPane.add(getBtnRemoveRuralHouse());
		}
		return jContentPane;
	}
	
	private JButton getBtnAddRuralHouse(){
		JButton btnAddRH = new JButton("Add a Rural House");
		btnAddRH.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAddRH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Owner owner = OWNER;
				
				if (owner == null){
					System.out.println("Not logged in. \nLog in please");
					return;
				}
				
				System.out.println("Logged in as: " + owner.getName());
				
				JFrame a = new AddRuralHouseGUI(StartWindow.OWNER);
				a.setVisible(true);
			}
		});
		btnAddRH.setBounds(56, 134, 245, 39);
		return btnAddRH;
	}

	private JButton getBtnRemoveRuralHouse(){
		JButton btnRemoveRH = new JButton("Remove Rural House");
		btnRemoveRH.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnRemoveRH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Owner owner = OWNER;

				if (owner == null){
					System.out.println("Not logged in. \nLog in please");
					return;
				}

				System.out.println("Logged in as: " + owner.getName());

				JFrame a = new removeRuralHouseGUI();
				a.setVisible(true);
			}
		});
		btnRemoveRH.setBounds(56, 184, 245, 37);
		return btnRemoveRH;
	}	
	
	private JButton getBtnBookRH() {
		if (btnBookRH == null) {
			btnBookRH = new JButton();
			btnBookRH.setBounds(56, 86, 245, 37);
			btnBookRH.setFont(new Font("Tahoma", Font.PLAIN, 24));
			btnBookRH.setText("Book rural house");
			btnBookRH.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnBookRH.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new BookRuralHouseGUI();
					a.setVisible(true);
				}
			});
		}
		return btnBookRH;
	}

	private JButton getBtnIntroduceOffer() {
		if (btnIntroduceOffer == null) {
			btnIntroduceOffer = new JButton();
			btnIntroduceOffer.setBounds(56, 232, 245, 37);
			btnIntroduceOffer.setFont(new Font("Tahoma", Font.PLAIN, 24));
			btnIntroduceOffer.setText("Introduce new offer");
			btnIntroduceOffer.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnIntroduceOffer.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					
					if (OWNER == null){
						System.out.println("Not logged in. \nLog in please");
						return;
					}
					
					System.out.println("Logged in as: " + OWNER.getName());
					Vector<RuralHouse> houseList=OWNER.getRuralHouses();

					if (houseList.isEmpty()!=true) {
						JFrame a = new IntroduceOffer2GUI(houseList);
						a.setVisible(true);
					}
					else if (houseList.isEmpty()==true) {
						System.out.print("Owner does not exist or has no registered houses ");
					} 	
				}
			});
		}
		return btnIntroduceOffer;
	}
	
	private JButton getSearchAvailabilityButton() {
		if (searchAvailabilityButton == null) {
			searchAvailabilityButton = new JButton();
			searchAvailabilityButton.setEnabled(false);
			searchAvailabilityButton.setBounds(56, 38, 245, 37);
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

	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton();
			btnExit.setBounds(134, 280, 71, 37);
			btnExit.setText("Exit");
			btnExit.setFont(new Font("Tahoma", Font.PLAIN, 24));
			btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnExit.addActionListener(new java.awt.event.ActionListener() {
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
		return btnExit;
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

	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("Login");
			btnLogin.setBounds(94, 328, 150, 23);
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {	
					JFrame a = new OwnerLoginGUI();
					a.setVisible(true);
				}
			});
			btnLogin.setMaximumSize(new Dimension(150, 23));
			btnLogin.setPreferredSize(new Dimension(150, 23));
			btnLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return btnLogin;
	}
} //@jve:decl-index=0:visual-constraint="0,0"
