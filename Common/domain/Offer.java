package domain;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Vector;
import java.util.Date;
import businessLogic.OfferManager;
import com.db4o.ObjectContainer;
import dataAccess.DB4oManager;

@SuppressWarnings("serial")
public class Offer implements Serializable {
	
	private int offerNumber;
	private Date firstDay;
	private Date lastDay;
	private float price;
	private Booking booking;
	private RuralHouse ruralHouse;
	
	public Offer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price) {
		  this.firstDay=firstDay;
		  this.lastDay=lastDay;
		  this.price=price;
		  this.ruralHouse=ruralHouse;
		  this.offerNumber=OfferManager.getNumber();
	}
	
	public Offer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price, int number){
		  this.firstDay=firstDay;
		  this.lastDay=lastDay;
		  this.price=price;
		  this.ruralHouse=ruralHouse;
		  this.offerNumber=number;
	}
	
	public RuralHouse getRuralHouse() {
		return this.ruralHouse;
	}

	public void setRuralHouse(RuralHouse ruralHouse) {
		this.ruralHouse = ruralHouse;
	}

	public int getOfferNumber() {
		return this.offerNumber;
	}

	public Date getFirstDay() {
		return this.firstDay;
	}

	public void setFirstDay(Date firstDay) {
		this.firstDay = firstDay;
	}

	public Date getLastDay() {
		return this.lastDay;
	}

	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	public Booking createBook(String bookTelephoneNumber) {
		Booking b=new Booking(bookTelephoneNumber, this);
		booking=b;
		return booking;			
	}
	
	public String toString(){
		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("dd/MM/yyyy");
		return "From " + df.format(firstDay) + " to " + df.format(lastDay) + " for " + price +"€";
	}
	
	
}