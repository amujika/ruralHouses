package gui;

import javax.swing.*;

import businessLogic.ApplicationFacadeInterface;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import domain.Booking;
import domain.Client;
import domain.Offer;

public class BookRuralHouseConfirmationWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblBankAccNum = new JLabel();
	private JTextField txtBankAccNum = new JTextField();
	private JLabel lblBookingNum = new JLabel();
	private JTextField txtBookingNum = new JTextField();
	private JLabel lblDepositAdvertise = new JLabel();
	private JButton btnAccept = new JButton();
	private JLabel lblTotalPayment = new JLabel();
	private JLabel lblDepositAmount = new JLabel();
	private JTextField txtTotalPayment = new JTextField();
	private JTextField txtDepositAmount = new JTextField();
	
//	private BookRuralHouseBL BL = new BookRuralHouseBL();
	private ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();

	public BookRuralHouseConfirmationWindow(Booking booking, Offer offer, String email, String telephone) {
		getContentPane().setBackground(new Color(152, 251, 152));
		try {
			jbInit(booking,offer, email, telephone);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(final Booking booking, final Offer offer, final String email, final String telephone) throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(460, 360));
		this.setTitle("Confirm Booking");
		this.setResizable(false);
		lblBankAccNum.setText("Owner Bank account number:");
		lblBankAccNum.setBounds(new Rectangle(20, 39, 200, 25));
		txtBankAccNum.setBounds(new Rectangle(225, 39, 165, 25));
		txtBankAccNum.setEditable(false);
		txtBankAccNum.setText(booking.getOffer().getRuralHouse().getOwner().getBankAccount());

		lblBookingNum.setText("Booking number:");
		lblBookingNum.setBounds(new Rectangle(20, 75, 130, 25));
		txtBookingNum.setBounds(new Rectangle(225, 75, 165, 25));
		txtBookingNum.setEditable(false);

		txtBookingNum.setText(Integer.toString(booking.getBookNumber()));

		lblDepositAdvertise.setText("You ought to transfer 20% of the total price of the booking in the next three days.");
		lblDepositAdvertise.setBounds(new Rectangle(20, 127, 424, 37));
		btnAccept.setBackground(new Color(152, 251, 152));
		btnAccept.setText("Accept");
		btnAccept.setBounds(new Rectangle(144, 273, 130, 30));
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAccept_actionPerformed(e,booking,offer,email,telephone);
			}
		});
		lblTotalPayment.setText("Total:");
		lblTotalPayment.setBounds(new Rectangle(70, 175, 85, 25));
		lblDepositAmount.setText("Deposit amount:");
		lblDepositAmount.setBounds(new Rectangle(70, 225, 100, 25));
		txtTotalPayment.setBounds(new Rectangle(180, 175, 115, 25));
		txtTotalPayment.setEditable(false);

		txtTotalPayment.setText(Float.toString(booking.getPrice()) + " €");
		txtDepositAmount.setBounds(new Rectangle(180, 225, 115, 25));
		txtDepositAmount.setEditable(false);
		txtDepositAmount.setText(Float.toString(booking.getPrice()*(float)0.2) + " €");
		this.getContentPane().add(txtDepositAmount, null);
		this.getContentPane().add(txtTotalPayment, null);
		this.getContentPane().add(lblDepositAmount, null);
		this.getContentPane().add(lblTotalPayment, null);
		this.getContentPane().add(btnAccept, null);
		this.getContentPane().add(lblDepositAdvertise, null);
		this.getContentPane().add(txtBookingNum, null);
		this.getContentPane().add(lblBookingNum, null);
		this.getContentPane().add(txtBankAccNum, null);
		this.getContentPane().add(lblBankAccNum, null);
	}

	private void btnAccept_actionPerformed(ActionEvent e, Booking booking, Offer offer, String email, String telephone) {
		

		//IF LOGGED
		if(StartWindow.CLIENT != null){
			Client client = new Client();
			client = StartWindow.CLIENT;
			try {
				facade.bookRuralHouse(booking, offer,client);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setVisible(false);
		}else{
			Client client = new Client(email, null, null);		
				try {
					if(facade.clientRegistered(client)!=null){
						JFrame a = new ClientAuthenticationGUI();
						a.setVisible(true);
					}else{
						client = new Client(email,"password",telephone);
						facade.registerClient(client);
						facade.bookRuralHouse(booking, offer,client);
						StartWindow.CLIENT=client;
						JOptionPane.showMessageDialog(null, "User registered!");
						setVisible(false);
						
					}
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}			
	}
	
}