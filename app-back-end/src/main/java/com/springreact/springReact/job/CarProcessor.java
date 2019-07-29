package com.springreact.springReact.job;



import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.springreact.springReact.model.Car;
import com.springreact.springReact.model.EngineType;
import com.springreact.springReact.model.FuelType;
import com.springreact.springReact.repositories.EngineTypeRepository;
import com.springreact.springReact.repositories.FuelTypeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarProcessor implements ItemProcessor<Car, Car>{

	
	@Autowired
	private FuelTypeRepository fuelTypeRepository;
	
	@Autowired
	private EngineTypeRepository engineTypeRepository;
	
	private Map<String, FuelType> fuelMap;
	
	private Map<String, EngineType> engineMap;
	
	public CarProcessor() {
		fuelMap = new HashMap<String, FuelType>();
		engineMap = new HashMap<String, EngineType>();
	}
	
	private FuelType resolveFuel(String fuelName) {
		FuelType result = null;
		if(this.fuelMap.containsKey(fuelName)) {
			result = fuelMap.get(fuelName);
		}else {
			result = fuelTypeRepository.save(new FuelType(fuelName));
			this.fuelMap.put(fuelName, result);
		}
		return result;
	}
	
	private EngineType resolveEngine(String engineModel) {
		EngineType result = null;
		if(this.engineMap.containsKey(engineModel)) {
			result = engineMap.get(engineModel);
		}else {
			result = engineTypeRepository.save(new EngineType(engineModel));
			this.engineMap.put(engineModel, result);
		}
		return result;
	}
	
	
	@Override
	public Car process(Car item) throws Exception {
		item.setFuelType(this.resolveFuel(item.getFuelType().getName()));
		item.setEngineType(this.resolveEngine(item.getEngineType().getName()));
		return item;
	}

	

}
