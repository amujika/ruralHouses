package domain;

import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class Client implements Serializable, Comparable<Client> {

	private String telephoneNumber;
	private String name;
	private String username;
	private String password;
	private Vector<Booking> bookings;
	//private Vector<RuralHouse> ruralHouses;

	public Client(String name,String login, String password) {
		this.name=name;
		this.username=login;
		this.password=password;
		bookings=new Vector<Booking>();
		//ruralHouses=new Vector<RuralHouse>();		
	}

	public Client(String name,String login, String password, String telephoneNumber) {
		this.telephoneNumber=telephoneNumber;
		this.name=name;
		this.username=login;
		this.password=password;
		bookings=new Vector<Booking>();
		//ruralHouses=new Vector<RuralHouse>();		
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
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
	
	public Vector<Booking> getBookings(){
		return this.bookings;
	}
	
	public void addBooking (Booking b){
		this.bookings.add(b);
	}

	//	public Vector<RuralHouse> getRuralHouses() {
	//		return ruralHouses;
	//	}
	//
	//	public RuralHouse addRuralHouse(int houseNumber, String description, String city) {
	//		RuralHouse rh=new RuralHouse(houseNumber,  this,  description,  city);
	//		ruralHouses.add(rh);
	//		return rh;
	//	}

	public String toString(){
		return name;
	}

	public int compareTo(Client other) {
		return (this.telephoneNumber + '\n' + this.name + '\n' + this.password
				+ '\n' + this.username).compareTo(other.telephoneNumber + '\n'
						+ other.name + '\n' + other.password + '\n' + other.username);
	}

	public boolean equals(Object o) {
		Client other= (Client) o;		
		return ( this.password + '\n' + this.username).equals(other.password + '\n' + other.username);
	}
}
