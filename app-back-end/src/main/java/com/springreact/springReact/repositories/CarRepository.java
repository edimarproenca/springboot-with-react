package com.springreact.springReact.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springreact.springReact.model.Car;
import com.springreact.springReact.model.EngineType;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	public List<Car> findAllByEngineTypeEquals(EngineType engineType);
	
	
}
