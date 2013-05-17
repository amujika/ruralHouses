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
import javax.swing.JOptionPane;

import businessLogic.ApplicationFacadeInterface;

import domain.Booking;
import domain.Owner;
import domain.RuralHouse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Vector;
import java.awt.Color;

public class RecordBookingPaymentGUI extends JFrame {

	private JPanel contentPane;

	private DefaultComboBoxModel<RuralHouse> ruralHouses = new DefaultComboBoxModel<RuralHouse>();
	private DefaultComboBoxModel<Booking> bookingNumber= new DefaultComboBoxModel<Booking>();



	/**
	 * Create the frame.
	 */
	public RecordBookingPaymentGUI() {
		setBounds(100, 100, 460, 360);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
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


		final JCheckBox chckbxPaid = new JCheckBox("Paid");
		chckbxPaid.setBackground(new Color(152, 251, 152));
		chckbxPaid.setBounds(186, 166, 97, 23);
		contentPane.add(chckbxPaid);

		JButton btnAccept = new JButton("Accept");
		btnAccept.setBackground(new Color(152, 251, 152));
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Booking b= (Booking) bookingNumber.getSelectedItem();
				ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
				try {
					if(facade.paymentDone(b) == false){
						if(chckbxPaid.isSelected()){
							try {
								facade.storePayment(b);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} else
						JOptionPane.showMessageDialog(null, "Payment is already done.");
						//System.out.println("Payment is already done.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnAccept.setBounds(284, 260, 89, 23);
		contentPane.add(btnAccept);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(152, 251, 152));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(67, 260, 89, 23);
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
					bookingList=facade.getBookings(rh);

				}
				catch (Exception e1) {
					e1.printStackTrace();
				}

				if (bookingList.isEmpty())
					System.out.println("Rural house or offers do not exist or have not registered bookings ");

				for (Booking v : bookingList) {
					if (!v.isPaid())
						bookingNumber.addElement(v);
										
				}
			}
		});

		return JCBRuralHouse;
	}

	private JComboBox getBookingNum(){
		JComboBox JCBBookingNum = new JComboBox();
		JCBBookingNum.setBounds(186, 112, 187, 20);
		JCBBookingNum.setModel(bookingNumber);
		return JCBBookingNum;
	}
}
