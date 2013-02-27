package dataAccess;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
//import java.util.Enumeration;
//import java.util.Vector;

import businessLogic.BookingManager;

import com.db4o.*;

import configuration.Config;

import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import exceptions.OfferCanNotBeBooked;

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
			jon.addRuralHouse(1, "Ezkioko etxea","Ezkio");
			jon.addRuralHouse(2, "Eskiatzeko etxea","Jaca");
			jon.setBankAccount("1349 5677 21 2133567777");
			alfredo.addRuralHouse(3, "Casa del abuelo","Pitillas");
			alfredo.addRuralHouse(4, "Refugio","Murgia");
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
			ObjectSet result = db.queryByExample(proto);
			Vector<Owner> owners=new Vector<Owner>();
			while(result.hasNext())				 
				owners.add((Owner)result.next());
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

	public Vector<RuralHouse> getAllRuralHouses() throws RemoteException,
	Exception {
		ObjectContainer db=DB4oManager.getContainer();
		try {
			RuralHouse proto = new RuralHouse(0,null,null,null);
			ObjectSet result = db.queryByExample(proto);
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
			RuralHouse rh = new RuralHouse(houseNumber, null, null, null);
			ObjectSet<RuralHouse> result = db.queryByExample(rh);
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
				ruralHouse.getDescription(), ruralHouse.getTown());
		ObjectSet result = db.queryByExample(proto);
		RuralHouse rh=(RuralHouse)result.next();
		Offer o=rh.createOffer(firstDay, lastDay, price);
		db.store(o);
		db.commit(); 
		return o;
	}
	

	public RuralHouse getRuralHouse(RuralHouse rh){
		try {
			ObjectContainer db=DB4oManager.getContainer();
			RuralHouse proto = new RuralHouse(rh.getHouseNumber(), null,
					rh.getDescription(), rh.getTown());
			ObjectSet result = db.queryByExample(proto);
			return  rh=(RuralHouse)result.next();
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	public Booking createBooking(Offer offer, String clientTelephoneNumber)
			throws OfferCanNotBeBooked {
		try {
			Booking b=null;
			if (offer!=null) {
				b=offer.createBook(clientTelephoneNumber);
				db.store(b);
				db.store(offer);
				db.commit();
			}
			return b;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}
	
	public void addRuralHouse(RuralHouse rh){
		db.store(rh);
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

}

