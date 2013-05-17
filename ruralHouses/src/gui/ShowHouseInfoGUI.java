package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.RuralHouse;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class ShowHouseInfoGUI extends JFrame {

	private JPanel contentPane;
	private JCheckBox chckbxWifi = new JCheckBox();


	/**
	 * Create the frame.
	 */
	public ShowHouseInfoGUI(RuralHouse rh) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 0, 427, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String comments="";
		for (String c:rh.comments)
			comments += c +"<br>";
		if (comments == "") 
			comments = "No comments yet";
		JLabel lblHouseNum = new JLabel();
		lblHouseNum.setVerticalAlignment(SwingConstants.TOP);
		lblHouseNum.setHorizontalAlignment(SwingConstants.LEFT);
		lblHouseNum.setText("<html>-Number of bedrooms: " + rh.getBedRooms()
				+ "<br>Number of bathrooms: " + rh.getBathRooms() + "<br>Number of kitchens: " + rh.getKitchens()+ "<br>Number of dining rooms: " + 
				rh.getDiningRooms() + "<br>Number of parking spaces: " + rh.getParkingSpaces()  + "<br>Wifi: "
				+ rh.getWifi() + "<br>Comments: <br>" + comments +"</html>");
		if (rh.getImage() != null) {
			JLabel l = new JLabel();
			ImageIcon icon = new ImageIcon(rh.getImage());
			l.setIcon(new ImageIcon(icon.getImage().getScaledInstance(190, 190, java.awt.Image.SCALE_SMOOTH)));
			l.setBounds(10,10,200,200);
			contentPane.add(l);			
		}
		
		lblHouseNum.setBounds(10, 211, 312, 215);
		contentPane.add(lblHouseNum);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
}
