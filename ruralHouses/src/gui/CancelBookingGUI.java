package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterface;

import domain.Booking;
import domain.Client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Vector;
import java.awt.Color;

public class CancelBookingGUI extends JFrame {

	private JPanel contentPane;
	private DefaultComboBoxModel<Booking> bookings = new DefaultComboBoxModel<Booking>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelBookingGUI frame = new CancelBookingGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CancelBookingGUI() {
		setBounds(100, 100, 460, 360);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBooking = new JLabel("Booking:");
		lblBooking.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBooking.setBounds(62, 109, 61, 17);
		contentPane.add(lblBooking);
		
		contentPane.add(getBookings());
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.setBackground(new Color(152, 251, 152));
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Booking b= (Booking) bookings.getSelectedItem();
				ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
				try {
					Client client = new Client("Email","Password","Telephone");
					facade.cancelBooking(b,client);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnAccept.setBounds(250, 235, 89, 23);
		contentPane.add(btnAccept);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(152, 251, 152));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(62, 235, 89, 23);
		contentPane.add(btnCancel);
		
	}
	private JComboBox getBookings(){
		JComboBox JCBBookings = new JComboBox();
		JCBBookings.setBounds(133, 108, 206, 20);
		contentPane.add(JCBBookings);
		Client client = new Client("Email","Password","Telephone");
		Vector<Booking> bookingList = null;
		
		try {
			ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
			bookingList=facade.getBookings(client);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (bookingList.isEmpty())
			System.out.println("Client does not exist or has no bookings");
		for(Booking v : bookingList)
			bookings.addElement(v);
		
		JCBBookings.setModel(bookings);
		
		return JCBBookings;
			
	}
}
