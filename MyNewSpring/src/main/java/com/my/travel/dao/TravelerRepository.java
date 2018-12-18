package com.my.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.my.travel.model2.Traveler;


@Repository
public interface TravelerRepository extends JpaRepository<TRAVELER, Integer> {
	   
	@Query("select u.travelerName from TRAVELER u")
	   List<String> getAllNames();
	
    List<Traveler> findBytravelerNameContainingIgnoreCase(String name);
	

}
