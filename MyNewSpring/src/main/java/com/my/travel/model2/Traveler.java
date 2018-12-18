package com.my.travel.model2;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * The persistent class for the traveler database table.
 * 
 */
@Entity(name = "TRAVELER")
//@NamedQuery(name="TRIP.findAll", query="SELECT t FROM TRIP t")
@NamedQuery(name="TRAVELER.findAll", query="SELECT t FROM TRAVELER t")
@Table(name = "TRAVELER")//
public class Traveler implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDTRAVELER")//
	private int idtraveler;

	@Temporal(TemporalType.DATE)
	@Column(name="TRAVELER_BIRTHDATE")
	private Date travelerBirthdate;

	@Column(name="traveler_name")
	private String travelerName;

	//bi-directional many-to-one association to Trip
	@OneToMany(mappedBy="traveler")
	private List<Trip> trips;

	public Traveler() {
	}

	public int getIdtraveler() {
		return this.idtraveler;
	}

	public void setIdtraveler(int idtraveler) {
		this.idtraveler = idtraveler;
	}

	public Date getTravelerBirthdate() {
		return this.travelerBirthdate;
	}

	public void setTravelerBirthdate(Date travelerBirthdate) {
		this.travelerBirthdate = travelerBirthdate;
	}

	public String getTravelerName() {
		return this.travelerName;
	}

	public void setTravelerName(String travelerName) {
		this.travelerName = travelerName;
	}

	public List<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public Trip addTrip(Trip trip) {
		getTrips().add(trip);
		trip.setTraveler(this);

		return trip;
	}

	public Trip removeTrip(Trip trip) {
		getTrips().remove(trip);
		trip.setTraveler(null);

		return trip;
	}
	public String toString() {
//		SimpleDateFormat sdf = new SimpleDateFormat(
//			    "MM-dd-yyyy");
		 long millisecondsSince = new Date().getTime() - this.getTravelerBirthdate().getTime();
		    long days= TimeUnit.DAYS.convert(millisecondsSince, TimeUnit.MILLISECONDS);
		    return this.getTravelerName() +" age "+ (int) (days/365.25);
	}
}