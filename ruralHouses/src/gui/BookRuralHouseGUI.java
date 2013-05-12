package gui;

import businessLogic.ApplicationFacadeInterface;

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
	private JComboBox<RuralHouse> jCBRuralHouse;
	private JLabel lblArrivalDay = new JLabel();
	private JLabel lblNumNights = new JLabel();
	private JLabel lblTelephone = new JLabel();
	private JTextField jTextField1 = new JTextField();
	private JTextField txtDate = new JTextField();
	private JTextField txtNumNights = new JTextField();
	private JTextField txtTelephone = new JTextField();
	private JButton btnAccept = new JButton();
	private JButton btnCancel = new JButton();
	private JFrame showInfo; 

	//private BookRuralHouseBL BL = new BookRuralHouseBL();
	private ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar myCalendar = null;
	private JLabel lblSpace = new JLabel();
	private final JLabel lblEmail = new JLabel("Email:");
	private final JTextField txtEmail = new JTextField();

	public BookRuralHouseGUI() {
		txtEmail.setBounds(294, 259, 140, 20);
		txtEmail.setColumns(10);
		getContentPane().setBackground(new Color(152, 251, 152));
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
		} catch(Exception e) {
			System.out.println("There has been an error in gui > BookRuralHouseGUI in line " + new Throwable().getStackTrace()[0].getLineNumber());
		}
	}

	private void jbInit() throws Exception {
		this.setSize(new Dimension(460, 400));
		this.setTitle("Book Rural House");
		lblRuralHouse.setBounds(15, 10, 115, 20);
		lblRuralHouse.setText("Rural house:");
		final ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
		Vector<RuralHouse> ruralHouses=facade.getAllRuralHouses();

		jCBRuralHouse = new JComboBox<RuralHouse>(ruralHouses);
		jCBRuralHouse.setBounds(120, 10, 175, 20);
		jCBRuralHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showInfo.setVisible(false);
				showInfo = new ShowHouseInfoGUI((RuralHouse) jCBRuralHouse.getSelectedItem());
				showInfo.setVisible(true);
			}
		});
		txtNumNights.setBounds(107, 227, 40, 20);

		txtNumNights.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			public void focusLost(FocusEvent e) {
				txtNumNights_focusLost();
			}
		});
		txtTelephone.setBounds(294, 227, 140, 20);
		txtTelephone.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }
			public void focusLost(FocusEvent e) {
				txtTelephone_focusLost();
			}
		});
		lblArrivalDay.setBounds(15, 41, 115, 20);
		lblArrivalDay.setText("Arrival day:");
		lblNumNights.setBounds(15, 227, 115, 20);
		lblNumNights.setText("Number of nights:");
		lblTelephone.setBounds(155, 227, 140, 20);
		lblTelephone.setText("Telephone contact number:");
		txtDate.setBounds(155, 196, 140, 20);
		txtDate.setEditable(false);
		txtNumNights.setText("0");
		txtTelephone.setText("0");
		btnAccept.setBackground(new Color(152, 251, 152));
		btnAccept.setBounds(260, 321, 130, 30);
		btnAccept.setText("Accept");
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
				// Contact email
				String email=txtEmail.getText();
				//IF LOGGED
				//IF NOT LOGGED BUT REGISTERED
				//IF NOT REGISTERED
				
				try {
					Offer auxOffer = facade.getOffer(ruralHouse, firstDay, lastDay);
					if(auxOffer!= null && auxOffer.getBooking()==null){					
						Booking auxBooking = auxOffer.createBook(telephone);						
						BookRuralHouseConfirmationWindow confirmWindow=new BookRuralHouseConfirmationWindow(auxBooking,auxOffer);
						confirmWindow.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(null, "Not a valid offer");
						//System.out.println("Not a valid offer");
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCancel.setBackground(new Color(152, 251, 152));
		btnCancel.setBounds(41, 321, 130, 30);
		btnCancel.setText("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel_actionPerformed(e);
			}
		});
		lblSpace.setBounds(90, 290, 300, 20);
		lblSpace.setForeground(Color.red);
		getContentPane().setLayout(null);
		jCalendar1.getDayChooser().setBackground(new Color(152, 251, 152));
		jCalendar1.getYearChooser().setBackground(new Color(152, 251, 152));
		jCalendar1.setBounds(155, 50, 235, 145);
		this.getContentPane().add(jCalendar1);
		this.getContentPane().add(lblSpace);
		this.getContentPane().add(btnCancel);
		this.getContentPane().add(btnAccept);
		this.getContentPane().add(txtTelephone);
		this.getContentPane().add(txtNumNights);
		this.getContentPane().add(txtDate);
		this.getContentPane().add(lblTelephone);
		this.getContentPane().add(lblNumNights);
		this.getContentPane().add(lblArrivalDay);
		this.getContentPane().add(jCBRuralHouse);
		this.getContentPane().add(lblRuralHouse);
		lblEmail.setBounds(256, 265, 28, 14);
		
		getContentPane().add(lblEmail);
		
		getContentPane().add(txtEmail);

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
		
		showInfo = new ShowHouseInfoGUI((RuralHouse) jCBRuralHouse.getSelectedItem());
		showInfo.setVisible(true);
	}

	private void btnCancel_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		showInfo.setVisible(false);
	}

	private void txtNumNights_focusLost() {
		try {
			new Integer (txtNumNights.getText());
			lblSpace.setText("");
		}
		catch (NumberFormatException ex) {
			lblSpace.setText("Error: Please introduce a number");
		}
	}

	private void txtTelephone_focusLost() {
		try {
			new Integer (txtTelephone.getText());
			lblSpace.setText("");
		}
		catch (NumberFormatException ex) {
			lblSpace.setText("Error: Please introduce a number");
		}
	}

}