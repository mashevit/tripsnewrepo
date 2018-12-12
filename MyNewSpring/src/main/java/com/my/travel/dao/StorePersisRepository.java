package com.my.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.travel.model2.Sightseeing;
import com.my.travel.model2.StorePersis;

@Repository
public interface StorePersisRepository extends JpaRepository<StorePersis, Integer>{

	
	List<StorePersis> findBycatname(String cat);
}
