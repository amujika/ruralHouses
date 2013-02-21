package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import domain.Booking;

public class BookRuralHouseConfirmationWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel1 = new JLabel();
	private JTextField jTextField1 = new JTextField();
	private JLabel jLabel2 = new JLabel();
	private JTextField jTextField2 = new JTextField();
	private JLabel jLabel3 = new JLabel();
	private JButton jButton1 = new JButton();
	private JLabel jLabel4 = new JLabel();
	private JLabel jLabel5 = new JLabel();
	private JTextField jTextField3 = new JTextField();
	private JTextField jTextField4 = new JTextField();

	public BookRuralHouseConfirmationWindow(Booking booking) {
		try {
			jbInit(booking);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Booking booking) throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(416, 316));
		this.setTitle("Confirm Booking");
		this.setResizable(false);
		jLabel1.setText("Owner Bank account number:");
		jLabel1.setBounds(new Rectangle(20, 20, 200, 25));
		jTextField1.setBounds(new Rectangle(225, 20, 165, 25));
		jTextField1.setEditable(false);
		jTextField1.setText(booking.getOffer().getRuralHouse().getOwner().getBankAccount());

		jLabel2.setText("Booking number:");
		jLabel2.setBounds(new Rectangle(20, 60, 130, 25));
		jTextField2.setBounds(new Rectangle(225, 60, 165, 25));
		jTextField2.setEditable(false);

		jTextField2.setText(Integer.toString(booking.getBookNumber()));

		jLabel3.setText("You ought to transfer 20% of the total price of the booking in the next three days.");
		jLabel3.setBounds(new Rectangle(20, 105, 370, 25));
		jButton1.setText("Accept");
		jButton1.setBounds(new Rectangle(135, 235, 130, 30));
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton1_actionPerformed(e);
			}
		});
		jLabel4.setText("Total:");
		jLabel4.setBounds(new Rectangle(70, 140, 85, 25));
		jLabel5.setText("Deposit amount:");
		jLabel5.setBounds(new Rectangle(70, 175, 100, 25));
		jTextField3.setBounds(new Rectangle(180, 140, 115, 25));
		jTextField3.setEditable(false);

		jTextField3.setText(Float.toString(booking.getPrice()) + " €");
		jTextField4.setBounds(new Rectangle(180, 175, 115, 25));
		jTextField4.setEditable(false);
		jTextField4.setText(Float.toString(booking.getPrice()*(float)0.2) + " €");
		this.getContentPane().add(jTextField4, null);
		this.getContentPane().add(jTextField3, null);
		this.getContentPane().add(jLabel5, null);
		this.getContentPane().add(jLabel4, null);
		this.getContentPane().add(jButton1, null);
		this.getContentPane().add(jLabel3, null);
		this.getContentPane().add(jTextField2, null);
		this.getContentPane().add(jLabel2, null);
		this.getContentPane().add(jTextField1, null);
		this.getContentPane().add(jLabel1, null);
	}

	private void jButton1_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	
}