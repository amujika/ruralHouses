package gui;

import businessLogic.ApplicationFacadeInterface;

import com.toedter.calendar.JCalendar;

import domain.Booking;
import domain.Offer;
import domain.RuralHouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.beans.*;

import java.sql.Date;
import java.text.DateFormat;
import java.util.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class SearchOffersGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblRuralHouse = new JLabel();
	private JComboBox jCBRuralHouse;
	private JButton btnSearch = new JButton();
	private JButton btnClose = new JButton();
	private Calendar myCalendar = null;
	private JList showOffers = new JList();
	
	private DefaultListModel<Offer> offerList;
	private JTextField txtTelephone;

	public SearchOffersGUI() {
		try {
			jbInit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();

		Vector<RuralHouse> rhs=facade.getAllRuralHouses();
		jCBRuralHouse = new JComboBox(rhs);

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(440, 375));
		this.setTitle("Search available rural houses");
		lblRuralHouse.setText("Rural house:");
		jCBRuralHouse.setBounds(new Rectangle(10, 10, 115, 25));
		lblRuralHouse.setBounds(new Rectangle(40, 20, 105, 25));
		jCBRuralHouse.setBounds(new Rectangle(115, 30, 115, 20));
		btnSearch.setText("Search");
		btnSearch.setBounds(new Rectangle(10, 281, 130, 30));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAccept_actionPerformed(e);
			}
		});
		btnClose.setText("Close");
		btnClose.setBounds(new Rectangle(284, 281, 130, 30));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClose_actionPerformed(e);
			}
		});
		this.getContentPane().add(btnClose, null);
		this.getContentPane().add(btnSearch, null);
		this.getContentPane().add(jCBRuralHouse, null);
		this.getContentPane().add(lblRuralHouse, null);
		showOffers.setBounds(55, 126, 305, 125);
		
		getContentPane().add(showOffers);
		
		JButton btnBook = new JButton();
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBook_actionPerformed();
			}
		});
		btnBook.setText("Book");
		btnBook.setBounds(new Rectangle(284, 281, 130, 30));
		btnBook.setBounds(144, 281, 130, 30);
		getContentPane().add(btnBook);
		
		txtTelephone = new JTextField();
		txtTelephone.setText("0");
		txtTelephone.setBounds(new Rectangle(155, 270, 140, 20));
		txtTelephone.setBounds(195, 87, 140, 20);
		getContentPane().add(txtTelephone);
		
		JLabel label = new JLabel();
		label.setText("Telephone contact number:");
		label.setBounds(new Rectangle(15, 270, 140, 20));
		label.setBounds(55, 87, 140, 20);
		getContentPane().add(label);

	}

	private void btnClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	private void btnAccept_actionPerformed(ActionEvent e) {	
		RuralHouse rh = (RuralHouse) jCBRuralHouse.getSelectedItem();
		offerList = new DefaultListModel<Offer>();
		for (Offer offer : rh.offers)
			if (offer.getBooking() == null)
				offerList.addElement(offer);

		showOffers.setModel(offerList);
	}

	private void btnBook_actionPerformed(){
		String telephone=txtTelephone.getText();
		Offer auxOffer = (Offer) showOffers.getSelectedValue();
		Booking auxBooking = auxOffer.createBook(telephone);						
		BookRuralHouseConfirmationWindow confirmWindow=new BookRuralHouseConfirmationWindow(auxBooking,auxOffer);
		confirmWindow.setVisible(true);
	}
}