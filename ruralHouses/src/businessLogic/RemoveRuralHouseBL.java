package businessLogic;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import dataAccess.DB4oManager;

import domain.Owner;
import domain.RuralHouse;

public class RemoveRuralHouseBL {
	
	dataAccess.DB4oManager dbMngr;
	
	public RemoveRuralHouseBL(){}
	
	public void RemoveRuralHouse(RuralHouse rh){ 
		DB4oManager.getInstance().removeRuralHouse(rh);		
	}

}