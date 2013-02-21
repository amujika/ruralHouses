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

	private JLabel jLabel1 = new JLabel();
	private JComboBox jComboBox1;
	private JLabel jLabel2 = new JLabel();
	private JTextField jTextField2 = new JTextField();
	private JLabel jLabel3 = new JLabel();
	private JTextField jTextField3 = new JTextField();
	private JButton jButton1 = new JButton();
	private JButton jButton2 = new JButton();
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
		jComboBox1 = new JComboBox(rhs);

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(440, 548));
		this.setTitle("Search available rural houses");
		jLabel1.setText("Rural house:");
		jComboBox1.setBounds(new Rectangle(10, 10, 115, 25));
		jLabel1.setBounds(new Rectangle(40, 20, 105, 25));
		jComboBox1.setBounds(new Rectangle(115, 30, 115, 20));
		jLabel2.setText("First day:");
		jLabel2.setBounds(new Rectangle(40, 55, 75, 25));
		jTextField2.setBounds(new Rectangle(190, 210, 155, 25));
		jTextField2.setEditable(false);
		jLabel3.setText("Number of nights:");
		jLabel3.setBounds(new Rectangle(40, 250, 115, 25));
		jTextField3.setBounds(new Rectangle(190, 250, 155, 25));
		jTextField3.setText("0");
		jButton1.setText("Accept");
		jButton1.setBounds(new Rectangle(55, 455, 130, 30));
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton1_actionPerformed(e);
			}
		});
		jButton2.setText("Close");
		jButton2.setBounds(new Rectangle(230, 455, 130, 30));

		jTextField3.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }
			public void focusLost(FocusEvent e) {
				jTextField3_focusLost();
			}
		});
		jButton2.addActionListener(new ActionListener() {
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
		scrollPane.getViewport().add(jTextArea1, null);

		this.getContentPane().add(scrollPane, null);
		this.getContentPane().add(jCalendar1, null);
		this.getContentPane().add(jLabel4, null);
		this.getContentPane().add(jButton2, null);
		this.getContentPane().add(jButton1, null);
		this.getContentPane().add(jTextField3, null);
		this.getContentPane().add(jLabel3, null);
		this.getContentPane().add(jTextField2, null);
		this.getContentPane().add(jLabel2, null);
		this.getContentPane().add(jComboBox1, null);
		this.getContentPane().add(jLabel1, null);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
					DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jTextField2.setText(dateformat.format(myCalendar.getTime()));
				}
				else if (propertychangeevent.getPropertyName().equals("calendar")) {
					myCalendar = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jTextField2.setText(dateformat1.format(myCalendar.getTime()));
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
			new Integer (jTextField3.getText());
			jLabel4.setText("");
		}
		catch (NumberFormatException ex) {
			jLabel4.setText("Error: Please introduce a number");
		}
	}

	private void jButton1_actionPerformed(ActionEvent e) {		
		// House object
		RuralHouse rh=(RuralHouse)jComboBox1.getSelectedItem();
		// First day
		Date firstDay=new Date(jCalendar1.getCalendar().getTime().getTime());
		//Remove the hour:minute:second:ms from the date 
		firstDay=Date.valueOf(firstDay.toString());
		final long diams=1000*60*60*24;
		long nights= diams * Integer.parseInt(jTextField3.getText());
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