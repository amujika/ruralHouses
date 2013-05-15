package businessLogic;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import dataAccess.DB4oManager;

public final class OfferManager {

	private int offerNumber = 0;
	dataAccess.DB4oManager dbMngr;
	private static OfferManager theOfferManager;

	private OfferManager() {}

	public static int getNumber() {
		ObjectContainer db=DB4oManager.getContainer();
		OfferManager o=getInstance();
		o.offerNumber++;
		db.store(o);
		db.commit();
		return o.offerNumber;
	}

	/**
	 * This method returns the instance of the OfferManager class 
	 * 
	 * @return the offer manager
	 */
	public static OfferManager getInstance()  {
		ObjectContainer db=DB4oManager.getContainer();
		OfferManager b = new OfferManager();
		ObjectSet result = db.queryByExample(b);
		if (!result.hasNext()){
			theOfferManager = new OfferManager();
			db.store(theOfferManager);
			db.commit();
		} else theOfferManager=(OfferManager)result.next();
		return theOfferManager;
	}
}
