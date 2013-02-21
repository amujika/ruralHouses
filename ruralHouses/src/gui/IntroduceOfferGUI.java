package gui;

import javax.swing.*;
import java.awt.Rectangle;

import java.util.Vector;

import businessLogic.ApplicationFacadeInterface;
import domain.Owner;
import domain.RuralHouse;


public class IntroduceOfferGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JComboBox jComboBox = null;
	private JButton jButton = null;

	public IntroduceOfferGUI() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(449, 293);
		this.setContentPane(getJContentPane());
		this.setTitle("Choose owner");

	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(getJButton(), null);
		}
		return jContentPane;
	}

	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			try {
				ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
				Vector<Owner> owners=facade.getOwners();
				jComboBox = new JComboBox(owners);
				jComboBox.setBounds(new Rectangle(63, 38, 175, 44));
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jComboBox;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(113, 146, 95, 59));
			jButton.setText("Show houses");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Owner owner=(Owner)jComboBox.getSelectedItem();
					System.out.println(owner.getUsername());
					Vector<RuralHouse> houseList=null;
					try {
						//Obtain the business logic from a StartWindow class (local or remote)
						ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
						houseList=facade.getRuralHouses(owner);

					}
					catch (Exception e1) {
						e1.printStackTrace();
					}
					if (houseList.isEmpty()!=true) {
						JFrame a = new IntroduceOffer2GUI(houseList);
						a.setVisible(true);
					}
					else if (houseList.isEmpty()==true) {
						System.out.print("Owner does not exist or has no registered houses ");
					} 		
				}
			});
		}
		return jButton;
	}

}  //  @jve:decl-index=0:visual-constraint="222,33"
