package gui;

import java.beans.*;

import java.sql.Date;
import java.text.DateFormat;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import businessLogic.ApplicationFacadeInterface;

import com.toedter.calendar.JCalendar;

import domain.RuralHouse;

public class IntroduceOffer2GUI extends JFrame  {
	
	private static final long serialVersionUID = 1L;

	private JComboBox jCBListHouses;
	private JLabel lblListHouses = new JLabel();
	private JLabel lblFirstDay = new JLabel();
	private JTextField txtDateFirst = new JTextField();
	private JLabel lblLastDay = new JLabel();
	private JTextField txtDateLast = new JTextField();
	private JLabel lblPrice = new JLabel();
	private JTextField txtPrice = new JTextField();
	private JButton btnAccept = new JButton();
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private JCalendar jCalendar2 = new JCalendar();
	private Calendar calendarInicio = null;
	private Calendar calendarFin = null;
	private JButton btnCancel = new JButton();
	private JLabel lblSpace = new JLabel();
	
//	private IntroduceOfferBL BL = new IntroduceOfferBL();
	private ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();

	public IntroduceOffer2GUI(Vector<RuralHouse> v)	{
		try	{
			jbInit(v);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<RuralHouse> v) throws Exception {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(513, 433));
		this.setTitle("Set availability");

		jCBListHouses = new JComboBox(v);
		jCBListHouses.setBounds(new Rectangle(115, 30, 115, 20));
		lblListHouses.setText("List of houses:");
		lblListHouses.setBounds(new Rectangle(25, 30, 95, 20));
		lblFirstDay.setText("First day :");
		lblFirstDay.setBounds(new Rectangle(25, 75, 85, 25));
		txtDateFirst.setBounds(new Rectangle(25, 265, 220, 25));
		txtDateFirst.setEditable(false);
		lblLastDay.setText("Last day :");
		lblLastDay.setBounds(new Rectangle(260, 75, 75, 25));
		txtDateLast.setBounds(new Rectangle(260, 265, 220, 25));
		txtDateLast.setEditable(false);
		lblPrice.setText("Price:");
		lblPrice.setBounds(new Rectangle(260, 30, 75, 20));
		txtPrice.setBounds(new Rectangle(350, 30, 115, 20));
		txtPrice.setText("0");
		btnAccept.setText("Accept");
		btnAccept.setBounds(new Rectangle(100, 360, 130, 30));
		txtPrice.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			public void focusLost(FocusEvent e) {
				txtPrice_focusLost();
			}
		});
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAccept_actionPerformed(e);
			}
		});
		btnCancel.setText("Cancel");
		btnCancel.setBounds(new Rectangle(270, 360, 130, 30));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel_actionPerformed(e);
			}
		});
		lblSpace.setBounds(new Rectangle(100, 320, 300, 20));
		lblSpace.setForeground(Color.red);
		lblSpace.setSize(new Dimension(305, 20));
		jCalendar1.setBounds(new Rectangle(25, 100, 220, 165));
		jCalendar2.setBounds(new Rectangle(260, 100, 220, 165));

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
					DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					txtDateFirst.setText(dateformat.format(calendarInicio.getTime()));
				}
				else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarInicio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					txtDateFirst.setText(dateformat1.format(calendarInicio.getTime()));
					jCalendar1.setCalendar(calendarInicio);
				}
			} 
		});

		this.jCalendar2.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar2.setLocale((Locale) propertychangeevent.getNewValue());
					DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar2.getLocale());
					txtDateLast.setText(dateformat.format(calendarFin.getTime()));
				}
				else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarFin = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar2.getLocale());
					txtDateLast.setText(dateformat1.format(calendarFin.getTime()));
					jCalendar2.setCalendar(calendarFin);
				}
			} 
		});

		this.getContentPane().add(jCalendar2, null);
		this.getContentPane().add(jCalendar1, null);
		this.getContentPane().add(lblSpace, null);
		this.getContentPane().add(btnCancel, null);
		this.getContentPane().add(btnAccept, null);
		this.getContentPane().add(txtPrice, null);
		this.getContentPane().add(lblPrice, null);
		this.getContentPane().add(txtDateLast, null);
		this.getContentPane().add(lblLastDay, null);
		this.getContentPane().add(txtDateFirst, null);
		this.getContentPane().add(lblFirstDay, null);
		this.getContentPane().add(lblListHouses, null);
		this.getContentPane().add(jCBListHouses, null);
	}

	private void btnAccept_actionPerformed(ActionEvent e) {
		RuralHouse ruralHouse=((RuralHouse)jCBListHouses.getSelectedItem());
		Date firstDay=new Date(jCalendar1.getCalendar().getTime().getTime());
		//Remove the hour:minute:second:ms from the date 
		firstDay=Date.valueOf(firstDay.toString());
		Date lastDay=new Date(jCalendar2.getCalendar().getTime().getTime());
		//Remove the hour:minute:second:ms from the date 
		lastDay=Date.valueOf(lastDay.toString());
		//It could be to trigger an exception if the introduced string is not a number
		float price= Float.parseFloat(txtPrice.getText());
		try {
			//Obtain the business logic from a StartWindow class (local or remote)
			//ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();

			facade.createOffer(ruralHouse, firstDay, lastDay, price); 

			this.setVisible(false);
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void btnCancel_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	private void txtPrice_focusLost() {
		try {
			new Integer (txtPrice.getText());
			lblSpace.setText("");
		}
		catch (NumberFormatException ex) {
			lblSpace.setText("Error: Please introduce a number");
		}
	}
}