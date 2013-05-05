package businessLogic;

import java.rmi.*;

import java.util.Vector;
import java.util.Date;

import dataAccess.DB4oManager;
import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;

import exceptions.OfferCanNotBeBooked; 

public interface ApplicationFacadeInterface extends Remote {

	/**
	 * This method obtains an owner's rural houses 
	 * 
	 * @param owner object
	 *            
	 * @return a vector of Rural Houses
	 */
	Vector<RuralHouse> getRuralHouses(Owner owner)
			throws RemoteException;

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */

	Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws RemoteException, Exception;

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	/*Booking createBooking(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			String telephoneNumber) throws RemoteException, OfferCanNotBeBooked;*/

	/**
	 * This method obtains available offers for a concrete house in a certain period 
	 * 
	 * @param houseNumber, the house number where the offers must be obtained 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return a vector of offers(Offer class)  available  in this period
	 */
	Vector<Offer> getOffers(RuralHouse houseNumber, Date firstDay, Date lastDay) 
			throws RemoteException, Exception;
	
	/**
	 * This method finds existing  owners 
	 * 
	 */
	public Vector<Owner> getOwners() throws RemoteException,
			Exception;
	
	public Vector<RuralHouse> getAllRuralHouses()throws RemoteException,
	Exception;
	
	public Vector<Booking> getBookings(RuralHouse rh)throws RemoteException,
	Exception;
	
	public void storePayment(Booking booking) throws RemoteException,
	Exception;
	
	public boolean paymentDone(Booking booking) throws RemoteException,
	Exception;
	
	public void close() throws RemoteException;
	
	//addRuralHouse
	public void addRuralHouse(int houseNumber, Owner owner, String description, String image, String town) throws RemoteException;
	
	//introduceOffer
	public void createOffer(RuralHouse rh, Date firstDay, Date lastDay, Float price) throws RemoteException;
	
	//ownerLogin
	public Owner ownerloginBL (Owner own) throws RemoteException;
	
	//removeRuralHouse
	public void RemoveRuralHouse(RuralHouse rh)throws RemoteException;
	
	//bookRuralHouse
	public void bookRuralHouse (Booking booking, Offer offer) throws RemoteException;

	public RuralHouse getRuralHouseByNumber (int houseNumber) throws RemoteException;
	
	public Offer getOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay) throws RemoteException;
	
}