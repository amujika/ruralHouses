package businessLogic;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import dataAccess.DB4oManager;


public final class BookingManager {

	private int bookingNumber = 0;
	dataAccess.DB4oManager dbMngr;

	private static BookingManager theBookingManager;

	private BookingManager() {}
	
	/**
	 * This method returns the next Booking number 
	 * 
	 * @return the book number
	 */
    public static int getNumber(){
		ObjectContainer db=DB4oManager.getContainer();
    	BookingManager b=getInstance();
    	b.bookingNumber++;
    	db.store(b);
    	db.commit();
    	return b.bookingNumber;
    }
	
	/**
	 * This method returns the instance of the BookingManager class 
	 * 
	 * @return the booking manager
	 */
	public static BookingManager getInstance()  {
		ObjectContainer db=DB4oManager.getContainer();
	    BookingManager b = new BookingManager();
	    ObjectSet result = db.queryByExample(b);
	    if (!result.hasNext()){
	    	theBookingManager = new BookingManager();
	    	db.store(theBookingManager);
	    	db.commit();
	    } else theBookingManager=(BookingManager)result.next();
		return theBookingManager;
	}
	
}