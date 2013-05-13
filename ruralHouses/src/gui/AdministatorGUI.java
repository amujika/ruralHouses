package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import domain.Administrator;
import domain.Owner;
import domain.RuralHouse;

import businessLogic.ApplicationFacadeInterface;

public class AdministatorGUI extends JFrame {

	public static boolean clientServer = false;

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	public static ApplicationFacadeInterface facadeInterface;

	public AdministatorGUI() {
		super();
		initialize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static ApplicationFacadeInterface getBusinessLogic() {
		return facadeInterface;
	}

	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(460, 360);
		this.setContentPane(getJContentPane());
		this.setTitle("Use Cases");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(152, 251, 152));
			jContentPane.setLayout(null);
			jContentPane.add(getBtnRegisterOwner());
			{
				JLabel lblAdministrator = new JLabel("ADMINISTRATOR");
				lblAdministrator.setForeground(new Color(47, 79, 79));
				lblAdministrator.setFont(new Font("Calibri", Font.BOLD, 30));
				lblAdministrator.setBounds(115, 26, 227, 37);
				jContentPane.add(lblAdministrator);
			}
		}
		return jContentPane;
	}

	private JButton getBtnRegisterOwner() {
		JButton btnAddRH = new JButton("Register Owner");
		btnAddRH.setBackground(new Color(152, 251, 152));
		btnAddRH.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnAddRH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrator administrator = StartWindow.ADMINISTRATOR;

				if (administrator == null) {
					JOptionPane.showMessageDialog(null,
							"Not logged in. \nLog in please");
					// System.out.println("Not logged in. \nLog in please");
					return;
				}

				System.out.println("Logged in as: " + administrator.getName());

				JFrame a = new RegisterOwnerGUI(StartWindow.ADMINISTRATOR);
				a.setVisible(true);
			}
		});
		btnAddRH.setBounds(97, 80, 245, 37);
		return btnAddRH;
	}

} // @jve:decl-index=0:visual-constraint="0,0"
