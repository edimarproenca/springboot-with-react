package com.springreact.springReact.job;


import java.time.Duration;
import java.time.LocalDateTime;

import javax.batch.runtime.BatchStatus;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobNotification extends JobExecutionListenerSupport {

	private LocalDateTime startTime;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		startTime = LocalDateTime.now();
		log.info("Before Job! | ID -> " + jobExecution.getJobId() );
		log.info("Started -> " + startTime.toString());
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus().equals(BatchStatus.COMPLETED)) {	
			log.info("Job Finished");
			log.info("After -> " + Duration.between(startTime, LocalDateTime.now()).toString() )
			;
			
			
			
		}
	}
	
}
