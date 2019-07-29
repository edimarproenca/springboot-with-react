package com.springreact.springReact.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springreact.springReact.model.Car;
import com.springreact.springReact.repositories.CarRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public List<Car> findAll(int first){
		Pageable pag = PageRequest.of(first, 15);
		return carRepository.findAll(pag).stream().collect(Collectors.toList());
	}

}
