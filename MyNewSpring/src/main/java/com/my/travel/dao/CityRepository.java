package com.my.travel.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.my.travel.model2.City;
import com.my.travel.model2.Trip;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
     City findByTripsIn(Trip id);
     
 	@Query("SELECT a FROM TRIP t Join t.city a GROUP BY a ORDER BY COUNT(a) Desc")
 	List<City> commoncities(Pageable pageable);
 	
 	List<City> findByOrderByIdcitiesDesc();
 	
 	City findOneByCityName(String name);
}
