package gui;

import businessLogic.ApplicationFacadeInterface;

import com.toedter.calendar.JCalendar;

import domain.Offer;
import domain.RuralHouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.beans.*;

import java.sql.Date;
import java.text.DateFormat;
import java.util.*;

public class SearchOffersGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblRuralHouse = new JLabel();
	private JComboBox jCBRuralHouse;
	private JLabel lblFirstDay = new JLabel();
	private JTextField txtDate = new JTextField();
	private JLabel lblNumNights = new JLabel();
	private JTextField txtNumNights = new JTextField();
	private JButton btnAccept = new JButton();
	private JButton btnClose = new JButton();
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar myCalendar = null;
	private JLabel jLabel4 = new JLabel();
	private JTextArea jTextArea1 = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane();

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
		this.setSize(new Dimension(440, 548));
		this.setTitle("Search available rural houses");
		lblRuralHouse.setText("Rural house:");
		jCBRuralHouse.setBounds(new Rectangle(10, 10, 115, 25));
		lblRuralHouse.setBounds(new Rectangle(40, 20, 105, 25));
		jCBRuralHouse.setBounds(new Rectangle(115, 30, 115, 20));
		lblFirstDay.setText("First day:");
		lblFirstDay.setBounds(new Rectangle(40, 55, 75, 25));
		txtDate.setBounds(new Rectangle(190, 210, 155, 25));
		txtDate.setEditable(false);
		lblNumNights.setText("Number of nights:");
		lblNumNights.setBounds(new Rectangle(40, 250, 115, 25));
		txtNumNights.setBounds(new Rectangle(190, 250, 155, 25));
		txtNumNights.setText("0");
		btnAccept.setText("Accept");
		btnAccept.setBounds(new Rectangle(55, 455, 130, 30));
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton1_actionPerformed(e);
			}
		});
		btnClose.setText("Close");
		btnClose.setBounds(new Rectangle(230, 455, 130, 30));

		txtNumNights.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }
			public void focusLost(FocusEvent e) {
				jTextField3_focusLost();
			}
		});
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		jLabel4.setBounds(new Rectangle(55, 300, 305, 30));
		jLabel4.setForeground(Color.red);
		jTextArea1.setEditable(false);
		jCalendar1.setBounds(new Rectangle(190, 60, 225, 150));
		scrollPane.setBounds(new Rectangle(45, 305, 320, 135));
		jTextArea1.setText("");
		scrollPane.setViewportView(jTextArea1);

		this.getContentPane().add(scrollPane, null);
		this.getContentPane().add(jCalendar1, null);
		this.getContentPane().add(jLabel4, null);
		this.getContentPane().add(btnClose, null);
		this.getContentPane().add(btnAccept, null);
		this.getContentPane().add(txtNumNights, null);
		this.getContentPane().add(lblNumNights, null);
		this.getContentPane().add(txtDate, null);
		this.getContentPane().add(lblFirstDay, null);
		this.getContentPane().add(jCBRuralHouse, null);
		this.getContentPane().add(lblRuralHouse, null);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
					DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					txtDate.setText(dateformat.format(myCalendar.getTime()));
				}
				else if (propertychangeevent.getPropertyName().equals("calendar")) {
					myCalendar = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					txtDate.setText(dateformat1.format(myCalendar.getTime()));
					jCalendar1.setCalendar(myCalendar);
				}
			} 
		});

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	private void jTextField3_focusLost() {
		try {
			new Integer (txtNumNights.getText());
			jLabel4.setText("");
		}
		catch (NumberFormatException ex) {
			jLabel4.setText("Error: Please introduce a number");
		}
	}

	private void jButton1_actionPerformed(ActionEvent e) {		
		// House object
		RuralHouse rh=(RuralHouse)jCBRuralHouse.getSelectedItem();
		// First day
		Date firstDay=new Date(jCalendar1.getCalendar().getTime().getTime());
		//Remove the hour:minute:second:ms from the date 
		firstDay=Date.valueOf(firstDay.toString());
		final long diams=1000*60*60*24;
		long nights= diams * Integer.parseInt(txtNumNights.getText());
		// Last day
		Date lastDay= new Date((long)(firstDay.getTime()+nights));
		jTextArea1.setText("");
		try {
			ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
			Vector<Offer> v=facade.getOffers(rh, firstDay, lastDay);

			Enumeration<Offer> en=v.elements();
			Offer of;
			while (en.hasMoreElements()) {
				of=en.nextElement();
				firstDay= new Date(of.getFirstDay().getTime());
				firstDay=Date.valueOf(firstDay.toString());
				lastDay= new Date(of.getLastDay().getTime());
				lastDay=Date.valueOf(lastDay.toString());
				jTextArea1.append("Offer "+Integer.toString(of.getOfferNumber())+":from:"+firstDay+" to:"+lastDay+"\n");		    	
			}

			//} catch (RemoteException e1) {
			//			e1.printStackTrace();

		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}