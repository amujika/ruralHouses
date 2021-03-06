package dataAccess;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

//import javax.swing.ImageIcon;
//import java.util.Enumeration;
//import java.util.Vector;

import com.db4o.*;

import configuration.Config;

import domain.Booking;
import domain.Client;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;

public class DB4oManager { 

	private static ObjectContainer db;
	private static DB4oManager theDB4oManager=new DB4oManager();

	private DB4oManager() {
		Config c=Config.getInstance();
		String dataBaseOpenMode=c.getDataBaseOpenMode();
		DB4oManager.openDatabase(dataBaseOpenMode);
	}

	public static void openDatabase(String mode) {
		Config c=Config.getInstance();
		String db4oFileName=c.getDb4oFilename();
		if (mode.compareTo("open")==0) {
			db=Db4o.openFile(Db4o.newConfiguration(), db4oFileName);
			db.ext().configure().updateDepth(5);
			System.out.println("DataBase Opened");

		} else if (mode.compareTo("initialize")==0){
			new File(db4oFileName).delete();
			db=Db4o.openFile(Db4o.newConfiguration(), db4oFileName);
			db.ext().configure().updateDepth(5);
			Owner jon = new Owner("Jon", "userJon", "passJon");
			Owner alfredo = new Owner("Alfredo","userAlfredo", "passAlfredo");
			String img = null;
			jon.addRuralHouse(1,3, 5, 2, 1, 1, true, img, "Ezkio");
			jon.addRuralHouse(2, 2, 3, 1, 0, 2, false, img, "Jaca");
			jon.setBankAccount("1349 5677 21 2133567777");
			alfredo.addRuralHouse(3, 3, 5, 2, 1, 2, false, img, "Pitillas");
			alfredo.addRuralHouse(4, 1, 2, 1, 0, 1, false, img, "Murgia");
			alfredo.setBankAccount("4144 0087 23 9700002133");

			db.store(jon);
			db.store(alfredo);
			db.commit();
			System.out.println("DataBase Initialized");
		}
	}

	public  static ObjectContainer getContainer() {
		return db;
	}
	public static void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	/**
	 * This method finds all existing  owners 
	 * 
	 */
	public Vector<Owner> getOwners() throws RemoteException,
	Exception {
		ObjectContainer db=DB4oManager.getContainer();

		try {
			Owner proto = new Owner(null,null,null,null);
			ObjectSet<Owner> result = db.queryByExample(proto);
			Vector<Owner> owners=new Vector<Owner>();
			while(result.hasNext())				 
				owners.add(result.next());
			return owners;
		} finally {
			//db.close();
		}
	}

	public Owner getOwner(Owner owner) {
		ObjectContainer db = DB4oManager.getContainer();

		try {
			List<Owner> result = db.queryByExample(owner);

			if (result.isEmpty()) {
				return null;
			} else {
				return result.get(0);
			} // END if

		} finally {
			// db.close();
		}
	}

	public Client getClient(Client client) {
		ObjectContainer db = DB4oManager.getContainer();

		try {
			List<Client> result = db.queryByExample(client);

			if (result.isEmpty()) {
				return null;
			} else {
				return result.get(0);
			} // END if

		} finally {
			// db.close();
		}
	}

	public void registerClient(Client client){
		db.store(client);
		db.commit();
	}

	public void registerOwner(Owner owner) {
		db.store(owner);
		db.commit();		
	}

	public Vector<RuralHouse> getAllRuralHouses() throws RemoteException,
	Exception {
		ObjectContainer db=DB4oManager.getContainer();
		try {
			RuralHouse proto = new RuralHouse(0, null, 0, 0, 0, 0, 0, null, null, null);			ObjectSet<Object> result = db.queryByExample(proto);
			Vector<RuralHouse> ruralHouses=new Vector<RuralHouse>();
			while(result.hasNext()) 
				ruralHouses.add((RuralHouse)result.next());
			return ruralHouses;
		} finally {
			//db.close();
		}
	}

