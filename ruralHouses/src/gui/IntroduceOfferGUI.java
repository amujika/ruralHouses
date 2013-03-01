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
	private JComboBox jCBOwner = null;
	private JButton btnShowHouses = null;

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
			jContentPane.add(getJCBOwner(), null);
			jContentPane.add(getBtnShowHouses(), null);
		}
		return jContentPane;
	}

	private JComboBox getJCBOwner() {
		if (jCBOwner == null) {
			try {
				ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
				Vector<Owner> owners=facade.getOwners();
				jCBOwner = new JComboBox(owners);
				jCBOwner.setBounds(new Rectangle(63, 38, 175, 44));
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jCBOwner;
	}

	private JButton getBtnShowHouses() {
		if (btnShowHouses == null) {
			btnShowHouses = new JButton();
			btnShowHouses.setBounds(new Rectangle(113, 146, 95, 59));
			btnShowHouses.setText("Show houses");
			btnShowHouses.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Owner owner=(Owner)jCBOwner.getSelectedItem();
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
		return btnShowHouses;
	}

}  //  @jve:decl-index=0:visual-constraint="222,33"
