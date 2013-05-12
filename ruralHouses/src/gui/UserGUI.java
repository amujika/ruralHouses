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

public class UserGUI extends JFrame {
	
	public static boolean clientServer = false;

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton btnBookRH = null;
	private JButton searchAvailabilityButton = null;
	private JButton cancelBookingButton = null;

	public static ApplicationFacadeInterface facadeInterface;
	private JButton btnAuthenticate;
	private JLabel lblUser;
	
	public UserGUI() {
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
			jContentPane.add(getSearchAvailabilityButton());
			jContentPane.add(getBtnBookRH());
			jContentPane.add(getCancelBookingButton());
			jContentPane.add(getBtnAuthenticate());
			jContentPane.add(getLblUser());
		}
		return jContentPane;
	}
		
	private JButton getBtnBookRH() {
		if (btnBookRH == null) {
			btnBookRH = new JButton();
			btnBookRH.setBackground(new Color(152, 251, 152));
			btnBookRH.setBounds(97, 214, 245, 37);
			btnBookRH.setFont(new Font("Calibri", Font.PLAIN, 24));
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
	
	private JButton getSearchAvailabilityButton() {
		if (searchAvailabilityButton == null) {
			searchAvailabilityButton = new JButton();
			searchAvailabilityButton.setBackground(new Color(152, 251, 152));
			searchAvailabilityButton.setEnabled(true);
			searchAvailabilityButton.setBounds(97, 166, 245, 37);
			searchAvailabilityButton.setFont(new Font("Calibri", Font.PLAIN, 24));
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
	
	private JButton getCancelBookingButton(){
		if (cancelBookingButton == null) {
			cancelBookingButton = new JButton();
			cancelBookingButton.setBackground(new Color(152, 251, 152));
			cancelBookingButton.setEnabled(true);
			cancelBookingButton.setBounds(97, 262, 245, 33);
			cancelBookingButton.setFont(new Font("Calibri", Font.PLAIN, 24));
			cancelBookingButton.setText("Cancel booking");
			cancelBookingButton.setAlignmentX(Component.CENTER_ALIGNMENT);		
			cancelBookingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new CancelBookingGUI();
				a.setVisible(true);			
			}
			});
		}
		return cancelBookingButton;
	}
	private JButton getBtnAuthenticate() {
		if (btnAuthenticate == null) {
			btnAuthenticate = new JButton("Authenticate");
			btnAuthenticate.setBackground(new Color(152, 251, 152));
			btnAuthenticate.setFont(new Font("Calibri", Font.PLAIN, 30));
			btnAuthenticate.setBounds(97, 93, 245, 54);
		}
		return btnAuthenticate;
	}
	private JLabel getLblUser() {
		if (lblUser == null) {
			lblUser = new JLabel("USER");
			lblUser.setFont(new Font("Calibri", Font.BOLD, 30));
			lblUser.setForeground(new Color(47, 79, 79));
			lblUser.setBounds(179, 28, 80, 54);
		}
		return lblUser;
	}
}
