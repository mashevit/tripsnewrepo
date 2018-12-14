package com.my.travel.model2;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the trip database table.
 * 
 */
@Entity
@NamedQuery(name="Trip.findAll", query="SELECT t FROM Trip t")
public class Trip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtrip;

/*	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="trip_date")
	private Date tripDate;

	@Column(name="trip_hotel")
	private String tripHotel;

	@Column(name="trip_moneyspent")
	private int tripMoneyspent;

	@Column(name="trip_numdays")
	private int tripNumdays;

	@Column(name="TRIP_NUMMINTRANSIT")
	private int tripNummintransit;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="trip_city")
	private City city;

	//bi-directional many-to-one association to Traveler
	@ManyToOne
	@JoinColumn(name="Trip_id_traveller")
	private Traveler traveler;

	//bi-directional many-to-one association to TripSightseeing
	@OneToMany(mappedBy="trip")
	private List<TripSightseeing> tripSightseeings;

	public Trip() {
	}

	public int getIdtrip() {
		return this.idtrip;
	}

	public void setIdtrip(int idtrip) {
		this.idtrip = idtrip;
	}

	public Date getTripDate() {
		return this.tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	public String getTripHotel() {
		return this.tripHotel;
	}

	public void setTripHotel(String tripHotel) {
		this.tripHotel = tripHotel;
	}

	public int getTripMoneyspent() {
		return this.tripMoneyspent;
	}

	public void setTripMoneyspent(int tripMoneyspent) {
		this.tripMoneyspent = tripMoneyspent;
	}

	public int getTripNumdays() {
		return this.tripNumdays;
	}

	public void setTripNumdays(int tripNumdays) {
		this.tripNumdays = tripNumdays;
	}

	public int getTripNummintransit() {
		return this.tripNummintransit;
	}

	public void setTripNummintransit(int tripNummintransit) {
		this.tripNummintransit = tripNummintransit;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Traveler getTraveler() {
		return this.traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}

	public List<TripSightseeing> getTripSightseeings() {
		return this.tripSightseeings;
	}

	public void setTripSightseeings(List<TripSightseeing> tripSightseeings) {
		this.tripSightseeings = tripSightseeings;
	}

	public TripSightseeing addTripSightseeing(TripSightseeing tripSightseeing) {
		getTripSightseeings().add(tripSightseeing);
		tripSightseeing.setTrip(this);

		return tripSightseeing;
	}

	public TripSightseeing removeTripSightseeing(TripSightseeing tripSightseeing) {
		getTripSightseeings().remove(tripSightseeing);
		tripSightseeing.setTrip(null);

		return tripSightseeing;
	}
	
	public String toString() {
		return "trip on the "+ this.getTripDate()+" stayed in "+this.getTripHotel()+" hotel, budget was: " +this.getTripMoneyspent()+" transit time was "+ this.getTripNummintransit()+" minutes";
	}
	public String nameCompat() {
	     //SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	       // Date date = dt.parse(date_s);

	        // *** same for the format String below
	        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
	        System.out.println();
		return "trip on the "+ /* LocalDate.of( this.tripDate.getYear() , this.tripDate.getMonth() , this.tripDate.getDay() )   */   dt1.format(this.tripDate)  /*this.tripDate.getTime()*/ +" to "+this.getCity().getCityName();
	}


}