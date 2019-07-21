package com.springreact.springReact.job;


import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.springreact.springReact.model.Car;

import lombok.extern.slf4j.Slf4j;

@EnableBatchProcessing
@Configuration
@Slf4j
public class CsvFileToDataBase {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
    @Autowired
    private EntityManagerFactory emf;
	
	@Bean
	public FlatFileItemReader<Car> csvCarReader(){
		FlatFileItemReader<Car> reader = new FlatFileItemReader<Car>();
		reader.setResource(new ClassPathResource("cars.csv"));
		DefaultLineMapper<Car> carLineMapper = new DefaultLineMapper<Car>();
		carLineMapper.setLineTokenizer(new DelimitedLineTokenizer());
		carLineMapper.setFieldSetMapper(new CarFieldSetMapper());
		reader.setLinesToSkip(1);
		reader.setLineMapper(carLineMapper);
		reader.open(new ExecutionContext());
		return reader;
	}
	
	@Bean
	ItemProcessor<Car,Car> csvCarProcessor(){
		return new CarProcessor();
	}
	
    @Bean
    public JpaItemWriter writer() {
        JpaItemWriter writer = new JpaItemWriter();
        writer.setEntityManagerFactory(emf);
        return writer;
    }
	
	@Bean
	public Step csvFileToDatabaseStep() {
		return stepBuilderFactory.get("csvFileToDatabaseStep")
				.<Car, Car>chunk(1000)
				.reader(csvCarReader())
				.processor(csvCarProcessor())
				.writer(writer())
				.build();
	}

	@Bean
	Job csvFileToDatabaseJob(JobNotification listener) {
		return jobBuilderFactory.get("csvFileToDatabaseJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(csvFileToDatabaseStep())
				.end()
				.build();
	}
	
}
