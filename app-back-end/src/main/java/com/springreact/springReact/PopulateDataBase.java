package com.springreact.springReact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springreact.springReact.model.Car;
import com.springreact.springReact.model.EngineType;
import com.springreact.springReact.model.FuelType;
import com.springreact.springReact.repositories.CarRepository;
import com.springreact.springReact.repositories.EngineTypeRepository;
import com.springreact.springReact.repositories.FuelTypeRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PopulateDataBase implements CommandLineRunner {

	@Autowired 
	private CarRepository carRepository;
	
	@Autowired 
	private FuelTypeRepository fuelTypeRepository;
	
	@Autowired EngineTypeRepository engineTypeRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
//		FuelType fuel = fuelTypeRepository.save(new FuelType("Gasolina"));
//		EngineType engine = engineTypeRepository.save(new EngineType("APZAO !"));
//		Car car = carRepository.save(new Car("Vw Quantum", engine, "1988", fuel));
//		log.info("Finished");
	}

}
