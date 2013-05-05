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
		this.setSize(355, 305);
		this.setContentPane(getJContentPane());
		this.setTitle("Use Cases");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnIntroduceOffer());
			jContentPane.add(getBtnAddRuralHouse());
			jContentPane.add(getBtnRemoveRuralHouse());
			jContentPane.add(getBtnRecordPayment());
			jContentPane.add(getBtnChangeProperties());			
		}
		return jContentPane;
	}

	private JButton getBtnAddRuralHouse() {
		JButton btnAddRH = new JButton("Add a Rural House");
		btnAddRH.setFont(new Font("Tahoma", Font.PLAIN, 22));
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
		btnAddRH.setBounds(57, 23, 245, 39);
		return btnAddRH;
	}

	private JButton getBtnChangeProperties() {
		JButton	btnChangeProperties = new JButton("Change Properties");
		btnChangeProperties.setFont(new Font("Tahoma", Font.PLAIN, 22));
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

		btnChangeProperties.setBounds(57, 73, 245, 37);
		return btnChangeProperties;
	}

	private JButton getBtnRemoveRuralHouse() {
		JButton btnRemoveRH = new JButton("Remove Rural House");
		btnRemoveRH.setFont(new Font("Tahoma", Font.PLAIN, 22));
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
		btnRemoveRH.setBounds(57, 122, 245, 37);
		return btnRemoveRH;
	}

	private JButton getBtnIntroduceOffer() {
		if (btnIntroduceOffer == null) {
			btnIntroduceOffer = new JButton();
			btnIntroduceOffer.setBounds(57, 169, 245, 37);
			btnIntroduceOffer.setFont(new Font("Tahoma", Font.PLAIN, 22));
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
		btnRecordPayment.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnRecordPayment.setBounds(57, 217, 245, 38);
		return btnRecordPayment;
	}
} // @jve:decl-index=0:visual-constraint="0,0"
