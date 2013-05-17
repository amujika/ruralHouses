package domain;

import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class Client implements Serializable, Comparable<Client> {
	
	private String email;
	private String password;
	private String telephoneNumber;
	private Vector<Booking> bookings;
	//private Vector<RuralHouse> ruralHouses;
	
	public Client(){
	}
	
	public Client(String email, String password, String telephoneNumber){
		this.email = email;
		this.password = password;
		this.telephoneNumber = telephoneNumber;
		this.bookings = new Vector<Booking>();
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	public Vector<Booking> getBookings(){
		return this.bookings;
	}
	
	public void addBooking (Booking b){
		this.bookings.add(b);
	}
	
	public void removeBooking (Booking b){
		this.bookings.removeElement(b);
	}

	public String toString(){
		return email;
	}

	public int compareTo(Client other) {
		return (this.email + '\n' + '\n' + this.password
				+ '\n' + this.telephoneNumber).compareTo(other.email + '\n'
						+ other.password + '\n' + '\n' + other.telephoneNumber);
	}

	public boolean equals(Object o) {
		Client other= (Client) o;		
		return ( this.password + '\n' + this.email).equals(other.password + '\n' + other.email);
	}
}
