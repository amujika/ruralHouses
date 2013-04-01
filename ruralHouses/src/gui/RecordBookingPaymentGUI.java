package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterface;

import domain.Booking;
import domain.Owner;
import domain.RuralHouse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class RecordBookingPaymentGUI extends JFrame {

	private JPanel contentPane;
	
	private DefaultComboBoxModel<RuralHouse> ruralHouses = new DefaultComboBoxModel<RuralHouse>();
	private DefaultComboBoxModel<Integer> bookingNumber= new DefaultComboBoxModel<Integer>();
	


	/**
	 * Create the frame.
	 */
	public RecordBookingPaymentGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBookingNum = new JLabel("Booking number:");
		lblBookingNum.setBounds(67, 111, 109, 23);
		contentPane.add(lblBookingNum);
		

		contentPane.add(getBookingNum());
		
		JLabel lblRuralHouse = new JLabel("Rural House:");
		lblRuralHouse.setBounds(67, 49, 71, 14);
		contentPane.add(lblRuralHouse);
		
		contentPane.add(getRuralHouse());
		
		JCheckBox chckbxPaid = new JCheckBox("Paid");
		chckbxPaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bookingNum = (int) (bookingNumber.getSelectedItem());
			}
		});
		chckbxPaid.setBounds(186, 166, 97, 23);
		contentPane.add(chckbxPaid);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnAccept.setBounds(284, 228, 89, 23);
		contentPane.add(btnAccept);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setVisible(false);
			}
		});
		btnCancel.setBounds(67, 228, 89, 23);
		contentPane.add(btnCancel);
	}
	
	private JComboBox getRuralHouse(){
		JComboBox JCBRuralHouse = new JComboBox();
		JCBRuralHouse.setBounds(186, 46, 187, 20);
		Owner owner = StartWindow.OWNER;
		Vector<RuralHouse> houseList = null;
		
		try {
			//Obtain the business logic from a StartWindow class (local or remote)
			ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
			houseList=facade.getRuralHouses(owner);

		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (houseList.isEmpty()) 
			System.out.println("Owner does not exist or has no registered houses ");
		
		for (RuralHouse v : houseList)
			ruralHouses.addElement(v);
		
		JCBRuralHouse.setModel(ruralHouses);
		
		JCBRuralHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RuralHouse rh = (RuralHouse) ruralHouses.getSelectedItem();
				Vector<Booking> bookingList = null;
				
				try {
					//Obtain the business logic from a StartWindow class (local or remote)
					ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
//					if (ruralHouses.getSelectedItem()!=null)
						System.out.println("Prueba");
						bookingList=facade.getBookings(rh);

				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				
				if (bookingList.isEmpty()) 
					System.out.println("Rural house or offers do not exist or have not registered bookings ");
				
				for (Booking v : bookingList)
					bookingNumber.addElement(v.getBookNumber());
				}
		});
		
		return JCBRuralHouse;
	}
	
	private JComboBox getBookingNum(){
		JComboBox JCBBookingNum = new JComboBox();
		JCBBookingNum.setBounds(186, 112, 187, 20);
		return JCBBookingNum;
	}
}
