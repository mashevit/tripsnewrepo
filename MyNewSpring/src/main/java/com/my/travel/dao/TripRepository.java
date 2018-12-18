package com.my.travel.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.my.travel.model2.City;
import com.my.travel.model2.Trip;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
	

	
	List<Trip> findByCityIn(City city);

	
	@Query("SELECT t FROM TRIP t GROUP BY t.city ORDER BY COUNT(t.city) Asc")
	List<Trip> uniqueTrips(Pageable pageable);

}
