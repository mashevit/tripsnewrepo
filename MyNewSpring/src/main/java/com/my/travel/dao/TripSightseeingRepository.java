package com.my.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.travel.model2.Sightseeing;
import com.my.travel.model2.Trip;
import com.my.travel.model2.TripSightseeing;

@Repository
public interface TripSightseeingRepository  extends JpaRepository<TripSightseeing, Integer> {
  TripSightseeing findOneByTripAndSightseeing(Trip t,Sightseeing s);
  
  TripSightseeing findOneByidtripSightseeing(int id);
  
  List<TripSightseeing> findAllByTripCityIdcities(int id);
  
  
  List<TripSightseeing> findAllByTripIdtrip(int id);
}
