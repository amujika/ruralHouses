package domain;

import java.io.Serializable;
import java.util.Vector;

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

	public RuralHouse addRuralHouse(int houseNumber, String description, String city) {
		RuralHouse rh=new RuralHouse(houseNumber,  this,  description,  city);
		ruralHouses.add(rh);
		return rh;
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