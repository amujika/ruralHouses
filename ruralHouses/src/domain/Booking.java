package domain;

import java.io.*;
import java.util.Date;

import businessLogic.BookingManager;

@SuppressWarnings("serial")
public class Booking implements Serializable {
	private int bookingNumber;
	private boolean isPaid;
	private Date bookingDate;
	private String telephone;
	private Offer offer;
	
	public Booking() {
	}

	public Booking(String telephone, Offer offer) {
		
		this.bookingNumber = BookingManager.getNumber();
		this.telephone=telephone;
		this.offer = offer;
		//this.price = price;
		//Booking date is assigned to actual date
		this.bookingDate= new java.util.Date(System.currentTimeMillis());
		this.isPaid=false;
	}
	
	public void imprimete(){
		System.out.println(bookingNumber);
		System.out.println(isPaid);
		System.out.println(bookingDate);
		System.out.println(telephone);
		System.out.println(offer);		
	}

	public int getBookNumber() {
		return this.bookingNumber;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public Offer getOffer() {
		return this.offer;
	}

	public float getPrice() {
		return this.offer.getPrice();
	}
	
	public void setBookDate(Date bookDate) {
		this.bookingDate = bookDate;
	}

	public Date getBookDate() {
		return this.bookingDate;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone() {
		return this.telephone;
	}
	
	public void paid() {
		this.isPaid = true;
	}

	public void notPaid() {
		this.isPaid=false;
	}

	public boolean isPaid() {
		return isPaid;
	}
	
}