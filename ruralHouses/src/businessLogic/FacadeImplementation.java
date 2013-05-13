package businessLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.Date;
import java.util.Vector;

import java.sql.SQLException;

//import com.db4o.ObjectContainer;
//import com.db4o.ObjectSet;
//import configuration.Config;

import dataAccess.DB4oManager;

import domain.Booking;
import domain.Client;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;

//import exceptions.OfferCanNotBeBooked;

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

	public Vector<Booking> getBookings(RuralHouse rh){
		return dbMngr.getBookingByRH(rh);
	}

	public void storePayment(Booking booking){
		dbMngr.storePayment(booking);
	}

	public boolean paymentDone(Booking booking){
		return dbMngr.paymentDone(booking);
	}

	public void close() throws RemoteException{
		DB4oManager.close();
	}

	//addRuralHouse

	public void addRuralHouse(int houseNumber, Owner owner, String description, String image, String town){ 
		RuralHouse rh=owner.addRuralHouse(houseNumber, description, image, town);
		DB4oManager.getInstance().addRuralHouse(owner,rh);}

	//introduceOffer

	public void createOffer(RuralHouse rh, Date firstDay, Date lastDay, Float price){ 
		DB4oManager.getInstance().createOffer(rh, firstDay, lastDay, price);		
	}

	//ownerLogin
	public Owner ownerloginBL (Owner own) {		
		DB4oManager dbManager = DB4oManager.getInstance();		
		return dbManager.getOwner(own);		
	}

	//clientRegistration
	public Client clientRegistered (Client cli) {		
		DB4oManager dbManager = DB4oManager.getInstance();		
		return dbManager.getClient(cli);		
	}

	public void registerClient (Client cli){
		DB4oManager dbManager = DB4oManager.getInstance();		
		dbManager.registerClient(cli);
	}

	//ownerRegistration	
	public void registerOwner(Owner owner) {
		DB4oManager dbManager = DB4oManager.getInstance();		
		dbManager.registerOwner(owner);		
	}

	//removeRuralHouse

	public void RemoveRuralHouse(RuralHouse rh){
		Owner owner = rh.getOwner();
		owner.removeRuralHouse(rh);
		DB4oManager.getInstance().updateOwner(owner);
		DB4oManager.getInstance().removeRuralHouse(rh);		
	}

	//bookRuralHouse

	public void bookRuralHouse (Booking booking, Offer offer,Client client) {
		try {
			DB4oManager dbManager = DB4oManager.getInstance();
			dbManager.storeBooking(booking, offer,client);
		} catch (Exception e) {
			System.out.println("There has been an error in businessLogic > DBookRuralHouseBL in line " + new Throwable().getStackTrace()[0].getLineNumber());
		}
	}

	public RuralHouse getRuralHouseByNumber (int houseNumber) {
		DB4oManager dbManager = DB4oManager.getInstance();
		return dbManager.getRuralHouseByNumber(houseNumber);
	}

	public Offer getOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay){
		DB4oManager dbManager = DB4oManager.getInstance();
		return dbManager.getOffer(new Offer(ruralHouse, firstDay, lastDay, 0, 0)); 
	}

	//cancelBooking

	public Vector<Booking> getBookings(Client client){
		return client.getBookings();
	}

	public void cancelBooking(Booking booking, Client client){
		DB4oManager dbManager = DB4oManager.getInstance();
		dbManager.removeBooking(booking,client);
	}

	@Override
	public void storeRuralHouse(RuralHouse rh) throws RemoteException {
		dbMngr.storeRuralHouse(rh);
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

