package gui;

import businessLogic.ApplicationFacadeInterface;
import businessLogic.BookRuralHouseBL;

import com.toedter.calendar.*;

import domain.Booking;
import domain.Offer;
import domain.RuralHouse;

import exceptions.OfferCanNotBeBooked;

import java.beans.*;

import java.sql.Date;
import java.text.*;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BookRuralHouseGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel lblRuralHouse = new JLabel();
	private JComboBox jCBRuralHouse;
	private JLabel lblArrivalDay = new JLabel();
	private JLabel lblNumNights = new JLabel();
	private JLabel lblTelephone = new JLabel();
	private JTextField jTextField1 = new JTextField();
	private JTextField txtDate = new JTextField();
	private JTextField txtNumNights = new JTextField();
	private JTextField txtTelephone = new JTextField();
	private JButton btnAccept = new JButton();
	private JButton btnCancel = new JButton();

	private BookRuralHouseBL BL = new BookRuralHouseBL();

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar myCalendar = null;
	private JLabel jLabel5 = new JLabel();

	public BookRuralHouseGUI() {
		try {
			jbInit();
		}
		catch(Exception e) {
			System.out.println("There has been an error in gui > BookRuralHouseGUI in line " + new Throwable().getStackTrace()[0].getLineNumber());
		}
	}

	public BookRuralHouseGUI(int houseNumber, Date firstDay, Date lastDay) {

		try {

			jbInit();

			/*jTextField1.setText(Integer.toString(houseNumber));
			long nights=(lastDay.getTime()-firstDay.getTime())/(1000*60*60*24);
			jTextField3.setText(Long.toString(nights));
			DateFormat dateformat1 = DateFormat.getDateInstance(1);

			Date first= new Date((long)(firstDay.getTime()));
			jTextField2.setText(dateformat1.format(first));
			GregorianCalendar cal=new GregorianCalendar();
			cal.setTime(first);
			int year=cal.get(Calendar.YEAR);
			int month=cal.get(Calendar.MONTH);
			int day=cal.get(Calendar.DAY_OF_MONTH);

			JYearChooser yc = jCalendar1.getYearChooser();
			JMonthChooser mc = jCalendar1.getMonthChooser();
			JDayChooser dc = jCalendar1.getDayChooser();

			yc.setYear(year);
			mc.setMonth(month);
			dc.setDay(day);*/

		} catch(Exception e) {
			System.out.println("There has been an error in gui > BookRuralHouseGUI in line " + new Throwable().getStackTrace()[0].getLineNumber());
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(430, 440));
		this.setTitle("Book Rural House");
		lblRuralHouse.setText("Rural house:");
		ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
		Vector<RuralHouse> ruralHouses=facade.getAllRuralHouses();

		jCBRuralHouse = new JComboBox(ruralHouses);

		lblRuralHouse.setBounds(new Rectangle(15, 10, 115, 20));
		jCBRuralHouse.setBounds(new Rectangle(120, 10, 175, 20));

		txtNumNights.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			public void focusLost(FocusEvent e) {
				jTextField3_focusLost();
			}
		});
		txtTelephone.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }
			public void focusLost(FocusEvent e) {
				jTextField4_focusLost();
			}
		});
		lblArrivalDay.setText("Arrival day:");
		lblArrivalDay.setBounds(new Rectangle(15, 40, 115, 20));
		lblNumNights.setText("Number of nights:");
		lblNumNights.setBounds(new Rectangle(15, 240, 115, 20));
		lblTelephone.setText("Telephone contact number:");
		lblTelephone.setBounds(new Rectangle(15, 270, 140, 20));
		txtDate.setBounds(new Rectangle(155, 205, 140, 20));
		txtDate.setEditable(false);
		txtNumNights.setBounds(new Rectangle(155, 240, 140, 20));
		txtNumNights.setText("0");
		txtTelephone.setBounds(new Rectangle(155, 270, 140, 20));
		txtTelephone.setText("0");
		btnAccept.setText("Accept");
		btnAccept.setBounds(new Rectangle(50, 345, 130, 30));
		btnAccept.setSize(new Dimension(130, 30));
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// House code
				RuralHouse ruralHouse=(RuralHouse)jCBRuralHouse.getSelectedItem();
				// Arrival date
				Date firstDay= new Date(jCalendar1.getCalendar().getTime().getTime());
				firstDay=Date.valueOf(firstDay.toString());
				// Last day
				// Number of days expressed in milliseconds
				long nights=1000*60*60*24* Integer.parseInt(txtNumNights.getText());
				Date lastDay= new Date((long)(firstDay.getTime()+nights));
				// Contact telephone
				String telephone=txtTelephone.getText();
				try {
					Offer auxOffer = BL.getOffer(ruralHouse, firstDay, lastDay);
					if(auxOffer!= null){					
						Booking auxBooking = auxOffer.createBook(telephone);						
						BookRuralHouseConfirmationWindow confirmWindow=new BookRuralHouseConfirmationWindow(auxBooking,auxOffer);
						confirmWindow.setVisible(true);
					}
					else
						System.out.println("Not a valid offer");
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCancel.setText("Cancel");
		btnCancel.setBounds(new Rectangle(220, 345, 130, 30));
		btnCancel.setSize(new Dimension(130, 30));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton3_actionPerformed(e);
			}
		});
		jLabel5.setBounds(new Rectangle(50, 310, 300, 20));
		jLabel5.setForeground(Color.red);
		jCalendar1.setBounds(new Rectangle(155, 50, 235, 145));
		this.getContentPane().add(jCalendar1, null);
		this.getContentPane().add(jLabel5, null);
		this.getContentPane().add(btnCancel, null);
		this.getContentPane().add(btnAccept, null);
		this.getContentPane().add(txtTelephone, null);
		this.getContentPane().add(txtNumNights, null);
		this.getContentPane().add(txtDate, null);
		this.getContentPane().add(lblTelephone, null);
		this.getContentPane().add(lblNumNights, null);
		this.getContentPane().add(lblArrivalDay, null);
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

	private void jButton3_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	private void jTextField3_focusLost() {
		try {
			new Integer (txtNumNights.getText());
			jLabel5.setText("");
		}
		catch (NumberFormatException ex) {
			jLabel5.setText("Error: Please introduce a number");
		}
	}

	private void jTextField4_focusLost() {
		try {
			new Integer (txtTelephone.getText());
			jLabel5.setText("");
		}
		catch (NumberFormatException ex) {
			jLabel5.setText("Error: Please introduce a number");
		}
	}

}