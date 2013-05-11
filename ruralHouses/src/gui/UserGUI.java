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
	private JButton btnRegistrate;
	private JButton btnAuthenticate;
	
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
		this.setSize(350, 258);
		this.setContentPane(getJContentPane());
		this.setTitle("Use Cases");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getSearchAvailabilityButton());
			jContentPane.add(getBtnBookRH());
			jContentPane.add(getCancelBookingButton());
			jContentPane.add(getBtnRegistrate());
			jContentPane.add(getBtnAuthenticate());
		}
		return jContentPane;
	}
		
	private JButton getBtnBookRH() {
		if (btnBookRH == null) {
			btnBookRH = new JButton();
			btnBookRH.setBounds(56, 59, 245, 37);
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
	
	private JButton getSearchAvailabilityButton() {
		if (searchAvailabilityButton == null) {
			searchAvailabilityButton = new JButton();
			searchAvailabilityButton.setEnabled(true);
			searchAvailabilityButton.setBounds(56, 11, 245, 37);
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
	
	private JButton getCancelBookingButton(){
		if (cancelBookingButton == null) {
			cancelBookingButton = new JButton();
			cancelBookingButton.setEnabled(true);
			cancelBookingButton.setBounds(56, 107, 245, 37);
			cancelBookingButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
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
	private JButton getBtnRegistrate() {
		if (btnRegistrate == null) {
			btnRegistrate = new JButton("Registrate");
			btnRegistrate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new ClientRegistrationGUI();
					a.setVisible(true);}
			});
			btnRegistrate.setBounds(53, 155, 89, 23);
		}
		return btnRegistrate;
	}
	private JButton getBtnAuthenticate() {
		if (btnAuthenticate == null) {
			btnAuthenticate = new JButton("Authenticate");
			btnAuthenticate.setBounds(175, 155, 109, 23);
		}
		return btnAuthenticate;
	}
}
