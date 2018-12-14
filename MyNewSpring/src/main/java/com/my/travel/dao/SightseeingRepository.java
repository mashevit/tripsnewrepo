package com.my.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.my.travel.model2.City;
import com.my.travel.model2.Sightseeing;
import com.my.travel.model2.Trip;

@Repository
public interface SightseeingRepository extends JpaRepository<Sightseeing, Integer> {

		List<Sightseeing> findBytripSightseeingsTripIdtripIn(int id);
	
	
	@Query("select u from Sightseeing u left join fetch u.city x where x.idcities = :id") 
	public List<Sightseeing> getUserInfo(@Param("id") int id);
	
	List<Sightseeing> findBycityIdcitiesIn(int id);
	List<Sightseeing> findBycityIn(City id);
	int countByCity(City c);
	//List<Sightseeing>  findBycityInAndtripSightseeingsTripNotIn(City id,Trip id1);
	}
