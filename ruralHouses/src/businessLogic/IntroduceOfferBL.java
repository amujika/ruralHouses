package businessLogic;

import java.util.Date;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import dataAccess.DB4oManager;

import domain.RuralHouse;

public class IntroduceOfferBL {

	dataAccess.DB4oManager dbMngr;

	public IntroduceOfferBL(){
	}
	
	public void createOffer(RuralHouse rh, Date firstDay, Date lastDay, Float price){ 
		DB4oManager.getInstance().createOffer(rh, firstDay, lastDay, price);		
	}
	
	
}
