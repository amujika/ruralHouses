package domain;

import java.awt.Image;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;

public class RuralHouse implements Serializable {

	private static final long serialVersionUID = 1L;

	private int houseNumber;
	private int numberOfRooms;
	private int numberOfBeds;
	private Boolean wifi;
	private String image;
	private Owner owner;
	private String town; 
	public Vector<Offer> offers;
	public Vector<String> comments;

	public RuralHouse() {
		super();
	}

	public RuralHouse(int houseNumber, Owner owner, int numberOfRooms, int numberOfBeds, Boolean wifi, String image, String town) {
		this.houseNumber = houseNumber;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBeds = numberOfBeds;
		this.wifi = wifi;
		this.image = image;
		this.owner = owner;
		this.town = town;
		offers=new Vector<Offer>();
		comments = new Vector<String>();
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public int getnumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms=numberOfRooms;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds=numberOfBeds;
	}

	public boolean getWifi() {
		return wifi;
	}

	public void setWifi (Boolean wifi) {
		this.wifi=wifi;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image=image;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner=owner;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town=town;
	}

	public String toString() {
		return this.houseNumber + ": " + this.town;
	}

	public Offer createOffer(Date firstDay, Date lastDay, float price) {
		Offer off=new Offer(this, firstDay, lastDay, price);
		offers.add(off);
		return off;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + houseNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuralHouse other = (RuralHouse) obj;
		if (houseNumber != other.houseNumber)
			return false;
		return true;
	}

	public String getAccountNumber(int houseNumber) {
		/*try {
			dbMngr=DBManager.getInstance();
			return dbMngr.getOwner(houseNumber).getBankAccount();

		} catch (Exception e) {
			System.out.println("Error, accessing to DB Manager: "
					+ e.toString());
			return null;
		}*/ return null;
	}

	/**
	 * This method obtains available offers for a concrete house in a certain period 
	 * 
	 * @param houseNumber, the house number where the offers must be obtained 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return a vector of offers(Offer class)  available  in this period
	 */
	public Vector<Offer> getOffers( Date firstDay,  Date lastDay) {
		Vector<Offer> availableOffers=new Vector<Offer>();
		Iterator<Offer> e=offers.iterator();
		Offer offer;
		while (e.hasNext()){
			offer=e.next();
			if ( (offer.getFirstDay().compareTo(firstDay)>=0) && (offer.getLastDay().compareTo(lastDay)<=0) && (offer.getBooking()==null) )
				availableOffers.add(offer);
		}
		return availableOffers;
	}

	/**
	 * This method obtains the offer that match exactly with a given dates that has not been booked
	 * 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return the  offer(Offer class)  available  for a this period
	 */
	public Offer findOffer( Date firstDay,  Date lastDay) {		
		Iterator<Offer> e=offers.iterator();
		Offer offer=null;
		while (e.hasNext()){
			offer=e.next();
			if ( (offer.getFirstDay().compareTo(firstDay)==0) && (offer.getLastDay().compareTo(lastDay)==0) && (offer.getBooking()==null) )
				return offer;
		}
		return null;		
	}

}
