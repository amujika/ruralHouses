package businessLogic;
import java.awt.Color;

import dataAccess.DB4oManager;
import domain.Owner;

public class OwnerLoginBL {
	
	public OwnerLoginBL(){}
	
	public Owner ownerloginBL (Owner own) {
		
		DB4oManager dbManager = DB4oManager.getInstance();		
		return dbManager.getOwner(own);
		
	} // END Constructor

}
