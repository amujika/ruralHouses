package businessLogic;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import dataAccess.DB4oManager;

import domain.Owner;
import domain.RuralHouse;

public class AddRuralHouseBL {
	
	dataAccess.DB4oManager dbMngr;
	
	public AddRuralHouseBL(){}
	
	public void addRuralHouse(int houseNumber, Owner owner, String description, String town){ 
		RuralHouse rh=new RuralHouse(houseNumber, owner, description, town);
		dbMngr.addRuralHouse(rh);
	}

}
