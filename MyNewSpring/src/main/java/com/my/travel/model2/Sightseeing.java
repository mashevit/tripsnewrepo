package com.my.travel.model2;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the sightseeings database table.
 * 
 */
@Entity
@Table(name="sightseeings")
@NamedQuery(name="Sightseeing.findAll", query="SELECT s FROM Sightseeing s")
@NamedEntityGraph(name = "GroupInfo.detail",
attributeNodes = @NamedAttributeNode("city"))
public class Sightseeing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idsightseeings;

	private String sightseeingsname;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(columnDefinition="integer", name="sightseeingscityid", nullable=false)
    @JsonBackReference
	private City city;

	//bi-directional many-to-one association to TripSightseeing
	@OneToMany(mappedBy="sightseeing")
	@JsonIgnore
	private List<TripSightseeing> tripSightseeings;

	public Sightseeing() {
	}

	public int getIdsightseeings() {
		return this.idsightseeings;
	}

	public void setIdsightseeings(int idsightseeings) {
		this.idsightseeings = idsightseeings;
	}

	public String getSightseeingsname() {
		return this.sightseeingsname;
	}

	public void setSightseeingsname(String sightseeingsname) {
		this.sightseeingsname = sightseeingsname;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<TripSightseeing> getTripSightseeings() {
		return this.tripSightseeings;
	}

	public void setTripSightseeings(List<TripSightseeing> tripSightseeings) {
		this.tripSightseeings = tripSightseeings;
	}

	public TripSightseeing addTripSightseeing(TripSightseeing tripSightseeing) {
		getTripSightseeings().add(tripSightseeing);
		tripSightseeing.setSightseeing(this);

		return tripSightseeing;
	}

	public TripSightseeing removeTripSightseeing(TripSightseeing tripSightseeing) {
		getTripSightseeings().remove(tripSightseeing);
		tripSightseeing.setSightseeing(null);

		return tripSightseeing;
	}
	public String toString() {
		return this.sightseeingsname;
	}
	public String toStringDetail() {
		return this.sightseeingsname +" in city "+ this.getCity().getCityName();
	}
}