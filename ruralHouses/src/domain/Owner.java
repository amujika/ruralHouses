package domain;

import java.awt.Image;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Owner implements Serializable, Comparable<Owner> {

	private String bankAccount;
	private String name;
	private String username;
	private String password;
	private Vector<RuralHouse> ruralHouses;

	public Owner(String name,String login, String password) {
		this.name=name;
		this.username=login;
		this.password=password;
		ruralHouses=new Vector<RuralHouse>();		
	}

	public Owner(String name,String login, String password, String bankAccount) {
		this.bankAccount=bankAccount;
		this.name=name;
		this.username=login;
		this.password=password;
		ruralHouses=new Vector<RuralHouse>();		
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Vector<RuralHouse> getRuralHouses() {
		return ruralHouses;
	}

	public RuralHouse addRuralHouse(int houseNumber, int bedRooms, int bathRooms, int kitchens, int diningRooms, int parkingSpaces, Boolean wifi, String image, String city) {
		RuralHouse rh=new RuralHouse(houseNumber,  this,  bedRooms, bathRooms, kitchens, diningRooms, parkingSpaces, wifi, image,  city);
		ruralHouses.add(rh);
		return rh;
	}
	
	public void changePropertiesRuralHouse(RuralHouse rh, int bedRooms, int bathRooms, Boolean wifi) {
		rh.setBedRooms(bedRooms);
		rh.setBathRooms(bathRooms);
		rh.setWifi(wifi);
	}

	public void removeRuralHouse(RuralHouse rh){
		ruralHouses.remove(rh);
	}

	public String toString(){
		return name;
	}

	public int compareTo(Owner other) {
		return (this.bankAccount + '\n' + this.name + '\n' + this.password
				+ '\n' + this.username).compareTo(other.bankAccount + '\n'
						+ other.name + '\n' + other.password + '\n' + other.username);
	}

	public boolean equals(Object o) {
		Owner other= (Owner) o;		
		return ( this.password + '\n' + this.username).equals(other.password + '\n' + other.username);
	}
}