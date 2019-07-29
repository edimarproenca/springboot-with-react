package com.springreact.springReact.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springreact.springReact.model.Car;
import com.springreact.springReact.repositories.CarRepository;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public List<Car> findAll(){
		Pageable pag = PageRequest.of(0, 15);
		return carRepository.findAll(pag).stream().collect(Collectors.toList());
	}

}
