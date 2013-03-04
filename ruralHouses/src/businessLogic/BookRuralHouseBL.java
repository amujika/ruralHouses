package businessLogic;

import java.util.Date;

import dataAccess.DB4oManager;
import domain.Booking;
import domain.Offer;
import domain.RuralHouse;

public class BookRuralHouseBL {


	public BookRuralHouseBL() {
	}

	public void bookRuralHouse (Booking booking, Offer offer) {
		try {
			DB4oManager dbManager = DB4oManager.getInstance();
			dbManager.storeBooking(booking, offer);
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
}
