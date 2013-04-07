package businessLogic;

import java.awt.Image;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import dataAccess.DB4oManager;

import domain.Owner;
import domain.RuralHouse;

public class AddRuralHouseBL {

	dataAccess.DB4oManager dbMngr;

	public AddRuralHouseBL(){}

	public void addRuralHouse(int houseNumber, Owner owner, String description, Image image, String town){ 
		RuralHouse rh=owner.addRuralHouse(houseNumber, description, image, town);
		DB4oManager.getInstance().addRuralHouse(owner,rh);		
	}

}
