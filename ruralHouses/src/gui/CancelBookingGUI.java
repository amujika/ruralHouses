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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBooking = new JLabel("Booking:");
		lblBooking.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBooking.setBounds(62, 46, 61, 17);
		contentPane.add(lblBooking);
		
		contentPane.add(getBookings());
		
		JButton btnCancelBooking = new JButton("Cancel Booking");
		btnCancelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Booking b= (Booking) bookings.getSelectedItem();
				ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
				try {
					facade.cancelBooking(b);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnCancelBooking.setBounds(227, 163, 112, 23);
		contentPane.add(btnCancelBooking);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnBack.setBounds(62, 163, 89, 23);
		contentPane.add(btnBack);
		
	}
	private JComboBox getBookings(){
		JComboBox JCBBookings = new JComboBox();
		JCBBookings.setBounds(133, 45, 206, 20);
		contentPane.add(JCBBookings);
		Client client = new Client("Bob","userBob","passBob");
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
