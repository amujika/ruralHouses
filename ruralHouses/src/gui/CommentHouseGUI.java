package gui;

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
import domain.RuralHouse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JTextField;

public class CommentHouseGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultComboBoxModel<RuralHouse> bookings = new DefaultComboBoxModel<RuralHouse>();
	private ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
	private JTextField comment;


	/**
	 * Create the frame.
	 */
	public CommentHouseGUI() {
		setBounds(100, 100, 460, 565);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBooking = new JLabel("House:");
		lblBooking.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBooking.setBounds(62, 109, 61, 17);
		contentPane.add(lblBooking);
		
		contentPane.add(getBookings());
		
		JButton btnAccept = new JButton("Send");
		btnAccept.setBackground(new Color(152, 251, 152));
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RuralHouse rh= (RuralHouse) bookings.getSelectedItem();
				try {
					facade.addComment(rh,"User " + StartWindow.CLIENT.getEmail() + " said: " + comment.getText());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnAccept.setBounds(250, 476, 89, 23);
		contentPane.add(btnAccept);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(152, 251, 152));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(62, 476, 89, 23);
		contentPane.add(btnCancel);
		
		comment = new JTextField();
		comment.setBounds(62, 186, 277, 244);
		contentPane.add(comment);
		comment.setColumns(10);
		
	}
	private JComboBox<RuralHouse> getBookings(){
		JComboBox<RuralHouse> JCBBookings = new JComboBox<RuralHouse>();
		JCBBookings.setBounds(133, 108, 206, 20);
		contentPane.add(JCBBookings);
		Client client = StartWindow.CLIENT;
		Vector<Booking> bookingList = null;
		
		try {
			bookingList=facade.getBookings(client);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (bookingList.isEmpty())
			System.out.println("Client does not exist or has no bookings");
		for(Booking v : bookingList)
			bookings.addElement(v.getOffer().getRuralHouse());
		
		JCBBookings.setModel(bookings);
		
		return JCBBookings;
			
	}
}