	//Returns the RuralHouse with the specified houseNumber
	public RuralHouse getRuralHouseByNumber(int houseNumber){
		try {
			ObjectContainer db=DB4oManager.getContainer();
			RuralHouse rh = new RuralHouse(houseNumber, null, 0, 0, 0, 0, 0, null, null, null);			ObjectSet<RuralHouse> result = db.queryByExample(rh);
			return result.next();
		} catch (Exception e) {
			System.out.println("There has been an error in dataAccess > DB4oManager in line " + new Throwable().getStackTrace()[0].getLineNumber());
			return null;
		}
	}

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * precondition: There are no overlapping offers
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price){
		ObjectContainer db=DB4oManager.getContainer();
		RuralHouse proto = new RuralHouse(ruralHouse.getHouseNumber(), null, 
				ruralHouse.getBedRooms(), ruralHouse.getBathRooms(), ruralHouse.getKitchens(), ruralHouse.getDiningRooms(),
				ruralHouse.getParkingSpaces(), ruralHouse.getWifi(), ruralHouse.getImage(), ruralHouse.getTown());		ObjectSet<Object> result = db.queryByExample(proto);
		RuralHouse rh=(RuralHouse)result.next();
		Offer o=rh.createOffer(firstDay, lastDay, price);
		db.store(o);
		db.commit(); 
		return o;
	}

	public Offer getOffer(Offer offer){
		List<Offer> result = db.queryByExample(new Offer(offer.getOfferNumber()));

		if (result.isEmpty())
			return null;
		else
			return result.get(0);		
	} 

	public RuralHouse getRuralHouse(RuralHouse rh){
		try {
			ObjectContainer db=DB4oManager.getContainer();
			RuralHouse proto = new RuralHouse(rh.getHouseNumber(), null,
					rh.getBedRooms(), rh.getBathRooms(),rh.getKitchens(), rh.getDiningRooms(), rh.getParkingSpaces(),
					rh.getWifi(), rh.getImage(), rh.getTown());			ObjectSet<Object> result = db.queryByExample(proto);
			return  rh=(RuralHouse)result.next();
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}
	
	public Booking getBooking (Booking b) {
		List<Booking> result = db.queryByExample(new Booking(b.getBookNumber()));

		if (result.isEmpty())
			return null;
		else
			return result.get(0);
	}

	public Vector<Booking> getBookingByRH(RuralHouse rh){
		try{
			RuralHouse ruHo = getRuralHouse(rh);
			Vector<Offer> of = ruHo.offers;
			Vector<Booking> bookings = new Vector <Booking>();
			for (int i=0; i<of.size();i++){
				bookings.add(of.elementAt(i).getBooking());			
			}
			return bookings;
		}catch(Exception exc){
			exc.printStackTrace();
			return null;
		}
	}

	public void storePayment(Booking booking){
		booking.setIsPaid(true);
		db.store(booking);
		db.commit();
	}

	public boolean paymentDone(Booking booking){
		return booking.isPaid();
	}

	public void storeBooking(Booking booking, Offer offer, Client client){
		db.store(client);
		db.store(booking);
		db.store(offer);
		db.commit();

	}

	public void addRuralHouse(Owner owner, RuralHouse rh){
		db.store(owner);
		db.store(rh);
		db.commit();
	}

	public void removeRuralHouse(RuralHouse rh){
		db.delete(rh);
		db.commit();
	}

	public void removeBooking(Booking booking, Client client){
		db.store(client);
		db.delete(booking);
		db.store(booking.getOffer());
		db.commit();
	}

	/**
	 * This method returns the instance of the DB4oManager class 
	 * 
	 * @return the db4o manager
	 */
	public static DB4oManager getInstance()  {
		return theDB4oManager;
	}

	public void updateOwner(Owner owner) {
		db.store(owner);
		db.commit();		
	}

	public void storeRuralHouse(RuralHouse rh) {
		db.store(rh);
		db.commit();
	}

}

