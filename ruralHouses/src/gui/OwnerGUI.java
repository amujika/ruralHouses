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

public class OwnerGUI extends JFrame {

	public static boolean clientServer = false;

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton btnIntroduceOffer = null;

	public static ApplicationFacadeInterface facadeInterface;
	private JButton btnRecordPayment;

	public OwnerGUI() {
		super();
		initialize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static ApplicationFacadeInterface getBusinessLogic() {
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
			jContentPane.add(getBtnIntroduceOffer());
			jContentPane.add(getBtnAddRuralHouse());
			jContentPane.add(getBtnRemoveRuralHouse());
			jContentPane.add(getBtnRecordPayment());
			jContentPane.add(getBtnChangeProperties());			
			{
				JLabel lblOwner = new JLabel("OWNER");
				lblOwner.setForeground(new Color(47, 79, 79));
				lblOwner.setFont(new Font("Calibri", Font.BOLD, 30));
				lblOwner.setBounds(160, 32, 99, 37);
				jContentPane.add(lblOwner);
			}
		}
		return jContentPane;
	}

	private JButton getBtnAddRuralHouse() {
		JButton btnAddRH = new JButton("Add a Rural House");
		btnAddRH.setBackground(new Color(152, 251, 152));
		btnAddRH.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnAddRH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Owner owner = StartWindow.OWNER;

				if (owner == null) {
					JOptionPane.showMessageDialog(null,
							"Not logged in. \nLog in please");
					// System.out.println("Not logged in. \nLog in please");
					return;
				}

				System.out.println("Logged in as: " + owner.getName());

				JFrame a = new AddRuralHouseGUI(StartWindow.OWNER);
				a.setVisible(true);
			}
		});
		btnAddRH.setBounds(97, 80, 245, 37);
		return btnAddRH;
	}

	private JButton getBtnChangeProperties() {
		JButton	btnChangeProperties = new JButton("Change Properties");
		btnChangeProperties.setBackground(new Color(152, 251, 152));
		btnChangeProperties.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnChangeProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Owner owner = StartWindow.OWNER;

				if (owner == null) {
					JOptionPane.showMessageDialog(null,
							"Not logged in. \nLog in please");
					// System.out.println("Not logged in. \nLog in please");
					return;
				}

				System.out.println("Logged in as: " + owner.getName());

				JFrame a = new ChangePropertiesRuralHouseGUI();
				a.setVisible(true);
			}
		});
		btnChangeProperties.setText("Change Properties");

		btnChangeProperties.setBounds(97, 128, 245, 37);
		return btnChangeProperties;
	}

	private JButton getBtnRemoveRuralHouse() {
		JButton btnRemoveRH = new JButton("Remove Rural House");
		btnRemoveRH.setBackground(new Color(152, 251, 152));
		btnRemoveRH.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnRemoveRH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Owner owner = StartWindow.OWNER;

				if (owner == null) {
					JOptionPane.showMessageDialog(null,
							"Not logged in. \nLog in please");
					// System.out.println("Not logged in. \nLog in please");
					return;
				}

				System.out.println("Logged in as: " + owner.getName());

				JFrame a = new RemoveRuralHouseGUI();
				a.setVisible(true);
			}
		});
		btnRemoveRH.setBounds(97, 273, 245, 38);
		return btnRemoveRH;
	}

	private JButton getBtnIntroduceOffer() {
		if (btnIntroduceOffer == null) {
			btnIntroduceOffer = new JButton();
			btnIntroduceOffer.setBackground(new Color(152, 251, 152));
			btnIntroduceOffer.setBounds(97, 176, 245, 37);
			btnIntroduceOffer.setFont(new Font("Calibri", Font.PLAIN, 24));
			btnIntroduceOffer.setText("Introduce new offer");
			btnIntroduceOffer.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnIntroduceOffer
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					if (StartWindow.OWNER == null) {
						JOptionPane.showMessageDialog(null,
								"Not logged in. \nLog in please");
						// System.out.println("Not logged in. \nLog in please");
						return;
					}

					System.out.println("Logged in as: " + StartWindow.OWNER.getName());
					Vector<RuralHouse> houseList = StartWindow.OWNER
							.getRuralHouses();

					if (houseList.isEmpty() != true) {
						JFrame a = new IntroduceOffer2GUI(houseList);
						a.setVisible(true);
					} else if (houseList.isEmpty() == true) {
						// System.out.print("Owner does not exist or has no registered houses");
					}
				}
			});
		}
		return btnIntroduceOffer;
	}

	private JButton getBtnRecordPayment() {
		btnRecordPayment = new JButton("Record Payment");
		btnRecordPayment.setBackground(new Color(152, 251, 152));
		btnRecordPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Owner owner = StartWindow.OWNER;

				if (owner == null) {
					JOptionPane.showMessageDialog(null,
							"Not logged in. \nLog in please");
					// System.out.println("Not logged in. \nLog in please");
					return;
				}

				System.out.println("Logged in as: " + owner.getName());

				JFrame a = new RecordBookingPaymentGUI();
				a.setVisible(true);
			}
		});
		btnRecordPayment.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnRecordPayment.setBounds(97, 224, 245, 38);
		return btnRecordPayment;
	}
} // @jve:decl-index=0:visual-constraint="0,0"
