package com.my.travel.dao;

import org.springframework.data.repository.CrudRepository;

import com.my.travel.model2.Car;

public interface CarRepository extends CrudRepository <Car, Long> {

}
