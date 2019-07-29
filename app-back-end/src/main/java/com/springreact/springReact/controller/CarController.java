package com.springreact.springReact.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springreact.springReact.model.Car;
import com.springreact.springReact.repositories.CarRepository;
import com.springreact.springReact.service.CarService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/cars/")
@Slf4j
public class CarController {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;
	
	@RequestMapping(path = "findAll",method = RequestMethod.GET ,produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Car> getAll() {
		return carService.findAll();
	}

	@RequestMapping(path = "delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deleteCar(@RequestParam(name = "id")Long id) {
		ResponseEntity<Boolean> result = null;
		try {
			carRepository.deleteById(id);
			result = new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
		}catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
		return result;
	}
	
	@RequestMapping(path = "loadDataBase",method = RequestMethod.POST ,produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<BatchStatus> load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {
		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters parameters = new JobParameters(maps);
		JobExecution jobExecution = jobLauncher.run(job, parameters);
		System.out.println("JobExecution: " + jobExecution.getStatus());
		System.out.println("Batch is Running...");
		while (jobExecution.isRunning()) {
			System.out.println("...");
		}
		return new ResponseEntity<BatchStatus>(jobExecution.getStatus(), HttpStatus.OK);
	}
	
	
	
	
	
}
