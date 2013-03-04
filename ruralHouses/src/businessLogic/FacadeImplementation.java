package businessLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.Date;
import java.util.Vector;

import java.sql.SQLException;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import configuration.Config;

import dataAccess.DB4oManager;

import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;

import exceptions.OfferCanNotBeBooked;

public class FacadeImplementation extends UnicastRemoteObject implements ApplicationFacadeInterface {

	private static final long serialVersionUID = 1L;
	DB4oManager dbMngr;

	public FacadeImplementation() throws RemoteException, InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException {
		dbMngr = DB4oManager.getInstance();
	}

	/**
	 * This method obtains an owner's rural houses 
	 * 
	 * @param owner object
	 *            
	 * @return a vector of Rural Houses
	 */
	public Vector<RuralHouse> getRuralHouses(Owner owner)
			throws RemoteException {
		return owner.getRuralHouses();		
	}

	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws RemoteException, Exception {		 
		return dbMngr.createOffer(ruralHouse, firstDay, lastDay, price);
	}

	/**
	 * This method obtains available offers for a concrete house in a certain period 
	 * 
	 * @param houseNumber, the house number where the offers must be obtained 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return a vector of offers(Offer class)  available  in this period
	 */
	public Vector<Offer> getOffers(RuralHouse house,Date firstDay, Date lastDay) throws RemoteException,
	Exception {		
		return house.getOffers(firstDay, lastDay);

	}
	/**
	 * This method finds all existing  owners 
	 * 
	 */
	public Vector<Owner> getOwners() throws RemoteException,
	Exception {
		return dbMngr.getOwners();
	} 

	public Vector<RuralHouse> getAllRuralHouses() throws RemoteException,
	Exception {		
		return dbMngr.getAllRuralHouses();		
	}

	public void close() throws RemoteException{
		DB4oManager.close();
	}

	/*public Booking createBooking(RuralHouse ruralHouse, Date firstDate, Date lastDate, String bookTelephoneNumber)
			throws OfferCanNotBeBooked {
		try {
			RuralHouse rh=dbMngr.getRuralHouse(ruralHouse);
			Offer offer = rh.findOffer(firstDate, lastDate);
			Booking b=dbMngr.createBooking(offer,bookTelephoneNumber);
			return b;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}*/

}

